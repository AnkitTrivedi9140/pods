package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CreateLoginUserResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("statusCode")
    private Long statuscode;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Long statuscode) {
        this.statuscode = statuscode;
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
