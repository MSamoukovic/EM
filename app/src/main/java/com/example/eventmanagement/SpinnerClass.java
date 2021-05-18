package com.example.eventmanagement;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eventmanagement.Models.EventTopicModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerClass {
    private List<EventTopicModel> eventTopics;
    private Context context;
    private HashMap<Integer, String> spinnerMap;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> names;

    public SpinnerClass(Context context, List<EventTopicModel> eventTopics) {
        this.context = context;
        this.eventTopics = eventTopics;
        spinnerMap = new HashMap<Integer, String>();
        names = new ArrayList<>();
    }

    public ArrayAdapter<String> getEventTopicsAdapter() {
        names.add(context.getResources().getString(R.string.event_topic));
        for (int i = 0; i < Constants.ALL_EVENT_TOPICS.size(); i++) {
            spinnerMap.put(Constants.ALL_EVENT_TOPICS.get(i).getId(), Constants.ALL_EVENT_TOPICS.get(i).getNameTitleId());
            names.add(Constants.ALL_EVENT_TOPICS.get(i).getNameTitleId());
        }
        adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, names) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                else
                    return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textview = (TextView) view;
                if (position == 0) {
                    textview.setBackgroundColor(context.getColor(R.color.lightGray));
                    textview.setTextColor(context.getColor(R.color.lightGray));
                } else {
                    textview.setTextColor(context.getColor(R.color.black));
                }
                return view;
            }
        };
        return adapter;
    }


    public Integer getEventTopicId(String eventTopicTitle) {
        Integer eventTopicId = 0;
        for (Map.Entry<Integer, String> pair : spinnerMap.entrySet()) {
            if (pair.getValue().equals(eventTopicTitle)) {
                eventTopicId = pair.getKey();
                break;
            }
        }
        return eventTopicId;
    }
}

