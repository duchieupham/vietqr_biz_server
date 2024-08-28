package com.vietqr.org.entity;

import com.vietqr.org.utils.DateTimeUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "box_device",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"device_code"}),
        @UniqueConstraint(columnNames = {"certificate"})
})
public class BoxDeviceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name = "device_code", nullable = false)
    private String deviceCode;

    @Column(name = "certificate", nullable = false)
    private String certificate;

    /*
    * Status
    * 0: inactive
    * 1: active
    * 2: deleted
    * */
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "time_created", nullable = false)
    private long timeCreated;

    @Column(name = "time_active", nullable = false)
    private long timeActive;

    public BoxDeviceEntity() {
        this.id = "";
        this.status = 1;
        this.timeCreated = DateTimeUtil.getNowUTC();
        this.timeActive = DateTimeUtil.getNowUTC();
    }

    public BoxDeviceEntity(String id, String deviceCode, String certificate, int status, long timeCreated, long timeActive) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.certificate = certificate;
        this.status = status;
        this.timeCreated = timeCreated;
        this.timeActive = timeActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public long getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(long timeActive) {
        this.timeActive = timeActive;
    }
}
