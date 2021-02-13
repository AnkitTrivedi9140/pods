package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class TellUsMoreResquest {

    @SerializedName("yourFullName")
    private String yourfullname;

    @SerializedName("yourMobileNumber")
    private String yourmobileno;

    @SerializedName("emailAddress")
    private String emailaddress;

    @SerializedName("yourQuery")
    private String yourquery;

    public String getYourfullname() {
        return yourfullname;
    }

    public void setYourfullname(String yourfullname) {
        this.yourfullname = yourfullname;
    }

    public String getYourmobileno() {
        return yourmobileno;
    }

    public void setYourmobileno(String yourmobileno) {
        this.yourmobileno = yourmobileno;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getYourquery() {
        return yourquery;
    }

    public void setYourquery(String yourquery) {
        this.yourquery = yourquery;
    }

    @Override
    public String toString() {
        return "TellUsMoreResquest{" +
                "yourfullname='" + yourfullname + '\'' +
                ", yourmobileno='" + yourmobileno + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", yourquery='" + yourquery + '\'' +
                '}';
    }
}
