package com.example.podsstore.data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

   /* public static final String BASE_URL = BuildConfig.BASE_URL;*/
   //public static final String BASE_URL ="http://216.10.243.60:4067/PodsStoreAPI/";
    public static final String BASE_URL ="http://216.10.243.60:4057/PodAPI/";
   private static Retrofit retrofit = null;

   public static final long NETWORK_CALL_TIMEOUT = 60;




    public static NetworkInterface getApiClient() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(NetworkInterface.class);
    }
}
