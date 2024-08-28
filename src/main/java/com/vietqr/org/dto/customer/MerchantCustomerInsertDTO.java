package com.vietqr.org.dto.customer;

public class MerchantCustomerInsertDTO {
    private String mid;
    private String tid;
    private String userId;
    private String phoneNo;
    private String staffId;
    private String name;
    private String address;

    public MerchantCustomerInsertDTO() {
    }

    public MerchantCustomerInsertDTO(String mid, String tid, String userId, String phoneNo, String staffId, String name, String address) {
        this.mid = mid;
        this.tid = tid;
        this.userId = userId;
        this.phoneNo = phoneNo;
        this.staffId = staffId;
        this.name = name;
        this.address = address;
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
}
