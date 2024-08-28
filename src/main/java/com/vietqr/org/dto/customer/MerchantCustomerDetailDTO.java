package com.vietqr.org.dto.customer;

public class MerchantCustomerDetailDTO {
    private String mid;
    private String tid;
    private String userId;
    private String phoneNo;
    private String staffId;
    private long timeCreate;
    private String data;

    public MerchantCustomerDetailDTO() {
    }

    public MerchantCustomerDetailDTO(String mid, String tid, String userId, String phoneNo, String staffId, long timeCreate, String data) {
        this.mid = mid;
        this.tid = tid;
        this.userId = userId;
        this.phoneNo = phoneNo;
        this.staffId = staffId;
        this.timeCreate = timeCreate;
        this.data = data;
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
