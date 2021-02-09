package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class BusinessCatResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("productname")
    private String productname;

    @SerializedName("productimage")
    private String productimage;

    @SerializedName("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BusinessCatResponse{" +
                "id='" + id + '\'' +
                ", productname='" + productname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
