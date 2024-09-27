package com.vietqr.org.dto.terminalorderitem;

public interface ITerminalOrderItemIdDTO {
    String getProductId();

    String getOrderId();

    int getQuantity();

    int getAmount();

    int getTotalAmount();

    int getVat();

    int getVatAmount();

    int getDiscountAmount();
}
