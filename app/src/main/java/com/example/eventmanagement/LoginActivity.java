package com.example.eventmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.API.IApi;
import com.example.eventmanagement.Models.LoginModel;
import com.example.eventmanagement.Models.LoginResponseModel;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private Button btnLogin;
    private IApi apiInterface;
    private TextInputLayout lytEmail, lytPassword;
    private TextInputEditText edtEmail, edtPassword;
    private SearchEventRequestModel searchEventModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lytEmail = findViewById(R.id.lytEmail);
        lytPassword = findViewById(R.id.lytPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
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
                loginModel.setEmail(edtEmail.getText().toString());
                loginModel.setPassword(edtPassword.getText().toString());

                if(validateInputField(lytEmail, edtEmail.getText()) && validateInputField(lytPassword, edtPassword.getText()))
                    login(loginModel);
            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInputField(lytEmail, s);
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInputField(lytPassword, s);
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
                    createDefaultSearchEventModel();
                    searchMyEvents();

                }
                else if (response.code() == 500)
                {
                    lytEmail.setError(getResources().getString(R.string.incorrect_email_or_password));
                    lytPassword.setError(getResources().getString(R.string.incorrect_email_or_password));
                }
                else{
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

    private boolean validateInputField(TextInputLayout lyt, Editable edt){
        if (!TextUtils.isEmpty(edt)) {
            lyt.setError(null);
            return true;
        }
        else if(lyt == lytEmail){
            lyt.setError(getResources().getString(R.string.please_enter_your_email_address));
            return false;
        }
        else{
            lyt.setError(getResources().getString(R.string.please_enter_your_password));
            return false;
        }
    }


    private void createDefaultSearchEventModel(){
        searchEventModel = new SearchEventRequestModel();
        searchEventModel.setPageNum(1);
        searchEventModel.setPageSize(10);
        searchEventModel.setName("");
        searchEventModel.setFrom("");
        searchEventModel.setTo("");
        searchEventModel.setFkChamberId(4);
        searchEventModel.setEventStatus(1);
        searchEventModel.setFkEventTopicId(1);
        searchEventModel.setIsPublic(false);
        searchEventModel.setIsVirtual(false);
    }

    private void searchMyEvents(){
        Call<SearchEventResponseModel> call = apiInterface.searchMyEvents("Bearer " + getToken(),  searchEventModel);
        call.enqueue(new Callback<SearchEventResponseModel>() {
            @Override
            public void onResponse(Call<SearchEventResponseModel> call, Response<SearchEventResponseModel> response) {
                if (response.isSuccessful())
                {
                    SearchEventResponseModel responseModel = response.body();
                    Constants.MY_EVENTS = responseModel.getEvents();
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, EventsBaseActivity.class);
                    startActivity(intent);

                }
                else if (response.code() == 500)
                {
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchEventResponseModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}