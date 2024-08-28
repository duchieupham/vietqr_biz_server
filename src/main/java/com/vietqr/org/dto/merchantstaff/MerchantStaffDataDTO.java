package com.vietqr.org.dto.merchantstaff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantStaffDataDTO {
    @JsonProperty(value = "phoneNo")
    private String phoneNo;

    @JsonProperty(value = "name")
    private String name;

    public MerchantStaffDataDTO() {
        this.phoneNo = "";
        this.name = "";
    }

    public MerchantStaffDataDTO(String phoneNo, String name) {
        this.phoneNo = phoneNo;
        this.name = name;
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
}
