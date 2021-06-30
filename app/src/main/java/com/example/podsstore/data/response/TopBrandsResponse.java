package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class TopBrandsResponse {

    @SerializedName("id")
    private Long brandid;

    @SerializedName("sellerid")
    private String sellerid;

    @SerializedName("image")
    private String image;

    @SerializedName("sellername")
    private String sellername;

    @SerializedName("status")
    private String status;


    @SerializedName("adddate")
    private String adddate;

    @SerializedName("advertisename")
    private String advertisename;
    @SerializedName("priority")
    private String priority;

    public Long getBrandid() {
        return brandid;
    }

    public void setBrandid(Long brandid) {
        this.brandid = brandid;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getAdvertisename() {
        return advertisename;
    }

    public void setAdvertisename(String advertisename) {
        this.advertisename = advertisename;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TopBrandsResponse{" +
                "brandid=" + brandid +
                ", sellerid='" + sellerid + '\'' +
                ", image='" + image + '\'' +
                ", sellername='" + sellername + '\'' +
                ", status='" + status + '\'' +
                ", adddate='" + adddate + '\'' +
                ", advertisename='" + advertisename + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
