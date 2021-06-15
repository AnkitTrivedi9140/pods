package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class BannerResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("bannername")
    private String bannername;

    @SerializedName("bannerimgurl")
    private String bannerimgurl;

    @SerializedName("bannerdescription")
    private String bannerdescription;
    @SerializedName("status")
    private String status;

    @SerializedName("addeddate")
    private String addeddate;

    @SerializedName("redirecturl")
    private String redirecturl;

    @SerializedName("priority")
    private String priority;
    @SerializedName("androidmove")
    private String androidmove;

    public String getAndroidmove() {
        return androidmove;
    }

    public void setAndroidmove(String androidmove) {
        this.androidmove = androidmove;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBannername() {
        return bannername;
    }

    public void setBannername(String bannername) {
        this.bannername = bannername;
    }

    public String getBannerimgurl() {
        return bannerimgurl;
    }

    public void setBannerimgurl(String bannerimgurl) {
        this.bannerimgurl = bannerimgurl;
    }

    public String getBannerdescription() {
        return bannerdescription;
    }

    public void setBannerdescription(String bannerdescription) {
        this.bannerdescription = bannerdescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(String addeddate) {
        this.addeddate = addeddate;
    }

    public String getRedirecturl() {
        return redirecturl;
    }

    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "BannerResponse{" +
                "id=" + id +
                ", bannername='" + bannername + '\'' +
                ", bannerimgurl='" + bannerimgurl + '\'' +
                ", bannerdescription='" + bannerdescription + '\'' +
                ", status='" + status + '\'' +
                ", addeddate='" + addeddate + '\'' +
                ", redirecturl='" + redirecturl + '\'' +
                ", priority='" + priority + '\'' +
                ", androidmove='" + androidmove + '\'' +
                '}';
    }
}
