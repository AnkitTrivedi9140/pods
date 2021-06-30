package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CheckoutResponse {

    @SerializedName("ephemeralKey")
    private String ephemeralKey;

    @SerializedName("paymentIntent")
    private String paymentIntent;

    @SerializedName("customer")
    private String customer;

    public String getEphemeralKey() {
        return ephemeralKey;
    }

    public void setEphemeralKey(String ephemeralKey) {
        this.ephemeralKey = ephemeralKey;
    }

    public String getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CheckoutResponse{" +
                "ephemeralKey='" + ephemeralKey + '\'' +
                ", paymentIntent='" + paymentIntent + '\'' +
                ", customer='" + customer + '\'' +
                '}';
    }
}
