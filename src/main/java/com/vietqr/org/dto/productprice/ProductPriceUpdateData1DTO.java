package com.vietqr.org.dto.productprice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductPriceUpdateData1DTO {
    @NotBlank
    private String id;

    @NotNull
    private String data1;

    public ProductPriceUpdateData1DTO() {
    }

    public @NotBlank String getId() {
        return id.trim();
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    public @NotNull String getData1() {
        return data1.trim();
    }

    public void setData1(@NotNull String data1) {
        this.data1 = data1;
    }
}
