package com.example.eventmanagement.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.eventmanagement.API.Api;
import com.example.eventmanagement.Dialogs.SearchDialog;
import com.example.eventmanagement.Interfaces.IApi;
import com.example.eventmanagement.Adapters.ViewPagerAdapter;
import com.example.eventmanagement.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventsBaseActivity extends BaseActivity {
    private IApi apiInterface;
    private BottomNavigationView bottomNavView;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ImageButton btnSearch, btnMoreOptions;
    private int currentPage;
    public ICallback icallback;

    public interface ICallback {
        void sendCurrentPage(int page);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof ICallback) {
            icallback = (ICallback) fragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_base);
        apiInterface = Api.getAPI();
        bottomNavView = findViewById(R.id.bottomNavView);
        btnSearch = findViewById(R.id.btnSearch);
        btnMoreOptions = findViewById(R.id.btnMoreOptions);
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDialog searchDialog = new SearchDialog(EventsBaseActivity.this, currentPage);
                searchDialog.show();

                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                searchDialog.getWindow().setLayout(width, height);

                searchDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (searchDialog.isSubmitSearch()) {
                            icallback.sendCurrentPage(currentPage);
                        }
                    }
                });
            }
        });

        btnMoreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(EventsBaseActivity.this, btnMoreOptions);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.logoutMenuItem:
                                return true;
                            case R.id.myProfileMenuItem:
                                Intent intent = new Intent(EventsBaseActivity.this, MyProfileActivity.class);
                                startActivity(intent);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

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
                currentPage = position;
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
}