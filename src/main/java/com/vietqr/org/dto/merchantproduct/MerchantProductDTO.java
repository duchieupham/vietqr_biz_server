package com.vietqr.org.dto.merchantproduct;

public class MerchantProductDTO {
    private String imgId;
    private String categoryId;
    private String name;
    private String unit;
    private String tid;

    public MerchantProductDTO() {
    }

    public MerchantProductDTO(String imgId, String categoryId, String name, String unit, String tid) {
        this.imgId = imgId;
        this.categoryId = categoryId;
        this.name = name;
        this.unit = unit;
        this.tid = tid;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
