package com.example.eventmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void putTokenInSharedPreferences(String t){
        editor = getSharedPreferences("Token", MODE_PRIVATE).edit();
        editor.putString("Token", t);
        token = t;
    }

    public String getToken(){
        return token;
    }
}