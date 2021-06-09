package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class EditMakeOfferRequest {
    @SerializedName("remark")
    private String remark;

    @SerializedName("offerAmount")
    private String offerAmount;
    @SerializedName("offerid")
    private String offerid;

    @SerializedName("quantityDetails")
    private String quantityDetails;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(String offerAmount) {
        this.offerAmount = offerAmount;
    }

    public String getOfferid() {
        return offerid;
    }

    public void setOfferid(String offerid) {
        this.offerid = offerid;
    }

    public String getQuantityDetails() {
        return quantityDetails;
    }

    public void setQuantityDetails(String quantityDetails) {
        this.quantityDetails = quantityDetails;
    }

    @Override
    public String toString() {
        return "EditMakeOfferRequest{" +
                "remark='" + remark + '\'' +
                ", offerAmount='" + offerAmount + '\'' +
                ", offerid='" + offerid + '\'' +
                ", quantityDetails='" + quantityDetails + '\'' +
                '}';
    }
}
