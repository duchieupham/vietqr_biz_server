package com.vietqr.org.dto.merchantcategory;

public class InsertMerchantCategoryDTO {
    private String mid;
    private String name;

    public InsertMerchantCategoryDTO() {
    }

    public InsertMerchantCategoryDTO(String mid, String name) {
        this.mid = mid;
        this.name = name;
    }


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
