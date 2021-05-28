package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class MakeOfferResponse {

    @SerializedName("makeOfferId")
    private Long makeofferis;

    @SerializedName("sellerId")
    private String selleris;

    @SerializedName("buyerId")
    private String buyerid;

    @SerializedName("productId")
    private String productid;

    @SerializedName("userType")
    private String usertype;

    @SerializedName("actualAmount")
    private String actualamount;


    @SerializedName("firstOfferAmountPerUnit")
    private String firstofferamountperunit;


    @SerializedName("secondOfferAmountPerUnit")
    private String secondofferamountperunit;

    @SerializedName("finalOfferAmountPerUnit")
    private String finalofferamountperunit;
    @SerializedName("quantityDetails")
    private String quantitydetails;

    @SerializedName("firstBidAmount")
    private String firstbidamount;



    @SerializedName("sellerFirstBidAmount")
    private String sellerfirstbidamount;

    @SerializedName("secondBidAmount")
    private String secondbidamount;

    @SerializedName("sellerSecondBidAmount")
    private String sellersecondbidamount;


    @SerializedName("finalBidAmount")
    private String finalbidamount;
    @SerializedName("sellerFinalBidAmount")
    private String sellerfinalbidamount;
    @SerializedName("remark")
    private String remarks;
    @SerializedName("offerCreatedAt")
    private String offercreatedat;
    @SerializedName("productname")
    private String productname;
    @SerializedName("addressDetails")
    public String offeraddress  ;

    public Long getMakeofferis() {
        return makeofferis;
    }

    public void setMakeofferis(Long makeofferis) {
        this.makeofferis = makeofferis;
    }

    public String getSelleris() {
        return selleris;
    }

    public void setSelleris(String selleris) {
        this.selleris = selleris;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getActualamount() {
        return actualamount;
    }

    public void setActualamount(String actualamount) {
        this.actualamount = actualamount;
    }

    public String getFirstofferamountperunit() {
        return firstofferamountperunit;
    }

    public void setFirstofferamountperunit(String firstofferamountperunit) {
        this.firstofferamountperunit = firstofferamountperunit;
    }

    public String getSecondofferamountperunit() {
        return secondofferamountperunit;
    }

    public void setSecondofferamountperunit(String secondofferamountperunit) {
        this.secondofferamountperunit = secondofferamountperunit;
    }

    public String getFinalofferamountperunit() {
        return finalofferamountperunit;
    }

    public void setFinalofferamountperunit(String finalofferamountperunit) {
        this.finalofferamountperunit = finalofferamountperunit;
    }

    public String getQuantitydetails() {
        return quantitydetails;
    }

    public void setQuantitydetails(String quantitydetails) {
        this.quantitydetails = quantitydetails;
    }

    public String getFirstbidamount() {
        return firstbidamount;
    }

    public void setFirstbidamount(String firstbidamount) {
        this.firstbidamount = firstbidamount;
    }

    public String getSellerfirstbidamount() {
        return sellerfirstbidamount;
    }

    public void setSellerfirstbidamount(String sellerfirstbidamount) {
        this.sellerfirstbidamount = sellerfirstbidamount;
    }

    public String getSecondbidamount() {
        return secondbidamount;
    }

    public void setSecondbidamount(String secondbidamount) {
        this.secondbidamount = secondbidamount;
    }

    public String getSellersecondbidamount() {
        return sellersecondbidamount;
    }

    public void setSellersecondbidamount(String sellersecondbidamount) {
        this.sellersecondbidamount = sellersecondbidamount;
    }

    public String getFinalbidamount() {
        return finalbidamount;
    }

    public void setFinalbidamount(String finalbidamount) {
        this.finalbidamount = finalbidamount;
    }

    public String getSellerfinalbidamount() {
        return sellerfinalbidamount;
    }

    public void setSellerfinalbidamount(String sellerfinalbidamount) {
        this.sellerfinalbidamount = sellerfinalbidamount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOffercreatedat() {
        return offercreatedat;
    }

    public void setOffercreatedat(String offercreatedat) {
        this.offercreatedat = offercreatedat;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getOfferaddress() {
        return offeraddress;
    }

    public void setOfferaddress(String offeraddress) {
        this.offeraddress = offeraddress;
    }

    @Override
    public String toString() {
        return "MakeOfferResponse{" +
                "makeofferis=" + makeofferis +
                ", selleris='" + selleris + '\'' +
                ", buyerid='" + buyerid + '\'' +
                ", productid='" + productid + '\'' +
                ", usertype='" + usertype + '\'' +
                ", actualamount='" + actualamount + '\'' +
                ", firstofferamountperunit='" + firstofferamountperunit + '\'' +
                ", secondofferamountperunit='" + secondofferamountperunit + '\'' +
                ", finalofferamountperunit='" + finalofferamountperunit + '\'' +
                ", quantitydetails='" + quantitydetails + '\'' +
                ", firstbidamount='" + firstbidamount + '\'' +
                ", sellerfirstbidamount='" + sellerfirstbidamount + '\'' +
                ", secondbidamount='" + secondbidamount + '\'' +
                ", sellersecondbidamount='" + sellersecondbidamount + '\'' +
                ", finalbidamount='" + finalbidamount + '\'' +
                ", sellerfinalbidamount='" + sellerfinalbidamount + '\'' +
                ", remarks='" + remarks + '\'' +
                ", offercreatedat='" + offercreatedat + '\'' +
                ", productname='" + productname + '\'' +
                ", offerdetails=" + offeraddress +
                '}';
    }

    public class Address {
        @SerializedName("addressId")
        public Long addressid;
        @SerializedName("userAddressId")
        public Long useraddressid;


        @SerializedName("userName")
        public String username;
        @SerializedName("userAddressLine1")
        public String addressline1;
        @SerializedName("userAddressLine2")
        public String addressline2;
        @SerializedName("userAddressLine3")
        public String addressline3;

        @SerializedName("userZipCode")
        public String zipcode;
        @SerializedName("userCountry")
        public String usercountry;
        @SerializedName("updatedAt")
        public String updatedat;
        @SerializedName("mobile")
        public String mobile;
        @SerializedName("state")
        public String state;

        public Long getAddressid() {
            return addressid;
        }

        public void setAddressid(Long addressid) {
            this.addressid = addressid;
        }

        public Long getUseraddressid() {
            return useraddressid;
        }

        public void setUseraddressid(Long useraddressid) {
            this.useraddressid = useraddressid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddressline1() {
            return addressline1;
        }

        public void setAddressline1(String addressline1) {
            this.addressline1 = addressline1;
        }

        public String getAddressline2() {
            return addressline2;
        }

        public void setAddressline2(String addressline2) {
            this.addressline2 = addressline2;
        }

        public String getAddressline3() {
            return addressline3;
        }

        public void setAddressline3(String addressline3) {
            this.addressline3 = addressline3;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getUsercountry() {
            return usercountry;
        }

        public void setUsercountry(String usercountry) {
            this.usercountry = usercountry;
        }

        public String getUpdatedat() {
            return updatedat;
        }

        public void setUpdatedat(String updatedat) {
            this.updatedat = updatedat;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "addressid=" + addressid +
                    ", useraddressid=" + useraddressid +
                    ", username='" + username + '\'' +
                    ", addressline1='" + addressline1 + '\'' +
                    ", addressline2='" + addressline2 + '\'' +
                    ", addressline3='" + addressline3 + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", usercountry='" + usercountry + '\'' +
                    ", updatedat='" + updatedat + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }

}
