package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("prodname")
    private String prodname;


    @SerializedName("serialno")
    private String serialno;


    @SerializedName("prodtype")
    private String prodtype;

    @SerializedName("manufacturer")
    private String manufacturer;

    @SerializedName("brand")
    private String brand;

    @SerializedName("description")
    private String description;

    @SerializedName("feature")
    private String feature;

    @SerializedName("standards")
    private String standards;

    @SerializedName("materials")
    private String materials;

    @SerializedName("functions")
    private String functions;

    @SerializedName("certifications")
    private String certifications;

    @SerializedName("country")
    private String country;

    @SerializedName("website")
    private String website;

    @SerializedName("imageurl")
    private String imageurl;

    @SerializedName("redirecturl")
    private String redirecturl;

    @SerializedName("status")
    private String status;

    @SerializedName("discount")
    private String discount;
    @SerializedName("price")
    private String price;

    @SerializedName("min_quantity")
    private String minqty;

    @SerializedName("imageurl1")
    private String imageurl1;

    @SerializedName("imageurl2")
    private String imageurl2;
    @SerializedName("imageurl3")
    private String imageurl3;

    @SerializedName("imageurl4")
    private String imageurl4;


    @SerializedName("returnpolicyurl")
    private String returnpolicyurl;

    @SerializedName("location")
    private String location;
    @SerializedName("proofoffundurl")
    private String proofoffundurl;

    @SerializedName("certificateurl")
    private String certificateurl;



    @SerializedName("pricetype")
    private String pricetype;


    public String getReturnpolicyurl() {
        return returnpolicyurl;
    }

    public void setReturnpolicyurl(String returnpolicyurl) {
        this.returnpolicyurl = returnpolicyurl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProofoffundurl() {
        return proofoffundurl;
    }

    public void setProofoffundurl(String proofoffundurl) {
        this.proofoffundurl = proofoffundurl;
    }

    public String getCertificateurl() {
        return certificateurl;
    }

    public void setCertificateurl(String certificateurl) {
        this.certificateurl = certificateurl;
    }

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public String getImageurl1() {
        return imageurl1;
    }

    public void setImageurl1(String imageurl1) {
        this.imageurl1 = imageurl1;
    }

    public String getImageurl2() {
        return imageurl2;
    }

    public void setImageurl2(String imageurl2) {
        this.imageurl2 = imageurl2;
    }

    public String getImageurl3() {
        return imageurl3;
    }

    public void setImageurl3(String imageurl3) {
        this.imageurl3 = imageurl3;
    }

    public String getImageurl4() {
        return imageurl4;
    }

    public void setImageurl4(String imageurl4) {
        this.imageurl4 = imageurl4;
    }

    public String getMinqty() {
        return minqty;
    }

    public void setMinqty(String minqty) {
        this.minqty = minqty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getRedirecturl() {
        return redirecturl;
    }

    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", prodname='" + prodname + '\'' +
                ", serialno='" + serialno + '\'' +
                ", prodtype='" + prodtype + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", feature='" + feature + '\'' +
                ", standards='" + standards + '\'' +
                ", materials='" + materials + '\'' +
                ", functions='" + functions + '\'' +
                ", certifications='" + certifications + '\'' +
                ", country='" + country + '\'' +
                ", website='" + website + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", redirecturl='" + redirecturl + '\'' +
                ", status='" + status + '\'' +
                ", discount='" + discount + '\'' +
                ", price='" + price + '\'' +
                ", minqty='" + minqty + '\'' +
                ", imageurl1='" + imageurl1 + '\'' +
                ", imageurl2='" + imageurl2 + '\'' +
                ", imageurl3='" + imageurl3 + '\'' +
                ", imageurl4='" + imageurl4 + '\'' +
                ", returnpolicyurl='" + returnpolicyurl + '\'' +
                ", location='" + location + '\'' +
                ", proofoffundurl='" + proofoffundurl + '\'' +
                ", certificateurl='" + certificateurl + '\'' +
                ", pricetype='" + pricetype + '\'' +
                '}';
    }
}
