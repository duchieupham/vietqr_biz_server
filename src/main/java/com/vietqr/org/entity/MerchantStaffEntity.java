package com.vietqr.org.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "merchant_staff")
public class MerchantStaffEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private String mid;

    @Column(nullable = false)
    private String tid;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "JSON")
    private String data;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "time_created", nullable = false)
    private long timeCreated;

    @Column(name = "time_updated", nullable = false)
    private long timeUpdated;

    @Column(name = "permission_group_id", nullable = false, columnDefinition = "JSON")
    private String permissionGroupId;

    @Column(name = "merchant_staff_role_id", nullable = false)
    private String merchantStaffRoleId;

    @Column(name = "staff_role_name", nullable = false)
    private String staffRoleName;

    public MerchantStaffEntity() {
        this.id = "";
    }

    public MerchantStaffEntity(String mid, String tid, String userId, String data, String permissionGroupId, String merchantStaffRoleId, String staffRoleName) {
        this.id = "";
        this.mid = mid;
        this.tid = tid;
        this.userId = userId;
        this.data = data;
        this.permissionGroupId = permissionGroupId;
        this.merchantStaffRoleId = merchantStaffRoleId;
        this.staffRoleName = staffRoleName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public long getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(long timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(String permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public String getMerchantStaffRoleId() {
        return merchantStaffRoleId;
    }

    public void setMerchantStaffRoleId(String merchantStaffRoleId) {
        this.merchantStaffRoleId = merchantStaffRoleId;
    }

    public String getStaffRoleName() {
        return staffRoleName;
    }

    public void setStaffRoleName(String staffRoleName) {
        this.staffRoleName = staffRoleName;
    }
}
