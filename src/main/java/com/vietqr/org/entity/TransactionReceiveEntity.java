package com.vietqr.org.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "transaction_receive")
public class TransactionReceiveEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private long time;

    @Column(name = "time_paid", nullable = false)
    private long timePaid;

    @Column(name = "service_code", nullable = false)
    private String serviceCode;

    @Size(min = 1, max = 15)
    @Column(name = "terminal_code", nullable = false)
    private String terminalCode;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "bank_account", nullable = false)
    private String bankAccount;

    @Column(name = "bank_id", nullable = false)
    private String bankId;

    @Column(name = "sub_code", nullable = false)
    private String subCode;

    @Column(name = "status_response", nullable = false)
    private boolean statusResponse;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String mid;

    public TransactionReceiveEntity() {
    }

    public TransactionReceiveEntity(String id, boolean status, int type, int amount, long time, long timePaid, String serviceCode, String terminalCode, String orderId, String referenceNumber, String bankAccount, String bankId, String subCode, boolean statusResponse, String content, String mid) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.timePaid = timePaid;
        this.serviceCode = serviceCode;
        this.terminalCode = terminalCode;
        this.orderId = orderId;
        this.referenceNumber = referenceNumber;
        this.bankAccount = bankAccount;
        this.bankId = bankId;
        this.subCode = subCode;
        this.statusResponse = statusResponse;
        this.content = content;
        this.mid = mid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public @Size(min = 1, max = 15) String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(@Size(min = 1, max = 15) String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public boolean getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(boolean statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
