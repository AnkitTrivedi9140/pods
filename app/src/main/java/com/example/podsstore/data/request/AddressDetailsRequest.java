package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class AddressDetailsRequest {

    @SerializedName("addressLine1")
    private String address1;

    @SerializedName("addressLine2")
    private String address2;

    @SerializedName("addressLine3")
    private String address3;

    @SerializedName("zipCode")
    private String zipcode;

    @SerializedName("country")
    private String country;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressDetailsRequest{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
