package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class BestSellingProductResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("productName")
    private String productname;

    @SerializedName("productImageUrl")
    private String imageurl;

    @SerializedName("productPrice")
    private String productprice;

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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    @Override
    public String toString() {
        return "BestSellingProductResponse{" +
                "id='" + id + '\'' +
                ", productname='" + productname + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", productprice='" + productprice + '\'' +
                '}';
    }
}
