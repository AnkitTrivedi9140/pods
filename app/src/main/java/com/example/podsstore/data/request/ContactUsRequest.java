package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class ContactUsRequest {
    @SerializedName("contactUsName")
    private String contactUsName;

    @SerializedName("contactUsNumber")
    private String contactUsNumber;

    @SerializedName("contactUsEmail")
    private String contactUsEmail;

    @SerializedName("contactUsConcern")
    private String contactUsConcern;

    @SerializedName("contactUsRemark")
    private String contactUsRemark;

    @SerializedName("contactUsBusiness")
    private String contactUsBusiness;


    public String getContactUsName() {
        return contactUsName;
    }

    public void setContactUsName(String contactUsName) {
        this.contactUsName = contactUsName;
    }

    public String getContactUsNumber() {
        return contactUsNumber;
    }

    public void setContactUsNumber(String contactUsNumber) {
        this.contactUsNumber = contactUsNumber;
    }

    public String getContactUsEmail() {
        return contactUsEmail;
    }

    public void setContactUsEmail(String contactUsEmail) {
        this.contactUsEmail = contactUsEmail;
    }

    public String getContactUsConcern() {
        return contactUsConcern;
    }

    public void setContactUsConcern(String contactUsConcern) {
        this.contactUsConcern = contactUsConcern;
    }

    public String getContactUsRemark() {
        return contactUsRemark;
    }

    public void setContactUsRemark(String contactUsRemark) {
        this.contactUsRemark = contactUsRemark;
    }

    public String getContactUsBusiness() {
        return contactUsBusiness;
    }

    public void setContactUsBusiness(String contactUsBusiness) {
        this.contactUsBusiness = contactUsBusiness;
    }

    @Override
    public String toString() {
        return "ContactUsRequest{" +
                "contactUsName='" + contactUsName + '\'' +
                ", contactUsNumber='" + contactUsNumber + '\'' +
                ", contactUsEmail='" + contactUsEmail + '\'' +
                ", contactUsConcern='" + contactUsConcern + '\'' +
                ", contactUsRemark='" + contactUsRemark + '\'' +
                ", contactUsBusiness='" + contactUsBusiness + '\'' +
                '}';
    }
}
