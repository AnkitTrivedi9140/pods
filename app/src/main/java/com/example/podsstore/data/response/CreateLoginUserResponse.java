package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CreateLoginUserResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("userName")
    private String username;

    @SerializedName("userEmailId")
    private String email;

    @SerializedName("enterPassword")
    private String password;

    @SerializedName("reEnterPassword")
    private String reenterpassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "LoginUserResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", reenterpassword='" + reenterpassword + '\'' +
                '}';
    }
}
