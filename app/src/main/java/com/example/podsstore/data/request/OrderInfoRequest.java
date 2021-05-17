package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class OrderInfoRequest {

    @SerializedName("orderId")
    private String orderid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
