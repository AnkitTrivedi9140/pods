package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class QtyResponse {

    @SerializedName("statusCode")
    private String statuscode;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

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

    @Override
    public String toString() {
        return "QtyResponse{" +
                "statuscode='" + statuscode + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
