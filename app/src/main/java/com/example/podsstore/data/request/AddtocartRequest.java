package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class AddtocartRequest {


    @SerializedName("productId")
    private Long productid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("price")
    private Long price;

    @SerializedName("quantity")
    private Long quantity;

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AddtocartRequest{" +
                "productid=" + productid +
                ", productname='" + productname + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
