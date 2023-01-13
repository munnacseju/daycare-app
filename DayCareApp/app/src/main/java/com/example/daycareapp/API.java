package com.example.daycareapp;

import com.example.daycareapp.models.Caregiver;
import com.example.daycareapp.network.response.AllBabyResponse;
import com.example.daycareapp.network.response.AllCaregiverResponse;
import com.example.daycareapp.network.response.AllOrderResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @POST("register")
    Call<RegisterResponse> createUser (
            @Body User user
    );

    @POST("login")
    Call<ResponseBody> checkUser (
            @Body User user
    );

    @GET("findAllCaregiver")
    Call<AllCaregiverResponse> findAllCaregiver();

    @GET("findAllBaby")
    Call<AllBabyResponse> findAllBaby();

    @GET("findAllOrder")
    Call<AllOrderResponse> findAllOrder();

}
