package com.sudo248.invoiceservice.Controller.dto;

public class InvoiceAddDto {
    private String invoiceId,address, status;
    private String cartId;
    private String paymentId;
    private String promotionId;
    private String UserId;
    private Double totalPrice, totalPromotionPrice, finalPrice;

    public InvoiceAddDto(String invoiceId, String address, String status, String cartId, String paymentId, String promotionId, String userId, Double totalPrice, Double totalPromotionPrice, Double finalPrice) {
        this.invoiceId = invoiceId;
        this.address = address;
        this.status = status;
        this.cartId = cartId;
        this.paymentId = paymentId;
        this.promotionId = promotionId;
        UserId = userId;
        this.totalPrice = totalPrice;
        this.totalPromotionPrice = totalPromotionPrice;
        this.finalPrice = finalPrice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPromotionPrice() {
        return totalPromotionPrice;
    }

    public void setTotalPromotionPrice(Double totalPromotionPrice) {
        this.totalPromotionPrice = totalPromotionPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
