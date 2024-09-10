package com.vietqr.org.dto.productprice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductPriceUpdateAmountDTO {
    @NotBlank
    private String id;

    @NotNull
    private int amount;

    public ProductPriceUpdateAmountDTO() {}

    public @NotBlank String getId() {
        return id.trim();
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    @NotNull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull int amount) {
        this.amount = amount;
    }
}
