package com.example.eventmanagement.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.eventmanagement.Activities.EventsBaseActivity;
import com.example.eventmanagement.Adapters.EventsAdapter;
import com.example.eventmanagement.Constants;
import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.R;

import java.util.List;

public class FutureEventsFragment extends Fragment implements EventsBaseActivity.ICallback {
    private RecyclerView recycleViewNotStartedEvents;
    private EventsAdapter adapter;
    private boolean isViewShown = false;

    public FutureEventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future_events, container, false);
        recycleViewNotStartedEvents = view.findViewById(R.id.recycleViewNotStartedEvents);
        setAdapter(Constants.NOT_STARTED_EVENTS);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            isViewShown = true;
            setAdapter(Constants.NOT_STARTED_EVENTS);
            adapter.notifyDataSetChanged();
        } else {
            isViewShown = false;
        }
    }

    private void setAdapter(List<EventModel> notStartedEvents) {
        adapter = new EventsAdapter(getContext(), notStartedEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleViewNotStartedEvents.setLayoutManager(layoutManager);
        recycleViewNotStartedEvents.setItemAnimator(new DefaultItemAnimator());
        recycleViewNotStartedEvents.setAdapter(adapter);
    }

    @Override
    public void sendCurrentPage(int page) {
        if (page == 1)
            setAdapter(Constants.FILTERED_NOT_STARTED_EVENTS);
        else
            setAdapter(Constants.NOT_STARTED_EVENTS);
    }
}