package com.vietqr.org.dto.productprice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductPriceInsertDTO {
    @NotBlank
    private String productId;

    @NotNull
    private int amount;

    @NotBlank
    private String traceTransfer;

    @NotNull
    private String data1;

    @NotNull
    private String data2;

    public ProductPriceInsertDTO() {
    }

    public @NotBlank String getProductId() {
        return productId.trim();
    }

    public void setProductId(@NotBlank String productId) {
        this.productId = productId;
    }

    @NotNull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull int amount) {
        this.amount = amount;
    }

    public @NotBlank String getTraceTransfer() {
        return traceTransfer.trim();
    }

    public void setTraceTransfer(@NotBlank String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public @NotNull String getData1() {
        return data1.trim();
    }

    public void setData1(@NotNull String data1) {
        this.data1 = data1;
    }

    public @NotNull String getData2() {
        return data2.trim();
    }

    public void setData2(@NotNull String data2) {
        this.data2 = data2;
    }
}
