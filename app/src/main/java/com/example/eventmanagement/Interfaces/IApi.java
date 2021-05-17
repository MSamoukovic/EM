package com.example.eventmanagement.Interfaces;

import com.example.eventmanagement.Models.CurrentUserResponseModel;
import com.example.eventmanagement.Models.LoginResponseModel;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IApi {

    @POST("api/Account/authenticate")
    Call<LoginResponseModel> login(
            @Body Object userModel
    );


    @Headers({"Content-Type:application/json","Accept:application/json"})
    @GET("api/Account/get-current-user")
    Call<CurrentUserResponseModel> getCurrentUser(
            @Header("Authorization") String token
    );

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/Events/search-my-events")
    Call<SearchEventResponseModel> searchMyEvents(
            @Header("Authorization") String token,
            @Body SearchEventRequestModel searchEventModel
    );
}
