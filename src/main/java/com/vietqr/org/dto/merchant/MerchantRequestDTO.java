package com.vietqr.org.dto.merchant;

public class MerchantRequestDTO {
    private String name;
    private String fullName;
    private String address;
    private Integer nationalId;
    private String businessSector;
    private Integer businessType;

    public MerchantRequestDTO() {
    }

    public MerchantRequestDTO(String name, String fullName, String address, Integer nationalId, String businessSector, Integer businessType) {
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.nationalId = nationalId;
        this.businessSector = businessSector;
        this.businessType = businessType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}
