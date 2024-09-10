package com.vietqr.org.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_price", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id"})
})
public class ProductPriceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private String data1;

    @Column(nullable = false)
    private String data2;

    @Column(name = "trace_transfer", nullable = false)
    private String traceTransfer;

    @Column(nullable = false)
    private int amount;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "time_created", nullable = false)
    private long timeCreated;

    @Column(name = "time_updated", nullable = false)
    private long timeUpdated;

    public ProductPriceEntity() {
        super();
        this.id = "";
    }

    public ProductPriceEntity(String data1, String data2, String traceTransfer, int amount, String productId) {
        super();
        this.id = "";
        this.data1 = data1;
        this.data2 = data2;
        this.traceTransfer = traceTransfer;
        this.amount = amount;
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getTraceTransfer() {
        return traceTransfer;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public long getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(long timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}
