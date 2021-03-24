package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    @SerializedName("newPassword")
    private String newpassword;

    @SerializedName("confirmPassword")
    private String confirmpassword;

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "newpassword='" + newpassword + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                '}';
    }
}
