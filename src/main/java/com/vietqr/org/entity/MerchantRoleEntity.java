package com.vietqr.org.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "merchant_role")
public class MerchantRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "permission_group_id", columnDefinition = "JSON", nullable = false)
    private String permissionGroupId;

    public MerchantRoleEntity() {
        this.id = "";
    }

    public MerchantRoleEntity(String name, String description, String permissionGroupId) {
        this.id = "";
        this.name = name;
        this.description = description;
        this.permissionGroupId = permissionGroupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(String permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }
}
