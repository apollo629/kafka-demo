package com.inal.kafkademo.request;

import java.math.BigDecimal;

public class AuthRequest extends Request {

    private String transactionStatusDate;
    private String blockageResolvedDate;
    private String merchantType;
    private Long merchantId;
    private Long subMerchantId;
    private Long currencyId;
    private BigDecimal merchantPayoutAmount;
    private BigDecimal subMerchantPayoutAmount;
    private BigDecimal iyzicoPayoutAmount;
    private String merchantBasketItemId;

    public String getTransactionStatusDate() {
        return transactionStatusDate;
    }

    public void setTransactionStatusDate(String transactionStatusDate) {
        this.transactionStatusDate = transactionStatusDate;
    }

    public String getBlockageResolvedDate() {
        return blockageResolvedDate;
    }

    public void setBlockageResolvedDate(String blockageResolvedDate) {
        this.blockageResolvedDate = blockageResolvedDate;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerchantId(Long subMerchantId) {
        this.subMerchantId = subMerchantId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getMerchantPayoutAmount() {
        return merchantPayoutAmount;
    }

    public void setMerchantPayoutAmount(BigDecimal merchantPayoutAmount) {
        this.merchantPayoutAmount = merchantPayoutAmount;
    }

    public BigDecimal getSubMerchantPayoutAmount() {
        return subMerchantPayoutAmount;
    }

    public void setSubMerchantPayoutAmount(BigDecimal subMerchantPayoutAmount) {
        this.subMerchantPayoutAmount = subMerchantPayoutAmount;
    }

    public BigDecimal getIyzicoPayoutAmount() {
        return iyzicoPayoutAmount;
    }

    public void setIyzicoPayoutAmount(BigDecimal iyzicoPayoutAmount) {
        this.iyzicoPayoutAmount = iyzicoPayoutAmount;
    }

    public String getMerchantBasketItemId() {
        return merchantBasketItemId;
    }

    public void setMerchantBasketItemId(String merchantBasketItemId) {
        this.merchantBasketItemId = merchantBasketItemId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthRequest{");
        sb.append("paymentTxId=").append(super.getPaymentTxId());
        sb.append(", transactionStatusDate='").append(transactionStatusDate).append('\'');
        sb.append(", blockageResolvedDate='").append(blockageResolvedDate).append('\'');
        sb.append(", merchantType='").append(merchantType).append('\'');
        sb.append(", merchantId=").append(merchantId);
        sb.append(", subMerchantId=").append(subMerchantId);
        sb.append(", currencyId=").append(currencyId);
        sb.append(", merchantPayoutAmount=").append(merchantPayoutAmount);
        sb.append(", subMerchantPayoutAmount=").append(subMerchantPayoutAmount);
        sb.append(", iyzicoPayoutAmount=").append(iyzicoPayoutAmount);
        sb.append(", merchantBasketItemId='").append(merchantBasketItemId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
