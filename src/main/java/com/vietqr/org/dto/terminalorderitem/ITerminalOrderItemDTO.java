package com.vietqr.org.dto.terminalorderitem;

public interface ITerminalOrderItemDTO {
    String getProductId();

    int getQuantity();

    int getAmount();

    int getTotalAmount();

    int getVat();

    int getVatAmount();

    int getDiscountAmount();

    String getImgId();

    String getUnit();

    String getName();
}
