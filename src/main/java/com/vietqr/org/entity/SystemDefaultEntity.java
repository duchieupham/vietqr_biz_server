package com.vietqr.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "system_default")
public class SystemDefaultEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private double vat;

    public SystemDefaultEntity() {
    }

    public SystemDefaultEntity(String id, double vat) {
        this.id = id;
        this.vat = vat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
}
