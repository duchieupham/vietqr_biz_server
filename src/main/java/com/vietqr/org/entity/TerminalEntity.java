package com.vietqr.org.entity;

import com.vietqr.org.utils.DateTimeUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "terminal")
public class TerminalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /* id of the terminal */
    @Id
    @Column(name = "id")
    private String id;

    /* name of the terminal */
    @Column(name = "name")
    private String name;

    /* address of the terminal */
    @Column(name = "address")
    private String address;

    /* FK: id of the merchant that the terminal connected with */
    @Column(name = "mid")
    private String mid;

    /* code of the terminal */
    @Column(name = "code")
    private String code;

    /* code of the terminal created by owner */
    @Column(name = "raw_code")
    private String rawCode;

    /* id of terminal when owner use api-service */
    @Column(name = "public_id")
    private String publicId;

    /* terminal id if it's a sub-terminal */
    @Column(name = "ref_id")
    private String refId;

    @Column(name = "bank_id")
    private String bankId;

    @Column(name = "qr_box_id")
    private String qrBoxId;

    @Column(name = "sub")
    private boolean sub;

    @Column(name = "data1")
    private String data1;

    @Column(name = "data2")
    private String data2;

    @Column(name = "trace_transfer")
    private String traceTransfer;

    /* status of the terminal is true if it is active else is fault if it is deleted */
    @Column(name = "status")
    private boolean status;

    /* status of the terminal is true if it is active else is fault if it is deleted */
    @Column(name = "num_of_staff")
    private byte numOfStaff;

    @Column(name = "time_created")
    private long timeCreated;

    public TerminalEntity() {
    }

    public TerminalEntity(String name, String address, String mid, String code, String rawCode, String publicId, String refId, String bankId, String qrBoxId, boolean sub, String data1, String data2, String traceTransfer) {
        this.name = name;
        this.address = address;
        this.mid = mid;
        this.code = code;
        this.rawCode = rawCode;
        this.publicId = publicId;
        this.refId = refId;
        this.bankId = bankId;
        this.qrBoxId = qrBoxId;
        this.sub = sub;
        this.data1 = data1;
        this.data2 = data2;
        this.traceTransfer = traceTransfer;
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.numOfStaff = 0;
        long createdAt = DateTimeUtil.getNowUTC();
        this.timeCreated = createdAt;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRawCode() {
        return rawCode;
    }

    public void setRawCode(String rawCode) {
        this.rawCode = rawCode;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getQrBoxId() {
        return qrBoxId;
    }

    public void setQrBoxId(String qrBoxId) {
        this.qrBoxId = qrBoxId;
    }

    public boolean isSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getTraceTransfer() {
        return traceTransfer;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte getNumOfStaff() {
        return numOfStaff;
    }

    public void setNumOfStaff(byte numOfStaff) {
        this.numOfStaff = numOfStaff;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }
}
