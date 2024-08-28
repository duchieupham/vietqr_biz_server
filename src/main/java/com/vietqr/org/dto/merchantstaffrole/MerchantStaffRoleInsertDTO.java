package com.vietqr.org.dto.merchantstaffrole;

public class MerchantStaffRoleInsertDTO {
    private String mid;
    private String tid;
    private String staffRoleName;
    private boolean isDefault;
    private String merchantRoleId;
    private String permissionGroupId;

    public MerchantStaffRoleInsertDTO() {
    }

    public MerchantStaffRoleInsertDTO(String mid, String tid, String staffRoleName, boolean isDefault, String merchantRoleId, String permissionGroupId) {
        this.mid = mid;
        this.tid = tid;
        this.staffRoleName = staffRoleName;
        this.isDefault = isDefault;
        this.merchantRoleId = merchantRoleId;
        this.permissionGroupId = permissionGroupId;
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

    public String getStaffRoleName() {
        return staffRoleName;
    }

    public void setStaffRoleName(String staffRoleName) {
        this.staffRoleName = staffRoleName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getMerchantRoleId() {
        return merchantRoleId;
    }

    public void setMerchantRoleId(String merchantRoleId) {
        this.merchantRoleId = merchantRoleId;
    }

    public String getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(String permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }
}
