package com.example.eventmanagement;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private Context context;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    private static final String PREF_NAME = "token";
    int PRIVATE_MODE = 0;

    public PreferenceManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public String getToken() {
        return pref.getString("token", null);
    }

    public void setToken(String value) {
        editor.putString("token", "Bearer " + value);
        editor.commit();
    }
}
