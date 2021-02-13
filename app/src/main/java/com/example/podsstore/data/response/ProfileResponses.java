package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class ProfileResponses {

    @SerializedName("id")
    private String id;

    @SerializedName("userName")
    private String username;

    @SerializedName("userEmailId")
    private String useremailid;

    @SerializedName("mobileNumber")
    private String mobilenumber;

    @SerializedName("addressDetails")
    private String addressdetails;

    @SerializedName("password")
    private String password;

    @SerializedName("joinedAt")
    private String joinedat;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremailid() {
        return useremailid;
    }

    public void setUseremailid(String useremailid) {
        this.useremailid = useremailid;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAddressdetails() {
        return addressdetails;
    }

    public void setAddressdetails(String addressdetails) {
        this.addressdetails = addressdetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinedat() {
        return joinedat;
    }

    public void setJoinedat(String joinedat) {
        this.joinedat = joinedat;
    }

    @Override
    public String toString() {
        return "ProfileResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", useremailid='" + useremailid + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", addressdetails='" + addressdetails + '\'' +
                ", password='" + password + '\'' +
                ", joinedat='" + joinedat + '\'' +
                '}';
    }

}
