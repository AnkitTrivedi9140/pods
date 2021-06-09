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


    @SerializedName("productType")
    private String producttype;

    @SerializedName("offerflag")
    private String offerflag;

    @SerializedName("min_quantity")
    private String minqty;
    @SerializedName("itemPresentIn")
    private String itempresentin;
    @SerializedName("addedDate")
    private String addeddate;

    public CartResponse(Long cartid, Long userid, Long productid, String productname, String imageUrl, Long qty, Long price, String discount, String totalprice, String producttype, String minqty, String itempresentin, String addeddate) {
        this.cartid = cartid;
        this.userid = userid;
        this.productid = productid;
        this.productname = productname;
        this.imageUrl = imageUrl;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
        this.totalprice = totalprice;
        this.producttype = producttype;
        this.minqty = minqty;
        this.itempresentin = itempresentin;
        this.addeddate = addeddate;
    }

    public String getOfferflag() {
        return offerflag;
    }

    public void setOfferflag(String offerflag) {
        this.offerflag = offerflag;
    }

    public String getMinqty() {
        return minqty;
    }

    public void setMinqty(String minqty) {
        this.minqty = minqty;
    }

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

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
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
                ", producttype='" + producttype + '\'' +
                ", offerflag='" + offerflag + '\'' +
                ", minqty='" + minqty + '\'' +
                ", itempresentin='" + itempresentin + '\'' +
                ", addeddate='" + addeddate + '\'' +
                '}';
    }
}
