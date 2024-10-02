package com.vietqr.org.dto.terminalorderitem;

import javax.validation.constraints.NotEmpty;

public class TerminalOrderItemInsertDTO {
    private String orderId;

    @NotEmpty
    private String productId;

    private int quantity;

    private double vat;

    private long discount;

    private long amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId.trim();
    }

    public String getProductId() {
        return productId.trim();
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
