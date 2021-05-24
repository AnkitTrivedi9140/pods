package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class OrderInfoResponse {
    @SerializedName("id")
    private Long id;

    @SerializedName("orderId")
    private String orderid;

    @SerializedName("userId")
    private Long userid;

    @SerializedName("productId")
    private Long productid;

    @SerializedName("productName")
    private String productname;

    @SerializedName("productType")
    private String producttype;


    @SerializedName("brandName")
    private String brandname;


    @SerializedName("productImage")
    private String productimage;

    @SerializedName("quantity")
    private Long qty;
    @SerializedName("price")
    private Long price;

    @SerializedName("orderStatus")
    private String orderstatus;



    @SerializedName("orderDate")
    private String orderdate;

    @SerializedName("current_order_status")
    private String currentorderstatus;

    @SerializedName("sellerid")
    private String sellerid;


    @SerializedName("dispatcheddate")
    private String dispatchdate;
    @SerializedName("deliverdate")
    private String delieverdate;
    @SerializedName("returndate")
    private String returndate;
    @SerializedName("canceldate")
    private String cancledate;
    @SerializedName("totalprice")
    private String totalprice;
    @SerializedName("addressDetails")
    public Address orderaddress  ;

    public Address getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(Address orderaddress) {
        this.orderaddress = orderaddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getCurrentorderstatus() {
        return currentorderstatus;
    }

    public void setCurrentorderstatus(String currentorderstatus) {
        this.currentorderstatus = currentorderstatus;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getDispatchdate() {
        return dispatchdate;
    }

    public void setDispatchdate(String dispatchdate) {
        this.dispatchdate = dispatchdate;
    }

    public String getDelieverdate() {
        return delieverdate;
    }

    public void setDelieverdate(String delieverdate) {
        this.delieverdate = delieverdate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getCancledate() {
        return cancledate;
    }

    public void setCancledate(String cancledate) {
        this.cancledate = cancledate;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "OrderInfoResponse{" +
                "id=" + id +
                ", orderid='" + orderid + '\'' +
                ", userid=" + userid +
                ", productid=" + productid +
                ", productname='" + productname + '\'' +
                ", producttype='" + producttype + '\'' +
                ", brandname='" + brandname + '\'' +
                ", productimage='" + productimage + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", orderstatus='" + orderstatus + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", currentorderstatus='" + currentorderstatus + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", dispatchdate='" + dispatchdate + '\'' +
                ", delieverdate='" + delieverdate + '\'' +
                ", returndate='" + returndate + '\'' +
                ", cancledate='" + cancledate + '\'' +
                ", totalprice='" + totalprice + '\'' +

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
