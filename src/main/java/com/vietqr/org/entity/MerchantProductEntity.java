package com.vietqr.org.entity;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@CrossOrigin
@Table(name = "merchant_product")
public class MerchantProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name = "img_Id", nullable = false)
    private String imgId;

    @Column(name = "category_Id", nullable = false)
    private String categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit;

//    0 = publish
//    1 = available
//    2 = disable
    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private String tid;

    public MerchantProductEntity() {
        super();
    }

    public MerchantProductEntity(String id, String imgId, String categoryId, String name, String unit, int status, String tid) {
        super();
        this.id = id;
        this.imgId = imgId;
        this.categoryId = categoryId;
        this.name = name;
        this.unit = unit;
        this.status = status;
        this.tid = tid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
