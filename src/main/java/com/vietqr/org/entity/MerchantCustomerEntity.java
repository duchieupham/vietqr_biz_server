package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "merchantCustomer")
public class MerchantCustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "mid", nullable = false)
    private String mid;
    @Column(name = "tid", nullable = false)
    private String tid;
    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "phoneNo", nullable = false)
    private String phoneNo;
    @Column(name = "staffId", nullable = false)
    private String staffId;
    @Column(name = "status", nullable = false)
    private boolean status;
    @Column(name = "timeCreate", nullable = false)
    private long timeCreate;
    @Column(name = "data", nullable = false)
    private String data;

    public MerchantCustomerEntity() {
    }

    public MerchantCustomerEntity(String id, String mid, String tid, String userId, String phoneNo, String staffId, boolean status, long timeCreate, String data) {
        this.id = id;
        this.mid = mid;
        this.tid = tid;
        this.userId = userId;
        this.phoneNo = phoneNo;
        this.staffId = staffId;
        this.status = status;
        this.timeCreate = timeCreate;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
