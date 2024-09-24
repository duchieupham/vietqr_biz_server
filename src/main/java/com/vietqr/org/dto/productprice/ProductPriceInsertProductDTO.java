package com.vietqr.org.dto.productprice;

import javax.validation.constraints.NotNull;

public class ProductPriceInsertProductDTO {
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

    public ProductPriceInsertProductDTO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
