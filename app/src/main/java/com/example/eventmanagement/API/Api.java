package com.example.eventmanagement.API;

import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String BASE_URL = "http://192.168.10.132:5000/";
    private static Retrofit retrofit = null;

    public static Retrofit get() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static IApi getAPI (){
        return get().create(IApi.class);
    }
}
