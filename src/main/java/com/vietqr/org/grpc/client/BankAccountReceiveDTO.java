package com.vietqr.org.grpc.client;

public class BankAccountReceiveDTO {
    private String bankAccount;
    private String userBankName;
    private boolean isSync;
    private String bankTypeId;
    private String bankShortName;
    private String userId;
    private String bankId;

    public BankAccountReceiveDTO() {
    }

    public BankAccountReceiveDTO(String bankAccount, String userBankName, boolean isSync, String bankTypeId, String bankShortName, String userId, String bankId) {
        this.bankAccount = bankAccount;
        this.userBankName = userBankName;
        this.isSync = isSync;
        this.bankTypeId = bankTypeId;
        this.bankShortName = bankShortName;
        this.userId = userId;
        this.bankId = bankId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUserBankName() {
        return userBankName;
    }

    public void setUserBankName(String userBankName) {
        this.userBankName = userBankName;
    }

    public boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(boolean sync) {
        isSync = sync;
    }

    public String getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(String bankTypeId) {
        this.bankTypeId = bankTypeId;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
