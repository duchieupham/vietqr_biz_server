package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchantExport")
public class MerchantExportEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "fileId")
    private String fileId;
    @Column(name = "mid")
    private String mid;
    @Column(name = "tid")
    private String tid;
    @Column(name = "staffId")
    private String staffId;
    @Column(name = "userId")
    private String userId;
    @Column(name = "timeCreate")
    private long timeCreate;
    @Column(name = "type")
    private int type;
    @Column(name = "data")
    private String data;

    public MerchantExportEntity() {
    }

    public MerchantExportEntity(String id, String fileId, String mid, String tid, String staffId, String userId, long timeCreate, int type, String data) {
        this.id = id;
        this.fileId = fileId;
        this.mid = mid;
        this.tid = tid;
        this.staffId = staffId;
        this.userId = userId;
        this.timeCreate = timeCreate;
        this.type = type;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
