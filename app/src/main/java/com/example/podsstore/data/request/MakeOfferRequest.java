package com.example.podsstore.data.request;

import com.google.gson.annotations.SerializedName;

public class MakeOfferRequest {

    @SerializedName("productId")
    private Long productid;

    @SerializedName("actualAmount")
    private String actualamount;

    @SerializedName("offerAmount")
    private String offeramount;

    @SerializedName("amountPerUnit")
    private Integer amountperunit;

    @SerializedName("quantityDetails")
    private Integer quantitydetails;

    @SerializedName("remark")
    private String remarks;

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getActualamount() {
        return actualamount;
    }

    public void setActualamount(String actualamount) {
        this.actualamount = actualamount;
    }

    public String getOfferamount() {
        return offeramount;
    }

    public void setOfferamount(String offeramount) {
        this.offeramount = offeramount;
    }

    public Integer getAmountperunit() {
        return amountperunit;
    }

    public void setAmountperunit(Integer amountperunit) {
        this.amountperunit = amountperunit;
    }

    public Integer getQuantitydetails() {
        return quantitydetails;
    }

    public void setQuantitydetails(Integer quantitydetails) {
        this.quantitydetails = quantitydetails;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "MakeOfferRequest{" +
                "productid=" + productid +
                ", actualamount=" + actualamount +
                ", offeramount=" + offeramount +
                ", amountperunit=" + amountperunit +
                ", quantitydetails=" + quantitydetails +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
