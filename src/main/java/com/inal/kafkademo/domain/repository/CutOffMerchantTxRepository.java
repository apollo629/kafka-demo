package com.inal.kafkademo.domain.repository;

import com.inal.kafkademo.domain.model.CutoffMerchantTx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CutOffMerchantTxRepository extends JpaRepository<CutoffMerchantTx, Long> {
}
