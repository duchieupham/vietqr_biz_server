package com.vietqr.org.dto.merchantstaff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MerchantStaffInsertDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private String mid;

    @NotNull
    @NotEmpty
    @NotBlank
    private String tid;

    @NotNull
    @NotEmpty
    @NotBlank
    private String userId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String data;

    @NotNull
    @NotEmpty
    @NotBlank
    private String permissionGroupId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String merchantStaffRoleId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String staffRoleName;

    public MerchantStaffInsertDTO() {
    }

    public MerchantStaffInsertDTO(String mid, String tid, String userId, String data, String permissionGroupId, String merchantStaffRoleId, String staffRoleName) {
        this.mid = mid;
        this.tid = tid;
        this.userId = userId;
        this.data = data;
        this.permissionGroupId = permissionGroupId;
        this.merchantStaffRoleId = merchantStaffRoleId;
        this.staffRoleName = staffRoleName;
    }

    public @NotNull @NotEmpty @NotBlank String getMid() {
        return mid;
    }

    public void setMid(@NotNull @NotEmpty @NotBlank String mid) {
        this.mid = mid;
    }

    public @NotNull @NotEmpty @NotBlank String getTid() {
        return tid;
    }

    public void setTid(@NotNull @NotEmpty @NotBlank String tid) {
        this.tid = tid;
    }

    public @NotNull @NotEmpty @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotEmpty @NotBlank String userId) {
        this.userId = userId;
    }

    public @NotNull @NotEmpty @NotBlank String getData() {
        return data;
    }

    public void setData(@NotNull @NotEmpty @NotBlank String data) {
        this.data = data;
    }

    public @NotNull @NotEmpty @NotBlank String getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(@NotNull @NotEmpty @NotBlank String permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public @NotNull @NotEmpty @NotBlank String getMerchantStaffRoleId() {
        return merchantStaffRoleId;
    }

    public void setMerchantStaffRoleId(@NotNull @NotEmpty @NotBlank String merchantStaffRoleId) {
        this.merchantStaffRoleId = merchantStaffRoleId;
    }

    public @NotNull @NotEmpty @NotBlank String getStaffRoleName() {
        return staffRoleName;
    }

    public void setStaffRoleName(@NotNull @NotEmpty @NotBlank String staffRoleName) {
        this.staffRoleName = staffRoleName;
    }
}
