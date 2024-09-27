package com.vietqr.org.dto.terminalorderitem;

import javax.validation.constraints.NotEmpty;

public class TerminalOrderItemInsertDTO {
    @NotEmpty
    private String orderId;

    @NotEmpty
    private String productId;

    private int quantity;

    private int vat;

    private int discount;

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

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
