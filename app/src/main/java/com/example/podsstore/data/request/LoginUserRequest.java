package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class LoginUserRequest {
//    @SerializedName("userName")
//    private String username;

    @SerializedName("emailId")
    private String useremail;

    @SerializedName("password")
    private String password;

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

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
