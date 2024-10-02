package com.vietqr.org.dto.terminalorder;

import com.vietqr.org.dto.terminalorderitem.ITerminalOrderItemDTO;

import java.util.List;

public class TerminalOrderInfoDTO {
    String tid;

    String customerId;

    String staffId;

    long totalAmount;

    long vatAmount;

    long discount;

    long timeCreated;

    long timePaid;

    int status;

    List<ITerminalOrderItemDTO> items;

    public TerminalOrderInfoDTO() {
    }

    public TerminalOrderInfoDTO(String tid, String customerId, String staffId, long totalAmount, long vatAmount, long discount, long timeCreated, long timePaid, int status, List<ITerminalOrderItemDTO> items) {
        this.tid = tid;
        this.customerId = customerId;
        this.staffId = staffId;
        this.totalAmount = totalAmount;
        this.vatAmount = vatAmount;
        this.discount = discount;
        this.timeCreated = timeCreated;
        this.timePaid = timePaid;
        this.status = status;
        this.items = items;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public long getTotalAmount() {
        return totalAmount;
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

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
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

    public List<ITerminalOrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ITerminalOrderItemDTO> items) {
        this.items = items;
    }
}
