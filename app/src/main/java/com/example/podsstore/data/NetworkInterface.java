package com.example.podsstore.data;

import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkInterface {


    @POST("loginRest/register")
    Single<Response<CreateLoginUserResponse>> createuserregister(@Body CreateLoginUserRequest requests);

    @POST("loginRest/login")
    Single<Response<CreateLoginUserResponse>> userlogin(@Body LoginUserRequest requests);

    @GET("getproduct/")
    Single<Response<List<ProductResponse>>>getproducts();

}
