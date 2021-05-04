package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class QtyRequest {
    @SerializedName("quantity")
    private String quantity;

    @SerializedName("productId")
    private String productid;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "QtyRequest{" +
                "quantity='" + quantity + '\'' +
                ", productid='" + productid + '\'' +
                '}';
    }
}
