package com.example.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmanagement.Adapters.ActiveEventsAdapter;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.R;

import java.util.List;

public class ActiveEventsFragment extends Fragment {
    private RecyclerView recycleViewActiveEvents;
    private ActiveEventsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_events, container, false);
        recycleViewActiveEvents = view.findViewById(R.id.recycleViewActiveEvents);
        setAdapter(Constants.MY_EVENTS);
        return view;
    }

    private void setAdapter(List<EventModel> activeEvents){
        adapter = new ActiveEventsAdapter(getContext(), activeEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recycleViewActiveEvents.setLayoutManager(layoutManager);
        recycleViewActiveEvents.setItemAnimator(new DefaultItemAnimator());
        recycleViewActiveEvents.setAdapter(adapter);
    }
}