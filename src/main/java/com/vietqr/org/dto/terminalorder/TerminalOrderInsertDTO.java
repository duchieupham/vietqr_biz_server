package com.vietqr.org.dto.terminalorder;

public class TerminalOrderInsertDTO {
    private long timePaid;
    private int totalAmount;
    private double vat;
    private int vatAmount;
    private int discountAmount;
    private String code;
    private String staffId;
    private String customerId;
    private String tid;

    public TerminalOrderInsertDTO() {
    }

    public TerminalOrderInsertDTO(long timePaid, int totalAmount, double vat, int vatAmount, int discountAmount, String code, String staffId, String customerId, String tid) {
        this.timePaid = timePaid;
        this.totalAmount = totalAmount;
        this.vat = vat;
        this.vatAmount = vatAmount;
        this.discountAmount = discountAmount;
        this.code = code;
        this.staffId = staffId;
        this.customerId = customerId;
        this.tid = tid;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
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
