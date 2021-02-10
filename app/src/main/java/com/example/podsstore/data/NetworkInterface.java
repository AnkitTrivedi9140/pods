package com.example.podsstore.data;

import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.LoginResponse;
import com.example.podsstore.data.response.ProductResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {


    @POST("loginRest/register")
    Single<Response<CreateLoginUserResponse>> createuserregister(@Body CreateLoginUserRequest requests);

    @POST("loginRest/login")
    Single<Response<LoginResponse>> userlogin(@Body LoginUserRequest requests);

    @Headers({"Content-Type: application/json"})
    @POST("/productRest/getProduct")
    Single<Response<List<ProductResponse>>>getproducts(@Header("Authorization") String authHeader);

    @GET("getprod/")
    Call<List<ProductResponse>>getproductsdetails(@Query("id") String userId);

    @GET("getbusinesscat/")
    Single<Response<List<BusinessCatResponse>>>getbusinesscat();
}
