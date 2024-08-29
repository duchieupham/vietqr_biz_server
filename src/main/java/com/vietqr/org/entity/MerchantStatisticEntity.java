package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchantStatistic")
public class MerchantStatisticEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "type")
    private int type;
    @Column(name = "startTime")
    private long startTime;
    @Column(name = "endTime")
    private long endTime;
    @Column(name = "timeCreate")
    private long timeCreate;
    @Column(name = "mid")
    private String mid;
    @Column(name = "tid")
    private String tid;

    public MerchantStatisticEntity() {
    }

    public MerchantStatisticEntity(String id, int type, long startTime, long endTime, long timeCreate, String mid, String tid) {
        this.id = id;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeCreate = timeCreate;
        this.mid = mid;
        this.tid = tid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
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
}
