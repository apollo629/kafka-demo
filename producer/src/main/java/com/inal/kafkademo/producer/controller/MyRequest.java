package com.inal.kafkademo.producer.controller;

public class MyRequest {

    private Long merchantId;
    private Long paymentId;
    private String paymentConversationId;
    private Long paymentTxId;
    private String refundConversationId;
    private String status;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentConversationId() {
        return paymentConversationId;
    }

    public void setPaymentConversationId(String paymentConversationId) {
        this.paymentConversationId = paymentConversationId;
    }

    public Long getPaymentTxId() {
        return paymentTxId;
    }

    public void setPaymentTxId(Long paymentTxId) {
        this.paymentTxId = paymentTxId;
    }

    public String getRefundConversationId() {
        return refundConversationId;
    }

    public void setRefundConversationId(String refundConversationId) {
        this.refundConversationId = refundConversationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
