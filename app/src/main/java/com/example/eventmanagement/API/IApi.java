package com.example.eventmanagement.API;

import com.example.eventmanagement.Models.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IApi {

    @POST("api/Account/authenticate")
    Call<LoginResponseModel> login(
            @Body Object userModel
    );

    @POST("api/Events/search-my-events")
    Call<Object> searchMyEvents(
            @Body Object searchEventModel
    );
}
