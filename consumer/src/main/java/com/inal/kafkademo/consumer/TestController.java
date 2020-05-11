package com.inal.kafkademo.consumer;

import com.inal.kafkademo.consumer.domain.model.CutOffMerchant;
import com.inal.kafkademo.consumer.domain.repository.CutOffMerchantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final CutOffMerchantRepository cutOffMerchantRepository;

    public TestController(CutOffMerchantRepository cutOffMerchantRepository) {
        this.cutOffMerchantRepository = cutOffMerchantRepository;
    }

    @GetMapping("/test")
    public void test(){
        CutOffMerchant cutOffMerchant = convertToCutOffMerchant();
        CutOffMerchant savedCutoffMerchant = cutOffMerchantRepository.save(cutOffMerchant);
        logger.info("Converted object id: {}", cutOffMerchant.getId());
    }

    private CutOffMerchant convertToCutOffMerchant() {
        CutOffMerchant cutOffMerchant = new CutOffMerchant();
        cutOffMerchant.setCreatedDate(new Date());
        cutOffMerchant.setStatus(1);
        Date date = Date.from(Instant.now());
        cutOffMerchant.setSettlementResolvedDate(date);
        cutOffMerchant.setStartDate(date);
        cutOffMerchant.setEndDate(date);
        cutOffMerchant.setPayoutStatus(10);
        cutOffMerchant.setPayoutAmount(BigDecimal.ZERO);
        cutOffMerchant.setIyzicoPayoutAmount(BigDecimal.ZERO);
        cutOffMerchant.setSettlementReferenceCode("ABC");
        cutOffMerchant.setMerchantId(1L);
        cutOffMerchant.setCurrencyId(1L);
        return cutOffMerchant;
    }

}
