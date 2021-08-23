package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class CustomNotificationRequest {

    @SerializedName("gcmtoken")
    private String gcmtoken;

    @SerializedName("event")
    private String event;

    public String getGcmtoken() {
        return gcmtoken;
    }

    public void setGcmtoken(String gcmtoken) {
        this.gcmtoken = gcmtoken;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "CustomNotificationRequest{" +
                "gcmtoken='" + gcmtoken + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
