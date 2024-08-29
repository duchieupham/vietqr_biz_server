package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchantSupport")
public class MerchantSupportEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "mid")
    private String mid;
    @Column(name = "tid")
    private String tid;
    @Column(name = "type")
    private int type;
    @Column(name = "data")
    private String data;
    @Column(name = "description")
    private String description;
    @Column(name = "timeCreate")
    private long timeCreate;

    public MerchantSupportEntity() {
    }

    public MerchantSupportEntity(String id, String mid, String tid, int type, String data, String description, long timeCreate) {
        this.id = id;
        this.mid = mid;
        this.tid = tid;
        this.type = type;
        this.data = data;
        this.description = description;
        this.timeCreate = timeCreate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }
}
