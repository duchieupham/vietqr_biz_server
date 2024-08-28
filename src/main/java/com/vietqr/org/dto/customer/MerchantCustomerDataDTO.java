package com.vietqr.org.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantCustomerDataDTO {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "address")
    private String address;

    public MerchantCustomerDataDTO() {
        this.name = "";
        this.address = "";
    }

    public MerchantCustomerDataDTO(String name, String address) {
        this.name = name;
        this.address = address;
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
