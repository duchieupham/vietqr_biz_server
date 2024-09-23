package com.vietqr.org.dto.productprice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductPriceDTO {
    @NotBlank
    private String id;

    @NotNull
    private int amount;

    @NotNull
    private String content;

    @NotNull
    private String bankAccount;

    @NotNull
    private String bankCode;

    @NotNull
    private String transType;

    @NotNull
    private String serviceCode;

    public ProductPriceDTO() {
    }

    public ProductPriceDTO(String id, int amount, String content, String bankAccount, String bankCode, String transType, String serviceCode) {
        this.id = id;
        this.amount = amount;
        this.content = content;
        this.bankAccount = bankAccount;
        this.bankCode = bankCode;
        this.transType = transType;
        this.serviceCode = serviceCode;
    }

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

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public @NotNull String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(@NotNull String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public @NotNull String getBankCode() {
        return bankCode;
    }

    public void setBankCode(@NotNull String bankCode) {
        this.bankCode = bankCode;
    }

    public @NotNull String getTransType() {
        return transType;
    }

    public void setTransType(@NotNull String transType) {
        this.transType = transType;
    }

    public @NotNull String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(@NotNull String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
