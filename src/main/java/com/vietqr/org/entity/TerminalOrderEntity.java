package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terminalOder")
public class TerminalOrderEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "timeCreate")
    private long timeCreate;
    @Column(name = "timePaid")
    private long timePaid;
    @Column(name = "status")
    private boolean status;
    @Column(name = "totalAmount")
    private int totalAmount;
    @Column(name = "vat")
    private double vat;
    @Column(name = "vatAmount")
    private int vatAmount;
    @Column(name = "discountAmount")
    private int discountAmount;
    @Column(name = "code")
    private String code;
    @Column(name = "staffId")
    private String staffId;
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "tid")
    private String tid;

    public TerminalOrderEntity() {
    }

    public TerminalOrderEntity(String id, long timeCreate, long timePaid, boolean status, int totalAmount, double vat, int vatAmount, int discountAmount, String code, String staffId, String customerId, String tid) {
        this.id = id;
        this.timeCreate = timeCreate;
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

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
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
