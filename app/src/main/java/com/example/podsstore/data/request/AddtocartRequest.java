package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class AddtocartRequest {


    @SerializedName("productId")
    private String productid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("price")
    private String price;

    @SerializedName("quantity")
    private String quantity;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AddtocartRequest{" +
                "productid='" + productid + '\'' +
                ", productname='" + productname + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
