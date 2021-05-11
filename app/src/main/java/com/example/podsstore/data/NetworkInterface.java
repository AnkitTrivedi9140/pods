package com.example.podsstore.data;

import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.ChangePasswordRequest;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.request.MakeOfferRequest;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.request.ProductReviewRequest;
import com.example.podsstore.data.request.QtyRequest;
import com.example.podsstore.data.request.ReturnRequest;
import com.example.podsstore.data.request.ReviewRequest;
import com.example.podsstore.data.request.TellUsMoreResquest;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.BestSellingProductResponse;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CountryResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.LoginResponse;
import com.example.podsstore.data.response.OrderResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.data.response.QtyResponse;
import com.example.podsstore.data.response.ReviewResponse;
import com.example.podsstore.data.response.SubCategoryProductResponce;
import com.example.podsstore.data.response.SubCategoryResponce;
import com.example.podsstore.data.response.TopBrandsProductResponse;
import com.example.podsstore.data.response.TopBrandsResponse;
import com.example.podsstore.data.response.UploadImageResponse;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

import retrofit2.http.Multipart;
import retrofit2.http.POST;

import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {


    @POST("loginRest/register")
    Single<Response<CreateLoginUserResponse>> createuserregister(@Body CreateLoginUserRequest requests);


    @POST("loginRest/loginapp")
    Single<Response<LoginResponse>> userlogin(@Body LoginUserRequest requests);


    @POST("imageRest/getReview")
    Single<Response<List<ReviewResponse>> >getproductReview(@Body ProductReviewRequest requests);



    @POST("cartRest/addquantity")
    Single<Response<QtyResponse>> addqty(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Body QtyRequest requests);



    @POST("loginRest/passwordChange")
    Single<Response<CreateLoginUserResponse>> pwdsuccess(@Query("userEmailId") String emailId,@Body ChangePasswordRequest requests);


    @POST("productRest/getProduct")
    Single<Response<List<ProductResponse>>>getproducts();

    @POST("productRest/getProd")
    Call<List<ProductResponse>>getproductsdetails(@Query("id") String userId);

    @POST("loginRest/users")
    Call<List<ProfileResponses>>profile(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId);

    @POST("countryRest/getCountryDetails")
    Single<Response<List<CountryResponse>>>getcountry();

    @POST("countryRest/selectCountry")
    Call<CountryResponse>selectcountry(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Query("countryId") String countryid);


    @POST("loginRest/forgotPassword")
    Call<CreateLoginUserResponse>forgotpassword(@Query("userEmailId") String emailId);

    @POST("addressRest/getAddressDetails")
    Single<Response<List<AddressResponse>>>getalladdress(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId);

    @POST("loginRest/changePhoneNumber")
    Call<CreateLoginUserResponse>changeno(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Query("phoneNumber") String mobileno);


    @POST("businessRest/homeCategory")
    Single<Response<List<BusinessCatResponse>>>getbusinesscat();

    @POST("brandRest/getBrandDetails")
    Single<Response<List<TopBrandsResponse>>>gettopbrands();


    @POST("businessRest/homeSubCategory")
    Single<Response<List<SubCategoryResponce>>>getsubcategory(@Query("id") String id);

    @POST("productRest/getProductByBrandName")
    Single<Response<List<TopBrandsProductResponse>>>gettopbrandproduct(@Query("brandName") String brandname);

    @POST("businessRest/homeSubCatInfo")
    Single<Response<List<SubCategoryProductResponce>>>getproductbycategory(@Query("catid") String catid, @Query("id") String id);

    @POST("tellUsRest/tellUsMore")
    Single<Response<CreateLoginUserResponse>> tellusmore(@Query("userEmailId") String emailId,@Body TellUsMoreResquest requests);

    @POST("addressRest/changeAddressDetails")
    Single<Response<CreateLoginUserResponse>> submitaddress(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body AddressDetailsRequest requests);


    @POST("makerOfferRest/makerOffer")
    Single<Response<CreateLoginUserResponse>> makeoffer(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body MakeOfferRequest requests);



    @POST("makerOfferRest/makerOffer")
    Single<Response<CreateLoginUserResponse>> updateoffer(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body MakeOfferRequest requests);



    @POST("cartRest/addToCart")
    Single<Response<CreateLoginUserResponse>> addtocart(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body AddtocartRequest requests);

    @POST("productRest/getBestSellingProduct")
    Single<Response<List<TopBrandsProductResponse>>>getbestsellingproducts();

    @POST("productRest/getBestPricedProduct")
    Single<Response<List<TopBrandsProductResponse>>>getbestpricedproduct();

    @Multipart
    @POST("imageRest/uploadProfilePhoto")
    Call<CreateLoginUserResponse> uploadImage(@Header("Authorization") String authHeader, @Part MultipartBody.Part file,
                                          @Part("userEmailId") RequestBody  userEmailId );


    @POST("cartRest/getCartDetails")
    Call<List<CartResponse>>getcartdetails(@Header("Authorization") String authHeader, @Query("userEmailId") String emailId);


    @POST("orderRest/getOrderDetails")
    Call<List<OrderResponse>>getplaceorder(@Header("Authorization") String authHeader, @Query("userEmailId") String emailId);


    @POST("orderRest/placeOrder")
    Single<Response<CreateLoginUserResponse>> placeOrder(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body List<PlaceOrderRequest> requests  );


    @POST("cartRest/deleteCart")
    Call<CreateLoginUserResponse>deletecart(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Query("productId") String productId);


    @POST("wishListRest/addToWishList")
    Single<Response<CreateLoginUserResponse>> addtowishlist(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body AddtocartRequest requests);


    @POST("wishListRest/getWishList")
    Call<List<CartResponse>>getwishlist(@Header("Authorization") String authHeader, @Query("userEmailId") String emailId);


    @POST("wishListRest/deleteWishList")
    Call<CreateLoginUserResponse>deletewishlist(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Query("productId") String productId);


    @POST("addressRest/deleteAddress")
    Call<CreateLoginUserResponse>deleteaddress(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Query("addressId") String addressid);


    @POST("wishListRest/moveToCart")
    Call<CreateLoginUserResponse>movetocart(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Query("productId") String productId);


    @GET("productRest/search")
    Single<Response<List<ProductResponse>>>search(@Query("data") String data);


    @POST("couponRest/applyCoupon")
    Call<String>getcoupon(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId, @Query("couponCode") String couponno);


    @POST("loginRest/confirmReset")
    Call<CreateLoginUserResponse>confirmotp(@Query("userEmailId") String emailId, @Query("otp") String otp);


    @POST("reviewRest/addReview")
    Single<Response<CreateLoginUserResponse>> addreview(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body ReviewRequest requests);




    @POST("imageRest/getReview")
    Single<Response<List<ReviewResponse>>>getallReview(@Body ProductReviewRequest requests);

    @POST("orderRest/returninit")
    Single<Response<CreateLoginUserResponse>> prodreturn(@Header("Authorization") String authHeader,@Query("userEmailId") String emailId,@Body ReturnRequest requests);


}

