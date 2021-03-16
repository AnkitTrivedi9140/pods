package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CartResponse {

    @SerializedName("cartId")
    private Long cartid;

    @SerializedName("userId")
    private Long userid;

    @SerializedName("productId")
    private Long productid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("quantity")
    private Long qty;

    @SerializedName("price")
    private Long price;

    @SerializedName("discount")
    private String discount;

    @SerializedName("totalPrice")
    private String totalprice;



    @SerializedName("itemPresentIn")
    private String itempresentin;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getItempresentin() {
        return itempresentin;
    }

    public void setItempresentin(String itempresentin) {
        this.itempresentin = itempresentin;
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
                ", productid=" + productid +
                ", productname='" + productname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", discount='" + discount + '\'' +
                ", totalprice='" + totalprice + '\'' +
                ", itempresentin='" + itempresentin + '\'' +
                ", addeddate='" + addeddate + '\'' +
                '}';
    }
}
