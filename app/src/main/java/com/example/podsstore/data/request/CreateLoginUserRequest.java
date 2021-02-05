package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class CreateLoginUserRequest {
    @SerializedName("userName")
    private String username;

    @SerializedName("userEmailId")
    private String useremail;

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
        return "LoginUserRequest{" +
                "username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                ", reenterpassword='" + reenterpassword + '\'' +
                '}';
    }
}
