package com.vietqr.org.entity;

import com.vietqr.org.utils.DateTimeUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "terminal", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"}),
        @UniqueConstraint(columnNames = {"public_id"})
})
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

    /* id of terminal when owner use api-service */
    @Column(name = "public_id")
    private String publicId;

    /* terminal id if it's a sub-terminal */
    @Column(name = "ref_id")
    private String refId;

    @Column(name = "bank_id")
    private String bankId;

    @Column(name = "box_device_id")
    private String boxDeviceId;

    @Column(name = "sub")
    private boolean sub;

    @Column(name = "data1")
    private String data1;

    @Column(name = "data2")
    private String data2;

    @Column(name = "trace_transfer")
    private String traceTransfer;

    /* status of the terminal is 1 if it is active else is 0 if it is deleted */
    @Column(name = "status")
    private int status;

    @Column(name = "num_of_staff")
    private int numOfStaff;

    /* the time of update status UTC */
    @Column(name = "time_updated_status")
    private long timeUpdatedStatus;

    @Column(name = "time_created")
    private long timeCreated;

    public TerminalEntity() {
    }

    public TerminalEntity(String name, String address, String mid, String code, String bankId) {
        this.id = "";
        this.name = name.trim();
        this.address = address.trim();
        this.mid = mid.trim();
        this.code = code.trim();
        this.bankId = bankId.trim();
        this.publicId = "";
        this.boxDeviceId = "";
        this.sub = false;
        this.data1 = "";
        this.data2 = "";
        this.traceTransfer = "";
        this.refId = "";
        this.status = 1;
        this.numOfStaff = 0;
        this.timeCreated = DateTimeUtil.getNowUTC();
        this.timeUpdatedStatus = DateTimeUtil.getNowUTC();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.trim();
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId.trim();
    }

    public String getBoxDeviceId() {
        return boxDeviceId;
    }

    public void setBoxDeviceId(String boxDeviceId) {
        this.boxDeviceId = boxDeviceId.trim();
    }

    public boolean getSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1.trim();
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2.trim();
    }

    public String getTraceTransfer() {
        return traceTransfer;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumOfStaff() {
        return numOfStaff;
    }

    public void setNumOfStaff(int numOfStaff) {
        this.numOfStaff = numOfStaff;
    }

    public long getTimeUpdatedStatus() {
        return timeUpdatedStatus;
    }

    public void setTimeUpdatedStatus(long timeUpdatedStatus) {
        this.timeUpdatedStatus = timeUpdatedStatus;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }
}
