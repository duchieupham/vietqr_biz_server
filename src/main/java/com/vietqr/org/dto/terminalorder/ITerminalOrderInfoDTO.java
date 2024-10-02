package com.vietqr.org.dto.terminalorder;

public interface ITerminalOrderInfoDTO {
    String getTid();

    String getCustomerId();

    String getStaffId();

    long getTotalAmount();

    long getDiscountAmount();

    long getVatAmount();

    long getTimeCreated();

    long getTimePaid();

    int getStatus();
}
