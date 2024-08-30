package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "merchant")
public class MerchantEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "nationalId")
    private int nationalId;
    @Column(name = "vso")
    private String vso;
    @Column(name = "email")
    private String email;
    @Column(name = "serviceType")
    private String serviceType;
    @Column(name = "status")
    private boolean status;
    @Column(name = "timeUpdatedStatus")
    private long timeUpdatedStatus;
    @Column(name = "userId")
    private String userId;
    @Column(name = "timeCreate")
    private long timeCreate;
    @Column(name = "publishId")
    private String publishId;
    @Column(name = "master")
    private boolean master;
    @Column(name = "refId")
    private String refId;
    @Column(name = "businessSector")
    private String businessSector;
    @Column(name = "businessType")
    private int businessType;

    public MerchantEntity() {
    }

    public MerchantEntity(String id, String name, String fullName, String address, int nationalId, String vso, String email, String serviceType, boolean status, long timeUpdatedStatus, String userId, long timeCreate, String publishId, boolean master, String refId, String businessSector, int businessType) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.nationalId = nationalId;
        this.vso = vso;
        this.email = email;
        this.serviceType = serviceType;
        this.status = status;
        this.timeUpdatedStatus = timeUpdatedStatus;
        this.userId = userId;
        this.timeCreate = timeCreate;
        this.publishId = publishId;
        this.master = master;
        this.refId = refId;
        this.businessSector = businessSector;
        this.businessType = businessType;
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

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getVso() {
        return vso;
    }

    public void setVso(String vso) {
        this.vso = vso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public long getTimeUpdatedStatus() {
        return timeUpdatedStatus;
    }

    public void setTimeUpdatedStatus(long timeUpdatedStatus) {
        this.timeUpdatedStatus = timeUpdatedStatus;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }
}
