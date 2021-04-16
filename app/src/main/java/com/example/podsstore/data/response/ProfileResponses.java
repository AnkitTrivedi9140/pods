package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponses {

    @SerializedName("id")
    private Long id;

    @SerializedName("userName")
    private String username;

    @SerializedName("userEmailId")
    private String useremailid;

    @SerializedName("mobileNumber")
    private String mobilenumber;


    @SerializedName("password")
    private String password;
    @SerializedName("joinedAt")
    private String joinedat;

    @SerializedName("addressDetails")
    public Address address ;
    @SerializedName("imageDetails")
    public Datum data  ;

    @SerializedName("countryName")
    public CountryName countryname  ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremailid() {
        return useremailid;
    }

    public void setUseremailid(String useremailid) {
        this.useremailid = useremailid;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinedat() {
        return joinedat;
    }

    public void setJoinedat(String joinedat) {
        this.joinedat = joinedat;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }

    public CountryName getCountryname() {
        return countryname;
    }

    public void setCountryname(CountryName countryname) {
        this.countryname = countryname;
    }

    @Override
    public String toString() {
        return "ProfileResponses{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", useremailid='" + useremailid + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", password='" + password + '\'' +
                ", joinedat='" + joinedat + '\'' +
                ", address=" + address +
                ", data=" + data +
                '}';
    }

    public class Datum {
        @SerializedName("imageId")
        public Long imageid;
        @SerializedName("userImageId")
        public Long userimageid;
        @SerializedName("userImageName")
        public String userimagename;
        @SerializedName("userImageUrl")
        public String userimageurl;
        @SerializedName("userImageType")
        public String userimagetype;

        @SerializedName("userImageSize")
        public String userimagesize;
        @SerializedName("createdAt")
        public String createdat;
        @SerializedName("updatedAt")
        public String updatedat;

        public Long getImageid() {
            return imageid;
        }

        public void setImageid(Long imageid) {
            this.imageid = imageid;
        }

        public Long getUserimageid() {
            return userimageid;
        }

        public void setUserimageid(Long userimageid) {
            this.userimageid = userimageid;
        }

        public String getUserimagename() {
            return userimagename;
        }

        public void setUserimagename(String userimagename) {
            this.userimagename = userimagename;
        }

        public String getUserimageurl() {
            return userimageurl;
        }

        public void setUserimageurl(String userimageurl) {
            this.userimageurl = userimageurl;
        }

        public String getUserimagetype() {
            return userimagetype;
        }

        public void setUserimagetype(String userimagetype) {
            this.userimagetype = userimagetype;
        }

        public String getUserimagesize() {
            return userimagesize;
        }

        public void setUserimagesize(String userimagesize) {
            this.userimagesize = userimagesize;
        }

        public String getCreatedat() {
            return createdat;
        }

        public void setCreatedat(String createdat) {
            this.createdat = createdat;
        }

        public String getUpdatedat() {
            return updatedat;
        }

        public void setUpdatedat(String updatedat) {
            this.updatedat = updatedat;
        }

        @Override
        public String toString() {
            return "Datum{" +
                    "imageid=" + imageid +
                    ", userimageid=" + userimageid +
                    ", userimagename='" + userimagename + '\'' +
                    ", userimageurl='" + userimageurl + '\'' +
                    ", userimagetype='" + userimagetype + '\'' +
                    ", userimagesize='" + userimagesize + '\'' +
                    ", createdat='" + createdat + '\'' +
                    ", updatedat='" + updatedat + '\'' +
                    '}';
        }
    }

    public class Address {
        @SerializedName("addressId")
        public Long addressid;
        @SerializedName("userAddressId")
        public Long useraddressid;
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

        @Override
        public String toString() {
            return "Address{" +
                    "addressid=" + addressid +
                    ", useraddressid=" + useraddressid +
                    ", addressline1='" + addressline1 + '\'' +
                    ", addressline2='" + addressline2 + '\'' +
                    ", addressline3='" + addressline3 + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", usercountry='" + usercountry + '\'' +
                    ", updatedat='" + updatedat + '\'' +
                    '}';
        }

    }

    public class CountryName{
        @SerializedName("countryId")
        public Long countryid;

        @SerializedName("countryName")
        public String countryname;
        @SerializedName("createdDate")
        public String countrycrdate;
        @SerializedName("countryStatus")
        public String countrystatus;

        @SerializedName("imageUrl")
        public String imageurl;
        @SerializedName("countryCode")
        public String countrycode;


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

        public String getCountrycrdate() {
            return countrycrdate;
        }

        public void setCountrycrdate(String countrycrdate) {
            this.countrycrdate = countrycrdate;
        }

        public String getCountrystatus() {
            return countrystatus;
        }

        public void setCountrystatus(String countrystatus) {
            this.countrystatus = countrystatus;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getCountrycode() {
            return countrycode;
        }

        public void setCountrycode(String countrycode) {
            this.countrycode = countrycode;
        }

        @Override
        public String toString() {
            return "CompanyName{" +
                    "countryid=" + countryid +
                    ", countryname='" + countryname + '\'' +
                    ", countrycrdate='" + countrycrdate + '\'' +
                    ", countrystatus='" + countrystatus + '\'' +
                    ", imageurl='" + imageurl + '\'' +
                    ", countrycode='" + countrycode + '\'' +
                    '}';
        }
    }
}
