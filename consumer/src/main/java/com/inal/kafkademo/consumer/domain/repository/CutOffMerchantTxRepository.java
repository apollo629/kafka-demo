package com.inal.kafkademo.consumer.domain.repository;

import com.inal.kafkademo.consumer.domain.model.CutoffMerchantTx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CutOffMerchantTxRepository extends JpaRepository<CutoffMerchantTx, Long> {
}
