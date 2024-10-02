package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terminal_order")
public class TerminalOrderEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "timeCreated", nullable = false)
    private long timeCreated;
    @Column(name = "timePaid", nullable = false)
    private long timePaid;

    /*
    * Status
    * 0: deleted
    * 1: paid
    * 2: waiting
    * 3: canceled
    * */
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "totalAmount", nullable = false)
    private long totalAmount;
    @Column(name = "vat", nullable = false)
    private double vat;
    @Column(name = "vatAmount", nullable = false)
    private long vatAmount;
    @Column(name = "discountAmount", nullable = false)
    private long discountAmount;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "staffId", nullable = false)
    private String staffId;
    @Column(name = "customerId", nullable = false)
    private String customerId;
    @Column(name = "tid", nullable = false)
    private String tid;

    public TerminalOrderEntity() {
    }

    public TerminalOrderEntity(String id, long timeCreated, long timePaid, int status, long totalAmount, double vat, long vatAmount, long discountAmount, String code, String staffId, String customerId, String tid) {
        this.id = id;
        this.timeCreated = timeCreated;
        this.timePaid = timePaid;
        this.status = status;
        this.totalAmount = totalAmount;
        this.vat = vat;
        this.vatAmount = vatAmount;
        this.discountAmount = discountAmount;
        this.code = code;
        this.staffId = staffId;
        this.customerId = customerId;
        this.tid = tid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
