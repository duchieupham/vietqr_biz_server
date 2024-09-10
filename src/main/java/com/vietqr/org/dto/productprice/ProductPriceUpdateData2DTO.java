package com.vietqr.org.dto.productprice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductPriceUpdateData2DTO {
    @NotBlank
    private String id;

    @NotNull
    private String data2;

    public ProductPriceUpdateData2DTO() {
    }

    public @NotBlank String getId() {
        return id.trim();
    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    public @NotNull String getData2() {
        return data2.trim();
    }

    public void setData2(@NotNull String data2) {
        this.data2 = data2;
    }
}
