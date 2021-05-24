package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class PlaceOrderRequest {

    @SerializedName("orderId")
    private String orderid;

    @SerializedName("addressId")
    private String address;

    @SerializedName("productId")
    private String productid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("productImage")
    private String productimage;
    @SerializedName("quantity")
    private String quantity;


    @SerializedName("totalPrice")
    private String totalprice;

    @SerializedName("subTotal")
    private String subtotal;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

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

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "orderid='" + orderid + '\'' +
                ", address='" + address + '\'' +
                ", productid='" + productid + '\'' +
                ", productname='" + productname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", quantity='" + quantity + '\'' +
                ", totalprice='" + totalprice + '\'' +
                ", subtotal='" + subtotal + '\'' +
                '}';
    }
}
