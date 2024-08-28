package com.vietqr.org.entity;

import javax.persistence.*;

@Entity
@Table(name = "merchant_role")
public class MerchantRoleEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "permission_group_id", columnDefinition = "JSON", nullable = false)
    private String permissionGroupId;
}
