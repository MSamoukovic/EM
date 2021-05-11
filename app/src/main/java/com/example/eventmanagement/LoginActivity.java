package com.example.eventmanagement;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.API.IApi;
import com.example.eventmanagement.Models.LoginModel;
import com.example.eventmanagement.Models.LoginResponseModel;

import javax.microedition.khronos.egl.EGLDisplay;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private Button btnLogin;
    private IApi apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        apiInterface = Api.getAPI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginModel loginModel = new LoginModel();
                loginModel.setEmail("superadmin@gmail.com");
                loginModel.setPassword("123Pa$$word!");
                login(loginModel);
            }
        });
    }

    private void login(LoginModel loginModel){
        Call<LoginResponseModel> call = apiInterface.login(loginModel);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful())
                {
                    putTokenInSharedPreferences(response.body().getData().getJwToken());
                    Toast.makeText(LoginActivity.this, "ddd", Toast.LENGTH_LONG).show();
                }
                else if (response.code() == 400)
                {
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}