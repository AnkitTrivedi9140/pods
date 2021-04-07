package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class CreateLoginUserRequest {
    @SerializedName("userName")
    private String username;

    @SerializedName("userEmailId")
    private String useremail;

    @SerializedName("userPhoneNumber")
    private String phoneno;

    @SerializedName("companyName")
    private String companyname;

    @SerializedName("businessType")
    private String businesstype;

    @SerializedName("userType")
    private String usertype;


    @SerializedName("enterPassword")
    private String password;

    @SerializedName("reEnterPassword")
    private String reenterpassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenterpassword() {
        return reenterpassword;
    }

    public void setReenterpassword(String reenterpassword) {
        this.reenterpassword = reenterpassword;
    }

    @Override
    public String toString() {
        return "CreateLoginUserRequest{" +
                "username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", companyname='" + companyname + '\'' +
                ", businesstype='" + businesstype + '\'' +
                ", usertype='" + usertype + '\'' +
                ", password='" + password + '\'' +
                ", reenterpassword='" + reenterpassword + '\'' +
                '}';
    }
}
