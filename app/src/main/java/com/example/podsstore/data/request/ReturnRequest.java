package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class ReturnRequest {

    @SerializedName("orderStatus")
    private String orderstatus;

    @SerializedName("orderId")
    private String orderid;
    @SerializedName("remark")
    private String remarks;


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "ReturnRequest{" +
                "orderstatus='" + orderstatus + '\'' +
                ", orderid='" + orderid + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
