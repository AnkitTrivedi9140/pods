package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("prodname")
    private String prodname;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", prodname='" + prodname + '\'' +
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
                '}';
    }
}
