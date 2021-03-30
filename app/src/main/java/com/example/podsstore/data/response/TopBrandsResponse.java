package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class TopBrandsResponse {

    @SerializedName("brandId")
    private Long brandid;

    @SerializedName("brandName")
    private String brandname;

    @SerializedName("brandImageUrl")
    private String brandimage;

    @SerializedName("brandStatus")
    private String brandstatus;

    @SerializedName("createdDate")
    private String brandcrdate;

    public Long getBrandid() {
        return brandid;
    }

    public void setBrandid(Long brandid) {
        this.brandid = brandid;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getBrandimage() {
        return brandimage;
    }

    public void setBrandimage(String brandimage) {
        this.brandimage = brandimage;
    }

    public String getBrandstatus() {
        return brandstatus;
    }

    public void setBrandstatus(String brandstatus) {
        this.brandstatus = brandstatus;
    }

    public String getBrandcrdate() {
        return brandcrdate;
    }

    public void setBrandcrdate(String brandcrdate) {
        this.brandcrdate = brandcrdate;
    }

    @Override
    public String toString() {
        return "TopBrandsResponse{" +
                "brandid=" + brandid +
                ", brandname='" + brandname + '\'' +
                ", brandimage='" + brandimage + '\'' +
                ", brandstatus='" + brandstatus + '\'' +
                ", brandcrdate='" + brandcrdate + '\'' +
                '}';
    }
}
