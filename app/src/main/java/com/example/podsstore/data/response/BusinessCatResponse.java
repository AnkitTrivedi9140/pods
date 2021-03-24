package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class BusinessCatResponse {
    @SerializedName("id")
    private Long id;

    @SerializedName("productcatalogname")
    private String productname;

    @SerializedName("imgurl")
    private String productimage;

    @SerializedName("priority")
    private String priority;

    @SerializedName("status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
