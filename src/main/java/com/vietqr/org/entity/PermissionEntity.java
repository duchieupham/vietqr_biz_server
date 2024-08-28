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
}
