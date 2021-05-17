package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class OrderInfoResponse {
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

    @SerializedName("productType")
    private String producttype;


    @SerializedName("brandName")
    private String brandname;


    @SerializedName("productImage")
    private String productimage;

    @SerializedName("quantity")
    private Long qty;
    @SerializedName("price")
    private Long price;

    @SerializedName("orderStatus")
    private String orderstatus;



    @SerializedName("orderDate")
    private String orderdate;

    @SerializedName("current_order_status")
    private String currentorderstatus;

    @SerializedName("sellerid")
    private String sellerid;


    @SerializedName("dispatcheddate")
    private String dispatchdate;
    @SerializedName("deliverdate")
    private String delieverdate;
    @SerializedName("returndate")
    private String returndate;
    @SerializedName("canceldate")
    private String cancledate;
    @SerializedName("totalprice")
    private String totalprice;
    @SerializedName("addressDetails")
    private String orderaddress;

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

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
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

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getCurrentorderstatus() {
        return currentorderstatus;
    }

    public void setCurrentorderstatus(String currentorderstatus) {
        this.currentorderstatus = currentorderstatus;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getDispatchdate() {
        return dispatchdate;
    }

    public void setDispatchdate(String dispatchdate) {
        this.dispatchdate = dispatchdate;
    }

    public String getDelieverdate() {
        return delieverdate;
    }

    public void setDelieverdate(String delieverdate) {
        this.delieverdate = delieverdate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getCancledate() {
        return cancledate;
    }

    public void setCancledate(String cancledate) {
        this.cancledate = cancledate;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(String orderaddress) {
        this.orderaddress = orderaddress;
    }

    @Override
    public String toString() {
        return "OrderInfoResponse{" +
                "id=" + id +
                ", orderid='" + orderid + '\'' +
                ", userid=" + userid +
                ", productid=" + productid +
                ", productname='" + productname + '\'' +
                ", producttype='" + producttype + '\'' +
                ", brandname='" + brandname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", orderstatus='" + orderstatus + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", currentorderstatus='" + currentorderstatus + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", dispatchdate='" + dispatchdate + '\'' +
                ", delieverdate='" + delieverdate + '\'' +
                ", returndate='" + returndate + '\'' +
                ", cancledate='" + cancledate + '\'' +
                ", totalprice='" + totalprice + '\'' +
                ", orderaddress='" + orderaddress + '\'' +
                '}';
    }
}
