package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CartResponse {

    @SerializedName("carId")
    private Long cartid;

    @SerializedName("userId")
    private Long userid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("quantity")
    private Long qty;

    @SerializedName("price")
    private Long price;

    @SerializedName("addedDate")
    private String addeddate;

    public Long getCartid() {
        return cartid;
    }

    public void setCartid(Long cartid) {
        this.cartid = cartid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(String addeddate) {
        this.addeddate = addeddate;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "cartid=" + cartid +
                ", userid=" + userid +
                ", productname='" + productname + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", addeddate='" + addeddate + '\'' +
                '}';
    }
}
