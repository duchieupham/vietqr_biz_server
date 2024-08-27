package com.vietqr.org.dto.customer;

public class CustomerUpdateDTO {
    private String phoneNo;
    private String name;
    private String address;

    public CustomerUpdateDTO() {
    }

    public CustomerUpdateDTO(String phoneNo, String name, String address) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
