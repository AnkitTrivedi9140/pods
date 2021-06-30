package com.example.podsstore.data.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CheckoutRequest  {
    @SerializedName("productId")
    private String productid;

    @SerializedName("quantity")
    private String quantity;





    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CheckoutRequest{" +
                "productid='" + productid + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }


}
