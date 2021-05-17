package com.example.eventmanagement.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.eventmanagement.Adapters.ActiveEventsAdapter;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.R;
import com.example.eventmanagement.Dialogs.SearchDialog;

import java.util.List;

public class ActiveEventsFragment extends Fragment {
    private RecyclerView recycleViewActiveEvents;
    private ActiveEventsAdapter adapter;
    private ImageButton btnSearch, btnBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_events, container, false);
        recycleViewActiveEvents = view.findViewById(R.id.recycleViewActiveEvents);
        btnBack = view.findViewById(R.id.imgBtnBack);
        btnSearch = view.findViewById(R.id.btnSearch);
        setAdapter(Constants.ACTIVE_EVENTS);
        return view;
    }

    private void setAdapter(List<EventModel> activeEvents){
        adapter = new ActiveEventsAdapter(getContext(), activeEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recycleViewActiveEvents.setLayoutManager(layoutManager);
        recycleViewActiveEvents.setItemAnimator(new DefaultItemAnimator());
        recycleViewActiveEvents.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDialog searchDialog = new SearchDialog(getContext());
                searchDialog.show();

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                searchDialog.getWindow().setLayout(width, height);

                searchDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(searchDialog.isSubmitSearch()){
                            setAdapter(Constants.FILTERED_ACTIVE_EVENTS);
                        }
                    }
                });
            }
        });
    }
}