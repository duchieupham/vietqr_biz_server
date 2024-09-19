package com.vietqr.org.dto.merchantcategory;

import javax.validation.constraints.NotNull;

public class MerchantCategoryDTO {
    @NotNull
    private String mid;

    @NotNull
    private String name;

    public MerchantCategoryDTO() {
    }

    public MerchantCategoryDTO(String mid, String name) {
        this.mid = mid;
        this.name = name;
    }

    public @NotNull String getMid() {
        return mid.trim();
    }

    public void setMid(@NotNull String mid) {
        this.mid = mid;
    }

    public @NotNull String getName() {
        return name.trim();
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }
}
