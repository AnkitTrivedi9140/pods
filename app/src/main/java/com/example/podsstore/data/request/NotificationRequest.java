package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class NotificationRequest {
    @SerializedName("gcmtoken")
    private String gcmtoken;

    public String getGcmtoken() {
        return gcmtoken;
    }

    public void setGcmtoken(String gcmtoken) {
        this.gcmtoken = gcmtoken;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "gcmtoken='" + gcmtoken + '\'' +
                '}';
    }
}
