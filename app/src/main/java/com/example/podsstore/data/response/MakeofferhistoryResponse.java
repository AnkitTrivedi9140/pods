package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class MakeofferhistoryResponse {
    @SerializedName("id")
    private Long id;


    @SerializedName("offerid")
    private String offerid;

    @SerializedName("productname")
    private String productname;

    @SerializedName("image")
    private String image;

    @SerializedName("buyerbidbrprice")
    private String buyerbidbrprice;

    @SerializedName("sellerprice")
    private String sellerprice;

    @SerializedName("buyerremark")
    private String buyerremark;


    @SerializedName("sellerremark")
    private String sellerremark;


    @SerializedName("quantity")
    private String quantity;

    @SerializedName("actualprice")
    private String actualprice;
    @SerializedName("userid")
    private String userid;




    @SerializedName("sellerid")
    private String sellerid;


    @SerializedName("datetime")
    private String datetime;

    @SerializedName("usertype")
    private String usertype;
    @SerializedName("status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferid() {
        return offerid;
    }

    public void setOfferid(String offerid) {
        this.offerid = offerid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBuyerbidbrprice() {
        return buyerbidbrprice;
    }

    public void setBuyerbidbrprice(String buyerbidbrprice) {
        this.buyerbidbrprice = buyerbidbrprice;
    }

    public String getSellerprice() {
        return sellerprice;
    }

    public void setSellerprice(String sellerprice) {
        this.sellerprice = sellerprice;
    }

    public String getBuyerremark() {
        return buyerremark;
    }

    public void setBuyerremark(String buyerremark) {
        this.buyerremark = buyerremark;
    }

    public String getSellerremark() {
        return sellerremark;
    }

    public void setSellerremark(String sellerremark) {
        this.sellerremark = sellerremark;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getActualprice() {
        return actualprice;
    }

    public void setActualprice(String actualprice) {
        this.actualprice = actualprice;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MakeofferhistoryResponse{" +
                "id=" + id +
                ", offerid='" + offerid + '\'' +
                ", productname='" + productname + '\'' +
                ", image='" + image + '\'' +
                ", buyerbidbrprice='" + buyerbidbrprice + '\'' +
                ", sellerprice='" + sellerprice + '\'' +
                ", buyerremark='" + buyerremark + '\'' +
                ", sellerremark='" + sellerremark + '\'' +
                ", quantity='" + quantity + '\'' +
                ", actualprice='" + actualprice + '\'' +
                ", userid='" + userid + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", datetime='" + datetime + '\'' +
                ", usertype='" + usertype + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
