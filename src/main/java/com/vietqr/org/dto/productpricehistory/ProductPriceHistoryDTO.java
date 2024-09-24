package com.vietqr.org.dto.productpricehistory;

import com.vietqr.org.utils.DateTimeUtil;

import javax.validation.constraints.NotNull;

public class ProductPriceHistoryDTO {
    @NotNull
    private String productId;

    @NotNull
    private int amount;

    @NotNull
    private long createdAt = DateTimeUtil.getNowUTC();

    public ProductPriceHistoryDTO() {
    }

    public ProductPriceHistoryDTO(String productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public ProductPriceHistoryDTO(String productId, int amount, long createdAt) {
        this.productId = productId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getProductId() {
        return productId.trim();
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
