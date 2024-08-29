package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terminalOrderItem")
public class TerminalOrderItemEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "orderId")
    private String orderId;
    @Column(name = "productId")
    private String productId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private int amount;
    @Column(name = "totalAmount")
    private int totalAmount;
    @Column(name = "vat")
    private int vat;
    @Column(name = "vatAmount")
    private int vatAmount;
    @Column(name = "discountAmount")
    private int discountAmount;

    public TerminalOrderItemEntity() {
    }

    public TerminalOrderItemEntity(String id, String orderId, String productId, int quantity, int amount, int totalAmount, int vat, int vatAmount, int discountAmount) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.vat = vat;
        this.vatAmount = vatAmount;
        this.discountAmount = discountAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(int vatAmount) {
        this.vatAmount = vatAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }
}
