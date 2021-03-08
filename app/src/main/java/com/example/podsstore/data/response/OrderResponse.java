package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("orderId")
    private String orderid;

    @SerializedName("userId")
    private Long userid;

    @SerializedName("productId")
    private Long productid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("productImage")
    private String productimage;

    @SerializedName("quantity")
    private Long qty;
    @SerializedName("price")
    private Long price;

    @SerializedName("orderStatus")
    private String totalprice;



    @SerializedName("orderDate")
    private String itempresentin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
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

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", orderid='" + orderid + '\'' +
                ", userid=" + userid +
                ", productid=" + productid +
                ", productname='" + productname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", totalprice='" + totalprice + '\'' +
                ", itempresentin='" + itempresentin + '\'' +
                '}';
    }
}
