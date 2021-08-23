package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class AddUserHistoryNotiRequest {
    @SerializedName("emailid")
    private String emailid;

    @SerializedName("gcm_token")
    private String gcmtoken;


    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getGcmtoken() {
        return gcmtoken;
    }

    public void setGcmtoken(String gcmtoken) {
        this.gcmtoken = gcmtoken;
    }

    @Override
    public String toString() {
        return "AddUserHistoryNotiRequest{" +
                "emailid='" + emailid + '\'' +
                ", gcmtoken='" + gcmtoken + '\'' +
                '}';
    }
}
