package com.vietqr.org.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "terminal_inventory")
public class TerminalInventoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private String tid;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "time_created", nullable = false)
    private Long timeCreated;

    @Column(name = "time_updated", nullable = false)
    private Long timeUpdated;

    @Column(nullable = false)
    private long exp;

    @Column(nullable = false)
    private long mfg;

    public TerminalInventoryEntity() {
        super();
        this.id = "";
    }

    public TerminalInventoryEntity(String productId, String tid, int quantity, long exp, long mfg) {
        super();
        this.id = "";
        this.productId = productId;
        this.tid = tid;
        this.quantity = quantity;
        this.exp = exp;
        this.mfg = mfg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public Long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Long getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Long timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getMfg() {
        return mfg;
    }

    public void setMfg(long mfg) {
        this.mfg = mfg;
    }
}
