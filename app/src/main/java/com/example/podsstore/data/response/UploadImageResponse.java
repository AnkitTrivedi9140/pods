package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {


    @SerializedName("userEmailId")
    private String useremailid;

    @SerializedName("userName")
    private String username;

    @SerializedName("uploadedDate")
    private String uploadeddate;

    @SerializedName("imageDetails")
    public ProfileResponses.Datum data = null;


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

    public String getUseremailid() {
        return useremailid;
    }

    public void setUseremailid(String useremailid) {
        this.useremailid = useremailid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUploadeddate() {
        return uploadeddate;
    }

    public void setUploadeddate(String uploadeddate) {
        this.uploadeddate = uploadeddate;
    }

    public ProfileResponses.Datum getData() {
        return data;
    }

    public void setData(ProfileResponses.Datum data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UploadImageResponse{" +
                "useremailid='" + useremailid + '\'' +
                ", username='" + username + '\'' +
                ", uploadeddate='" + uploadeddate + '\'' +
                ", data=" + data +
                '}';
    }
}
