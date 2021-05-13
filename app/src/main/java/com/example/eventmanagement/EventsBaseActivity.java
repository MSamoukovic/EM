package com.example.eventmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EventsBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_base);
    }
}