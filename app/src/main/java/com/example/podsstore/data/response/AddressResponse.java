package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class AddressResponse {

    @SerializedName("addressId")
    private Long addressid;

    @SerializedName("userAddressId")
    private Long useraddserrid;

    @SerializedName("userAddressLine1")
    private String useraddressline1;

    @SerializedName("userAddressLine2")
    private String useraddressline2;

    @SerializedName("userAddressLine3")
    private String useraddressline3;

    @SerializedName("userZipCode")
    private String userzipcode;

    @SerializedName("userCountry")
    private String usercountry;

    @SerializedName("updatedAt")
    private String updateat;

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public Long getUseraddserrid() {
        return useraddserrid;
    }

    public void setUseraddserrid(Long useraddserrid) {
        this.useraddserrid = useraddserrid;
    }

    public String getUseraddressline1() {
        return useraddressline1;
    }

    public void setUseraddressline1(String useraddressline1) {
        this.useraddressline1 = useraddressline1;
    }

    public String getUseraddressline2() {
        return useraddressline2;
    }

    public void setUseraddressline2(String useraddressline2) {
        this.useraddressline2 = useraddressline2;
    }

    public String getUseraddressline3() {
        return useraddressline3;
    }

    public void setUseraddressline3(String useraddressline3) {
        this.useraddressline3 = useraddressline3;
    }

    public String getUserzipcode() {
        return userzipcode;
    }

    public void setUserzipcode(String userzipcode) {
        this.userzipcode = userzipcode;
    }

    public String getUsercountry() {
        return usercountry;
    }

    public void setUsercountry(String usercountry) {
        this.usercountry = usercountry;
    }

    public String getUpdateat() {
        return updateat;
    }

    public void setUpdateat(String updateat) {
        this.updateat = updateat;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "addressid=" + addressid +
                ", useraddserrid=" + useraddserrid +
                ", useraddressline1='" + useraddressline1 + '\'' +
                ", useraddressline2='" + useraddressline2 + '\'' +
                ", useraddressline3='" + useraddressline3 + '\'' +
                ", userzipcode='" + userzipcode + '\'' +
                ", usercountry='" + usercountry + '\'' +
                ", updateat='" + updateat + '\'' +
                '}';
    }
}
