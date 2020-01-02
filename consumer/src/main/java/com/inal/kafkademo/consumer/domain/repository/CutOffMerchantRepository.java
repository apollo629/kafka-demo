package com.inal.kafkademo.consumer.domain.repository;

import com.inal.kafkademo.consumer.domain.model.CutOffMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface CutOffMerchantRepository extends JpaRepository<CutOffMerchant, Long> {

    Optional<CutOffMerchant> findByMerchantIdAndCurrencyIdAndStatusAndPayoutStatus(Long merchantId, Long currencyId, Integer status, Integer payoutStatus);

    @Modifying
    @Transactional
    @Query("update CutOffMerchant " +
            "set payoutAmount = payoutAmount + :payoutAmount, " +
            "companyPayoutAmount = companyPayoutAmount + :companyPayoutAmount " +
            "where id = :id")
    void updatePayoutAmountsById(@Param("payoutAmount") BigDecimal payoutAmount,
                                 @Param("companyPayoutAmount") BigDecimal companyPayoutAmount,
                                 @Param("id") Long id);
}
