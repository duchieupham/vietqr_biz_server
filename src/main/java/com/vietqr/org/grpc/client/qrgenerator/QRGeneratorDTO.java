package com.vietqr.org.grpc.client.qrgenerator;

public class QRGeneratorDTO {
    private String bankCode;
    private String bankName;
    private String bankAccount;
    private String userBankName;
    private long amount;
    private String content;
    private String qrCode;
    private String imgId;
    private int existing;
    private String transactionId;
    private String transactionRefId;
    private String qrLink;
    private String terminalCode;
    private String subTerminalCode;
    private String serviceCode;
    private String orderId;

    public QRGeneratorDTO() {
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public int getExisting() {
        return existing;
    }

    public void setExisting(int existing) {
        this.existing = existing;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionRefId() {
        return transactionRefId;
    }

    public void setTransactionRefId(String transactionRefId) {
        this.transactionRefId = transactionRefId;
    }

    public String getQrLink() {
        return qrLink;
    }

    public void setQrLink(String qrLink) {
        this.qrLink = qrLink;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getSubTerminalCode() {
        return subTerminalCode;
    }

    public void setSubTerminalCode(String subTerminalCode) {
        this.subTerminalCode = subTerminalCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void clone(QRGeneratorDTO dto) {
        bankCode = dto.getBankCode();
        bankName = dto.getBankName();
        bankAccount = dto.getBankAccount();
        userBankName = dto.getUserBankName();
        amount = dto.getAmount();
        content = dto.getContent();
        qrCode = dto.getQrCode();
        imgId = dto.getImgId();
        existing = dto.getExisting();
        transactionId = dto.getTransactionId();
        transactionRefId = dto.getTransactionRefId();
        qrLink = dto.getQrLink();
        terminalCode = dto.getTerminalCode();
        subTerminalCode = dto.getSubTerminalCode();
        serviceCode = dto.getServiceCode();
        orderId = dto.getOrderId();
    }
}
