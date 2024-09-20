package com.vietqr.org.grpc.client.qrgenerator;

public class RequestSemiDynamicQRDTO {
    private long amount;
    private String content;
    private String bankAccount;
    private String bankCode;
    private String transType;
    private String serviceCode;
    private String token;

    public RequestSemiDynamicQRDTO() {
    }

    public RequestSemiDynamicQRDTO(long amount, String content, String bankAccount, String bankCode, String transType, String serviceCode, String token) {
        this.amount = amount;
        this.content = content;
        this.bankAccount = bankAccount;
        this.bankCode = bankCode;
        this.transType = transType;
        this.serviceCode = serviceCode;
        this.token = token;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
