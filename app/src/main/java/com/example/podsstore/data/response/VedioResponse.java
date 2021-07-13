package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class VedioResponse {

    @SerializedName("id")
    private Long id;


    @SerializedName("uploadate")
    private String uploadate;

    @SerializedName("filename")
    private String filename;

    @SerializedName("fileurl")
    private String fileurl;

    @SerializedName("fileurl1")
    private String fileurl1;


    @SerializedName("fileurl2")
    private String fileurl2;



    @SerializedName("fileurl3")
    private String fileurl3;

    @SerializedName("fileurl4")
    private String fileurl4;

    @SerializedName("status")
    private String status;

    @SerializedName("sellerid")
    private String sellerid;


    @SerializedName("userid")
    private String userid;
    @SerializedName("offerid")
    private String offerid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadate() {
        return uploadate;
    }

    public void setUploadate(String uploadate) {
        this.uploadate = uploadate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getFileurl1() {
        return fileurl1;
    }

    public void setFileurl1(String fileurl1) {
        this.fileurl1 = fileurl1;
    }

    public String getFileurl2() {
        return fileurl2;
    }

    public void setFileurl2(String fileurl2) {
        this.fileurl2 = fileurl2;
    }

    public String getFileurl3() {
        return fileurl3;
    }

    public void setFileurl3(String fileurl3) {
        this.fileurl3 = fileurl3;
    }

    public String getFileurl4() {
        return fileurl4;
    }

    public void setFileurl4(String fileurl4) {
        this.fileurl4 = fileurl4;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOfferid() {
        return offerid;
    }

    public void setOfferid(String offerid) {
        this.offerid = offerid;
    }

    @Override
    public String toString() {
        return "VedioResponse{" +
                "id=" + id +
                ", uploadate='" + uploadate + '\'' +
                ", filename='" + filename + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", fileurl1='" + fileurl1 + '\'' +
                ", fileurl2='" + fileurl2 + '\'' +
                ", fileurl3='" + fileurl3 + '\'' +
                ", fileurl4='" + fileurl4 + '\'' +
                ", status='" + status + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", userid='" + userid + '\'' +
                ", offerid='" + offerid + '\'' +
                '}';
    }
}
