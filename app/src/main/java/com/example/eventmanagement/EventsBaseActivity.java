package com.example.eventmanagement;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.API.IApi;
import com.example.eventmanagement.Adapters.ViewPagerAdapter;
import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsBaseActivity extends BaseActivity {
    private IApi apiInterface;
    private BottomNavigationView bottomNavView;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_base);
        apiInterface = Api.getAPI();
        bottomNavView = findViewById(R.id.bottomNavView);
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navActiveEvents){
                    viewPager.setCurrentItem(0);
                    return true;
                }
                else if(item.getItemId() == R.id.navFutureEvents) {
                    viewPager.setCurrentItem(1);
                    return true;
                }
                else
                    return false;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case (0):
                        bottomNavView.getMenu().findItem(R.id.navActiveEvents).setChecked(true);
                        break;
                    case(1):
                        bottomNavView.getMenu().findItem(R.id.navFutureEvents).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}