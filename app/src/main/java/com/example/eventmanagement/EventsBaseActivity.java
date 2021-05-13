package com.example.eventmanagement;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eventmanagement.API.IApi;
import com.example.eventmanagement.Adapters.ViewPagerAdapter;
import com.example.eventmanagement.Models.SearchEventRequestModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

    private void searchMyEvents(){
        SearchEventRequestModel searchEventModel = new SearchEventRequestModel();
        searchEventModel.setPageNum(1);
        searchEventModel.setPageSize(10);
        searchEventModel.setName("");
        searchEventModel.setFrom("");
        searchEventModel.setTo("");
        searchEventModel.setFkChamberId(4);
        searchEventModel.setEventStatus(0);
        searchEventModel.setFkEventTopicId(0);
        searchEventModel.setIsPublic(true);
        searchEventModel.setIsVirtual(true);

        Call<Object> call = apiInterface.searchMyEvents(searchEventModel);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful())
                {
                }
                else if (response.code() == 500)
                {
                }
                else{
                    Toast.makeText(EventsBaseActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(EventsBaseActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}