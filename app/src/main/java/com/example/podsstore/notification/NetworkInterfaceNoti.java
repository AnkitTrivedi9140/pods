package com.example.podsstore.notification;

import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.NotificationRequest;
import com.example.podsstore.data.request.PlaceOrderNotificationRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkInterfaceNoti {

    @POST("noti/register")
    Single<Response<CreateLoginUserResponse>> createuserregisternotification(@Body NotificationRequest requests);

    @POST("noti/orderplace")
    Single<Response<CreateLoginUserResponse>> ordernoti(@Body PlaceOrderNotificationRequest requests);

}
