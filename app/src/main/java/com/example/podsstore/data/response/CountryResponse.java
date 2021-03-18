package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class CountryResponse {
    @SerializedName("countryId")
    private Long countryid;



    @SerializedName("countryName")
    private String countryname;

    @SerializedName("createdDate")
    private String crdate;
    @SerializedName("countryStatus")
    private String countrystatus;

    public Long getCountryid() {
        return countryid;
    }

    public void setCountryid(Long countryid) {
        this.countryid = countryid;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCrdate() {
        return crdate;
    }

    public void setCrdate(String crdate) {
        this.crdate = crdate;
    }

    public String getCountrystatus() {
        return countrystatus;
    }

    public void setCountrystatus(String countrystatus) {
        this.countrystatus = countrystatus;
    }

    @Override
    public String toString() {
        return "CountryResponse{" +
                "countryid=" + countryid +
                ", countryname='" + countryname + '\'' +
                ", crdate='" + crdate + '\'' +
                ", countrystatus='" + countrystatus + '\'' +
                '}';
    }
}
