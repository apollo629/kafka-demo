package com.inal.kafkademo.consumer.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cutoff_merchant")
public class CutOffMerchant {

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

    @Column(name = "settlement_resolved_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date settlementResolvedDate;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "payout_status", nullable = false)
    private Integer payoutStatus;

    @Column(name = "payout_completed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payoutCompletedDate;

    @Column(name = "payout_amount", nullable = false)
    private BigDecimal payoutAmount = BigDecimal.ZERO;

    @Column(name = "company_payout_amount", nullable = false)
    private BigDecimal companyPayoutAmount = BigDecimal.ZERO;

    @Column(name = "settlement_reference_code", nullable = false)
    private String settlementReferenceCode;

    @Column(name = "bank_transfer_code")
    private String bankTransferCode;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;

    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    @OneToMany(mappedBy = "cutOffMerchant")
    private List<CutOffMerchantTx> cutOffMerchantTxList = new ArrayList<>();

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

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSettlementResolvedDate() {
        return settlementResolvedDate;
    }

    public void setSettlementResolvedDate(Date settlementResolvedDate) {
        this.settlementResolvedDate = settlementResolvedDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPayoutStatus() {
        return payoutStatus;
    }

    public void setPayoutStatus(Integer payoutStatus) {
        this.payoutStatus = payoutStatus;
    }

    public Date getPayoutCompletedDate() {
        return payoutCompletedDate;
    }

    public void setPayoutCompletedDate(Date payoutCompletedDate) {
        this.payoutCompletedDate = payoutCompletedDate;
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

    public String getSettlementReferenceCode() {
        return settlementReferenceCode;
    }

    public void setSettlementReferenceCode(String settlementReferenceCode) {
        this.settlementReferenceCode = settlementReferenceCode;
    }

    public String getBankTransferCode() {
        return bankTransferCode;
    }

    public void setBankTransferCode(String bankTransferCode) {
        this.bankTransferCode = bankTransferCode;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public List<CutOffMerchantTx> getCutOffMerchantTxList() {
        return cutOffMerchantTxList;
    }

    public void setCutOffMerchantTxList(List<CutOffMerchantTx> cutOffMerchantTxList) {
        this.cutOffMerchantTxList = cutOffMerchantTxList;
    }
}
