package com.inal.kafkademo.service;

import com.inal.kafkademo.consumer.AuthConsumerService;
import com.inal.kafkademo.domain.model.CutoffMerchantTx;
import com.inal.kafkademo.domain.repository.CutOffMerchantTxRepository;
import com.inal.kafkademo.request.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CutoffMerchantTxService {

    private static final Logger logger = LoggerFactory.getLogger(AuthConsumerService.class);

    private final CutOffMerchantTxRepository cutOffMerchantTxRepository;

    public CutoffMerchantTxService(CutOffMerchantTxRepository cutOffMerchantTxRepository) {
        this.cutOffMerchantTxRepository = cutOffMerchantTxRepository;
    }

    public void create(AuthRequest authMessage) {
        CutoffMerchantTx cutoffMerchantTx = convertToCutoffMerchantTx(authMessage);
        CutoffMerchantTx savedCutoffMerchantTx = cutOffMerchantTxRepository.save(cutoffMerchantTx);
        logger.info("CutoffMerchantTx is created with id: {}", savedCutoffMerchantTx.getId());
    }

    private CutoffMerchantTx convertToCutoffMerchantTx(AuthRequest authMessage) {
        CutoffMerchantTx cutoffMerchantTx = new CutoffMerchantTx();
        cutoffMerchantTx.setPaymentTxId(authMessage.getPaymentTxId());
        cutoffMerchantTx.setPayoutAmount(authMessage.getMerchantPayoutAmount());
        cutoffMerchantTx.setCompanyPayoutAmount(authMessage.getCompanyPayoutAmount());
        cutoffMerchantTx.setCurrencyId(authMessage.getCurrencyId());
        cutoffMerchantTx.setMerchantBasketItemId(authMessage.getMerchantBasketItemId());
        cutoffMerchantTx.setCreatedDate(new Date());
        cutoffMerchantTx.setStatus(1);
        return cutoffMerchantTx;
    }
}
