package com.example.eventmanagement.Activities;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.Interfaces.IApi;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.Models.CurrentUserResponseModel;
import com.example.eventmanagement.PreferenceManager;
import com.example.eventmanagement.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends BaseActivity {
    private IApi apiInterface;
    private TextView txtName, txtEmail;
    private CircleImageView profileImg;
    private static final int PICK_IMAGE = 1;
    private Uri imageUri;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        preferenceManager = new PreferenceManager(getApplicationContext());
        apiInterface = Api.getAPI();
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        profileImg = findViewById(R.id.profileImg);
        getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, getResources().getString(R.string.select_picture)), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            imageUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            profileImg.setImageBitmap(bitmap);

        }
    }

    private void getCurrentUser(){
        Call<CurrentUserResponseModel> call = apiInterface.getCurrentUser(preferenceManager.getToken());
        call.enqueue(new Callback<CurrentUserResponseModel>() {
            @Override
            public void onResponse(Call<CurrentUserResponseModel> call, Response<CurrentUserResponseModel> response) {
                if (response.isSuccessful())
                {
                    Constants.CURRENT_USER = response.body().getUser();
                    txtName.setText(Constants.CURRENT_USER.getFirstName() + " " + Constants.CURRENT_USER.getLastName());
                    txtEmail.setText(Constants.CURRENT_USER.getEmail());
                }
                else if (response.code() == 500)
                {
                    Toast.makeText(MyProfileActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MyProfileActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentUserResponseModel> call, Throwable t) {
                Toast.makeText(MyProfileActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}

