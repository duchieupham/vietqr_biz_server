package com.vietqr.org.entity;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private String color;

    public PermissionEntity() {
    }

    public PermissionEntity(String name, String description, int category, String color) {
        this.id = "";
        this.name = name;
        this.description = description;
        this.category = category;
        this.color = color;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
