package com.inal.kafkademo.consumer.service;

import com.inal.kafkademo.consumer.domain.model.CutOffMerchant;
import com.inal.kafkademo.consumer.domain.model.CutOffMerchantTx;
import com.inal.kafkademo.consumer.domain.repository.CutOffMerchantRepository;
import com.inal.kafkademo.consumer.domain.repository.CutOffMerchantTxRepository;
import com.inal.kafkademo.commons.request.AuthRequest;
import com.inal.kafkademo.consumer.lock.LockService;
import com.inal.kafkademo.consumer.lock.LockedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CutoffMerchantTxService {

    private static final Logger logger = LoggerFactory.getLogger(AuthConsumerService.class);

    private final CutOffMerchantTxRepository cutOffMerchantTxRepository;
    private final CutOffMerchantRepository cutOffMerchantRepository;
    private final LockService lockService;
    private final DateFormat dateFormat;

    public CutoffMerchantTxService(CutOffMerchantTxRepository cutOffMerchantTxRepository,
                                   CutOffMerchantRepository cutOffMerchantRepository,
                                   LockService lockService) {
        this.cutOffMerchantTxRepository = cutOffMerchantTxRepository;
        this.cutOffMerchantRepository = cutOffMerchantRepository;
        this.lockService = lockService;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void create(AuthRequest authMessage) {
        Optional<CutOffMerchant> optionalCutOffMerchant = cutOffMerchantRepository.findByMerchantIdAndCurrencyIdAndStatusAndPayoutStatus(authMessage.getMerchantId(), authMessage.getCurrencyId(), 1, 10);
        if (!optionalCutOffMerchant.isPresent()) {
            try {
                String key = generateKey(authMessage.getMerchantId(), authMessage.getCurrencyId());
                lockService.lock(key);
                CutOffMerchant cutOffMerchant = convertToCutOffMerchant(authMessage);
                cutOffMerchantRepository.save(cutOffMerchant);
                logger.info("CutOffMerchant is created with id: {}", cutOffMerchant.getId());
                lockService.unlock(key);

                CutOffMerchantTx cutoffMerchantTx = convertToCutoffMerchantTx(authMessage, cutOffMerchant);
                CutOffMerchantTx savedCutOffMerchantTx = cutOffMerchantTxRepository.save(cutoffMerchantTx);
                logger.info("CutoffMerchantTx is created with id: {}", savedCutOffMerchantTx.getId());

                cutOffMerchantRepository.updatePayoutAmountsById(authMessage.getMerchantPayoutAmount(), authMessage.getCompanyPayoutAmount(), cutOffMerchant.getId());
                logger.info("CutOffMerchant payout amounts updated with id: {}", cutOffMerchant.getId());
            } catch (LockedException e) {
                logger.error("Cannot acquire lock to create cutoff merchant {}-{}", authMessage.getMerchantId(), authMessage.getCurrencyId(), e);
                throw new RuntimeException(e);
            }
        } else {
            CutOffMerchant cutOffMerchant = optionalCutOffMerchant.get();
            CutOffMerchantTx cutoffMerchantTx = convertToCutoffMerchantTx(authMessage, cutOffMerchant);
            CutOffMerchantTx savedCutOffMerchantTx = cutOffMerchantTxRepository.save(cutoffMerchantTx);
            logger.info("CutoffMerchantTx is created with id: {}", savedCutOffMerchantTx.getId());

            cutOffMerchantRepository.updatePayoutAmountsById(authMessage.getMerchantPayoutAmount(), authMessage.getCompanyPayoutAmount(), cutOffMerchant.getId());
            logger.info("CutOffMerchant payout amounts updated with id: {}", cutOffMerchant.getId());
        }
    }

    private CutOffMerchant convertToCutOffMerchant(AuthRequest authMessage) {
        CutOffMerchant cutOffMerchant = new CutOffMerchant();
        cutOffMerchant.setCreatedDate(new Date());
        cutOffMerchant.setStatus(1);
        Date date = parseDate(authMessage);
        cutOffMerchant.setSettlementResolvedDate(date);
        cutOffMerchant.setStartDate(date);
        cutOffMerchant.setEndDate(date);
        cutOffMerchant.setPayoutStatus(10);
        cutOffMerchant.setPayoutAmount(BigDecimal.ZERO);
        cutOffMerchant.setCompanyPayoutAmount(BigDecimal.ZERO);
        cutOffMerchant.setSettlementReferenceCode("ABC");
        cutOffMerchant.setMerchantId(authMessage.getMerchantId());
        cutOffMerchant.setCurrencyId(authMessage.getCurrencyId());
        return cutOffMerchant;
    }

    private Date parseDate(AuthRequest authMessage) {
        try {
            return dateFormat.parse(authMessage.getBlockageResolvedDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CutOffMerchantTx convertToCutoffMerchantTx(AuthRequest authMessage, CutOffMerchant cutOffMerchant) {
        CutOffMerchantTx cutoffMerchantTx = new CutOffMerchantTx();
        cutoffMerchantTx.setPaymentTxId(authMessage.getPaymentTxId());
        cutoffMerchantTx.setPayoutAmount(authMessage.getMerchantPayoutAmount());
        cutoffMerchantTx.setCompanyPayoutAmount(authMessage.getCompanyPayoutAmount());
        cutoffMerchantTx.setCurrencyId(authMessage.getCurrencyId());
        cutoffMerchantTx.setMerchantBasketItemId(authMessage.getMerchantBasketItemId());
        cutoffMerchantTx.setCreatedDate(new Date());
        cutoffMerchantTx.setStatus(1);
        cutoffMerchantTx.setCutOffMerchant(cutOffMerchant);
        return cutoffMerchantTx;
    }

    private String generateKey(Long merchantId, Long currencyId) {
        return "merchantId:" + merchantId.toString() + ":currencyId:" + currencyId.toString();
    }
}
