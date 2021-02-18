package com.example.podsstore.data;

import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.request.TellUsMoreResquest;
import com.example.podsstore.data.response.BestSellingProductResponse;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.LoginResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.data.response.UploadImageResponse;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;

import retrofit2.http.Field;
import retrofit2.http.Header;

import retrofit2.http.Multipart;
import retrofit2.http.POST;

import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {


    @POST("loginRest/register")
    Single<Response<CreateLoginUserResponse>> createuserregister(@Body CreateLoginUserRequest requests);


    @POST("loginRest/login")
    Single<Response<LoginResponse>> userlogin(@Body LoginUserRequest requests);


    @POST("productRest/getProduct")
    Single<Response<List<ProductResponse>>>getproducts(@Header("Authorization") String authHeader);

    @POST("productRest/getProd")
    Call<List<ProductResponse>>getproductsdetails(@Header("Authorization") String authHeader,@Query("id") String userId);

    @POST("loginRest/users")
    Call<ProfileResponses>profile(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId);

    @POST("businessRest/getBusinessCategory")
    Single<Response<List<BusinessCatResponse>>>getbusinesscat(@Header("Authorization") String authHeader);


    @POST("tellUsRest/tellUsMore")
    Single<Response<CreateLoginUserResponse>> tellusmore(@Header("Authorization") String authHeader,@Body TellUsMoreResquest requests);

    @POST("addressRest/changeAddressDetails")
    Single<Response<CreateLoginUserResponse>> submitaddress(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body AddressDetailsRequest requests);

    @POST("cartRest/addToCart")
    Single<Response<CreateLoginUserResponse>> addtocart(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body AddtocartRequest requests);



    @POST("productRest/getBestSellingProduct")
    Single<Response<List<BestSellingProductResponse>>>getbestsellingproducts(@Header("Authorization") String authHeader);

    @POST("productRest/getBestPricedProduct")
    Single<Response<List<BestSellingProductResponse>>>getbestpricedproduct(@Header("Authorization") String authHeader);

    @Multipart
    @POST("imageRest/uploadProfilePhoto")
    Call<UploadImageResponse> uploadImage(@Header("Authorization") String authHeader, @Part MultipartBody.Part file,
                                          @Part("userEmailId") RequestBody  userEmailId );


    @POST("cartRest/getCartDetails")
    Call<List<CartResponse>>getcartdetails(@Header("Authorization") String authHeader, @Query("userEmailId") String emailId);

}
