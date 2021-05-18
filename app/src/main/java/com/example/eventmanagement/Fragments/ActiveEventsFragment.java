package com.example.eventmanagement.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmanagement.Activities.EventsBaseActivity;
import com.example.eventmanagement.Adapters.EventsAdapter;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.R;

import java.util.List;

public class ActiveEventsFragment extends Fragment implements EventsBaseActivity.ICallback {
    private RecyclerView recycleViewActiveEvents;
    private EventsAdapter adapter;
    private boolean isViewShown = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_events, container, false);
        recycleViewActiveEvents = view.findViewById(R.id.recycleViewActiveEvents);
        setAdapter(Constants.ACTIVE_EVENTS);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            isViewShown = true;
            setAdapter(Constants.ACTIVE_EVENTS);
            adapter.notifyDataSetChanged();
        } else {
            isViewShown = false;
        }
    }

    private void setAdapter(List<EventModel> activeEvents) {
        adapter = new EventsAdapter(getContext(), activeEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleViewActiveEvents.setLayoutManager(layoutManager);
        recycleViewActiveEvents.setItemAnimator(new DefaultItemAnimator());
        recycleViewActiveEvents.setAdapter(adapter);
    }

    @Override
    public void sendCurrentPage(int page) {
        if (page == 0)
            setAdapter(Constants.FILTERED_ACTIVE_EVENTS);
        else
            setAdapter(Constants.ACTIVE_EVENTS);
    }
}