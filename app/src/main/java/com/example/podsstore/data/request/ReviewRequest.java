package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class ReviewRequest {

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("rating")
    private String rating;

    @SerializedName("remark")
    private String remark;
    @SerializedName("productId")
    private String productid;

    public String getFullname() {
        return fullname;
    }


    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", rating='" + rating + '\'' +
                ", remark='" + remark + '\'' +
                ", productid='" + productid + '\'' +
                '}';
    }
}
