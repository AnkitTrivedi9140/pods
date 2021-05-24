package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class PlaceOrderNotificationRequest {

    @SerializedName("gcmtoken")
    private String gcmtoken;

    @SerializedName("orderid")
    private String orderid;


    public String getGcmtoken() {
        return gcmtoken;
    }

    public void setGcmtoken(String gcmtoken) {
        this.gcmtoken = gcmtoken;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "PlaceOrderNotificationRequest{" +
                "gcmtoken='" + gcmtoken + '\'' +
                ", orderid='" + orderid + '\'' +
                '}';
    }
}
