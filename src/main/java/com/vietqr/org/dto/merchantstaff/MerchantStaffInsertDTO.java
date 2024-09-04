package com.vietqr.org.dto.merchantstaff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MerchantStaffInsertDTO {
    @NotNull
    @NotBlank
    private String mid;

    @NotNull
    @NotBlank
    private String tid;

    @NotNull
    @NotEmpty
    @NotBlank
    private String userId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String phoneNo;

    @NotNull
    private List<String> permissionGroupId;

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

    public MerchantStaffInsertDTO(String mid, String tid, String userId, String name, String phoneNo, List<String> permissionGroupId, String merchantStaffRoleId, String staffRoleName) {
        this.mid = mid;
        this.tid = tid;
        this.userId = userId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.permissionGroupId = permissionGroupId;
        this.merchantStaffRoleId = merchantStaffRoleId;
        this.staffRoleName = staffRoleName;
    }

    public @NotNull @NotBlank String getMid() {
        return mid;
    }

    public void setMid(@NotNull @NotBlank String mid) {
        this.mid = mid;
    }

    public @NotNull @NotBlank String getTid() {
        return tid;
    }

    public void setTid(@NotNull @NotBlank String tid) {
        this.tid = tid;
    }

    public @NotNull @NotEmpty @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull @NotEmpty @NotBlank String userId) {
        this.userId = userId;
    }

    public @NotNull @NotEmpty @NotBlank String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty @NotBlank String name) {
        this.name = name;
    }

    public @NotNull @NotEmpty @NotBlank String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(@NotNull @NotEmpty @NotBlank String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public @NotNull List<String> getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(@NotNull @NotEmpty @NotBlank List<String> permissionGroupId) {
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
