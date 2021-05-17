package com.example.eventmanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.eventmanagement.PreferenceManager;
import com.example.eventmanagement.R;

public class BaseActivity extends AppCompatActivity {
    public String token;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        preferenceManager = new PreferenceManager(getApplicationContext());
        token = preferenceManager.getToken();
    }

    public String getToken() {
        return token;
    }

}