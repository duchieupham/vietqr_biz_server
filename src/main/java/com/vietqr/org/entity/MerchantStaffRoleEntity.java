package com.vietqr.org.entity;

import javax.persistence.*;

@Entity
@Table(name = "merchant_staff_role")
public class MerchantStaffRoleEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String mid;

    @Column(nullable = false)
    private String tid;

    @Column(name = "staff_role_name", nullable = false)
    private String staffRoleName;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @Column(name = "merchant_role_id", nullable = false)
    private String merchantRoleId;

    @Column(name = "permission_group_id", columnDefinition = "JSON", nullable = false)
    private String permissionGroupId;
}
