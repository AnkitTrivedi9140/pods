package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class ProfileResponses {

    @SerializedName("id")
    private Long id;

    @SerializedName("userName")
    private String username;

    @SerializedName("userEmailId")
    private String useremailid;

    @SerializedName("mobileNumber")
    private String mobilenumber;

    @SerializedName("addressDetails")
    private String addressdetails;

    @SerializedName("password")
    private String password;
    @SerializedName("joinedAt")
    private String joinedat;
    @SerializedName("imageDetails")
    public Datum data = null;


    public class Datum {

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
                    "userimageid=" + userimageid +
                    ", userimagename='" + userimagename + '\'' +
                    ", userimageurl='" + userimageurl + '\'' +
                    ", userimagetype='" + userimagetype + '\'' +

                    ", userimagesize='" + userimagesize + '\'' +
                    ", createdat='" + createdat + '\'' +
                    ", updatedat='" + updatedat + '\'' +
                    '}';
        }
    }


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

    public String getAddressdetails() {
        return addressdetails;
    }

    public void setAddressdetails(String addressdetails) {
        this.addressdetails = addressdetails;
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

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProfileResponses{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", useremailid='" + useremailid + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", addressdetails='" + addressdetails + '\'' +
                ", password='" + password + '\'' +
                ", joinedat='" + joinedat + '\'' +
                ", data=" + data +
                '}';
    }
}
