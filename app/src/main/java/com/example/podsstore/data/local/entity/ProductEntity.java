package com.example.podsstore.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_qty")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "product_id")
    private String productid;

    @ColumnInfo(name = "quantity")
    private String quantity;
    @ColumnInfo(name = "userid")
    private String userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ProductEntity(int id, String productid, String quantity, String userid) {
        this.id = id;
        this.productid = productid;
        this.quantity = quantity;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productid='" + productid + '\'' +
                ", quantity='" + quantity + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
