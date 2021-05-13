package com.example.eventmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
    private static final String PREF_NAME = "tokenPref";

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = pref.edit();
    }

    public void putTokenInSharedPreferences(String t){
        editor.putString("Token", t);
        editor.commit();
    }

    public String getToken(){
        return pref.getString("Token", "");
    }
}