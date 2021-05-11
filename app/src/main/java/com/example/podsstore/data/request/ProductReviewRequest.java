package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class ProductReviewRequest {
    @SerializedName("productId")
    private String productid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "ProductReviewRequest{" +
                "productid='" + productid + '\'' +
                '}';
    }
}
