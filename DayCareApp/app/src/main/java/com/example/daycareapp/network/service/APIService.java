package com.example.daycareapp.network.service;

import com.example.daycareapp.models.Caregiver;
import com.example.daycareapp.models.User;
import com.example.daycareapp.models.Baby;
import com.example.daycareapp.models.Order;
import com.example.daycareapp.network.response.AccountVerifyResponse;
import com.example.daycareapp.network.response.AddBabyResponse;
import com.example.daycareapp.network.response.AddCaregiverResponse;
import com.example.daycareapp.network.response.AllBabyResponse;
import com.example.daycareapp.network.response.AllCaregiverResponse;
import com.example.daycareapp.network.response.AllOrderResponse;
import com.example.daycareapp.network.response.CreateOrderResponse;
import com.example.daycareapp.network.response.RegisterResponseModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

//    @POST("register")
//    Call<RegisterResponseModel> createUser (
//            @Body User user
//    );
//
//    @POST("login")
//    Call<ResponseBody> checkUser (
//            @Body User user
//    );

    @GET("findAllCaregiver")
    Call<AllCaregiverResponse> findAllCaregiver();

    @GET("findAllBaby")
    Call<AllBabyResponse> findAllBaby();

    @GET("findAllOrder")
    Call<AllOrderResponse> findAllOrder();

    @POST("addBaby")
    Call<AddBabyResponse> addBaby(@Body Baby baby);

    @POST("addOrder")
    Call<CreateOrderResponse> createOrder(@Body Order order);

    @GET("verifypin/{pin}")
    Call<AccountVerifyResponse> veryfyPin(@Path("pin") String pin);

    @POST("addCaregiver")
    Call<AddCaregiverResponse> addCaregiver(@Body Caregiver caregiver);
}
