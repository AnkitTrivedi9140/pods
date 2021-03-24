package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class SubCategoryResponce {
    @SerializedName("id")
    private Long id;

    @SerializedName("productcatalogname")
    private String productname;

    @SerializedName("imgurl")
    private String productimage;

    @SerializedName("status")
    private String status;

    @SerializedName("catid")
    private Long catid;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCatid() {
        return catid;
    }

    public void setCatid(Long catid) {
        this.catid = catid;
    }

    @Override
    public String toString() {
        return "SubCategoryResponce{" +
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", status='" + status + '\'' +
                ", catid=" + catid +
                '}';
    }
}
