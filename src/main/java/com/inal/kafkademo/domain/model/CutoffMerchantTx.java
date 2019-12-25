package com.inal.kafkademo.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cutoff_merchant_tx")
public class CutoffMerchantTx {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "idate", nullable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "udate")
    private Date lastUpdatedDate;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "payout_amount", nullable = false)
    private BigDecimal payoutAmount = BigDecimal.ZERO;

    @Column(name = "company_payout_amount", nullable = false)
    private BigDecimal companyPayoutAmount = BigDecimal.ZERO;

    @Column(name = "merchant_basket_item_id", nullable = false)
    private String merchantBasketItemId;

    @Column(name = "payment_tx_id", nullable = false)
    private Long paymentTxId;

    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public BigDecimal getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(BigDecimal payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public BigDecimal getCompanyPayoutAmount() {
        return companyPayoutAmount;
    }

    public void setCompanyPayoutAmount(BigDecimal companyPayoutAmount) {
        this.companyPayoutAmount = companyPayoutAmount;
    }

    public String getMerchantBasketItemId() {
        return merchantBasketItemId;
    }

    public void setMerchantBasketItemId(String merchantBasketItemId) {
        this.merchantBasketItemId = merchantBasketItemId;
    }

    public Long getPaymentTxId() {
        return paymentTxId;
    }

    public void setPaymentTxId(Long paymentTxId) {
        this.paymentTxId = paymentTxId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
