package com.vietqr.org.dto.terminalorder;

public interface ITerminalOrderInfoDTO {
    String getTid();

    String getCustomerId();

    String getStaffId();

    int getTotalAmount();

    int getVatAmount();

    long getTimeCreated();

    long getTimePaid();

    int getStatus();
}
