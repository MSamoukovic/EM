package com.example.eventmanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.Interfaces.IApi;
import com.example.eventmanagement.Adapters.ViewPagerAdapter;
import com.example.eventmanagement.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
                if (item.getItemId() == R.id.navActiveEvents) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.navFutureEvents) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else
                    return false;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case (0):
                        bottomNavView.getMenu().findItem(R.id.navActiveEvents).setChecked(true);
                        break;
                    case (1):
                        bottomNavView.getMenu().findItem(R.id.navFutureEvents).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenuItem:
                return true;
            case R.id.myProfileMenuItem:
                Intent intent = new Intent(EventsBaseActivity.this, MyProfileActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchIcon));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }
}