package com.inal.kafkademo.request;

import java.math.BigDecimal;

public class CancelRequest extends Request {

    private Long paymentRefundId;
    private String transactionStatusDate;
    private String blockageResolvedDate;
    private String merchantType;
    private Long merchantId;
    private Long currencyId;
    private BigDecimal cancelAmount;
    private BigDecimal iyzicoCancelAmount;
    private String merchantBasketItemId;

    public Long getPaymentRefundId() {
        return paymentRefundId;
    }

    public void setPaymentRefundId(Long paymentRefundId) {
        this.paymentRefundId = paymentRefundId;
    }

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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getCancelAmount() {
        return cancelAmount;
    }

    public void setCancelAmount(BigDecimal cancelAmount) {
        this.cancelAmount = cancelAmount;
    }

    public BigDecimal getIyzicoCancelAmount() {
        return iyzicoCancelAmount;
    }

    public void setIyzicoCancelAmount(BigDecimal iyzicoCancelAmount) {
        this.iyzicoCancelAmount = iyzicoCancelAmount;
    }

    public String getMerchantBasketItemId() {
        return merchantBasketItemId;
    }

    public void setMerchantBasketItemId(String merchantBasketItemId) {
        this.merchantBasketItemId = merchantBasketItemId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CancelRequest{");
        sb.append("paymentTxId=").append(super.getPaymentTxId());
        sb.append("paymentRefundId=").append(paymentRefundId);
        sb.append(", transactionStatusDate='").append(transactionStatusDate).append('\'');
        sb.append(", blockageResolvedDate='").append(blockageResolvedDate).append('\'');
        sb.append(", merchantType='").append(merchantType).append('\'');
        sb.append(", merchantId=").append(merchantId);
        sb.append(", currencyId=").append(currencyId);
        sb.append(", cancelAmount=").append(cancelAmount);
        sb.append(", iyzicoCancelAmount=").append(iyzicoCancelAmount);
        sb.append(", merchantBasketItemId='").append(merchantBasketItemId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
