package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CreateLoginUserResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("statusCode")
    private String statuscode;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CreateLoginUserResponse{" +
                "status='" + status + '\'' +
                ", statuscode='" + statuscode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
