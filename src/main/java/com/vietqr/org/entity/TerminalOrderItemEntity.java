package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "terminal_order_item")
public class TerminalOrderItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Column(name = "order_id", nullable = false)
    private String orderId;
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private long amount;
    @Column(name = "total_amount", nullable = false)
    private long totalAmount;
    @Column(nullable = false)
    private double vat;
    @Column(name = "vat_amount", nullable = false)
    private long vatAmount;
    @Column(name = "discount_amount", nullable = false)
    private long discountAmount;

    public TerminalOrderItemEntity() {
        super();
    }

    public TerminalOrderItemEntity(String id, String orderId, String productId, int quantity, int amount, int totalAmount, int vat, int vatAmount, int discountAmount) {
        super();
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

    public TerminalOrderItemEntity(String id, String orderId, String productId, int quantity, double vat, long discountAmount, long amount) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.vat = vat;
        this.discountAmount = discountAmount;
        this.amount = amount;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public long getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(long vatAmount) {
        this.vatAmount = vatAmount;
    }

    public long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(long discountAmount) {
        this.discountAmount = discountAmount;
    }
}
