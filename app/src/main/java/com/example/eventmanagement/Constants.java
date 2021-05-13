package com.example.eventmanagement;

import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static List<SearchEventResponseModel> EVENTS;
    public static List<EventModel> MY_EVENTS;
    public static final int EVENT_STATUS_FINISHED;
    public static final int EVENT_STATUS_ACTIVE;
    public static final int EVENT_STATUS_NOT_STARTED;

    static {
        EVENTS = new ArrayList<>();
        MY_EVENTS = new ArrayList<>();
        EVENT_STATUS_FINISHED = 0;
        EVENT_STATUS_ACTIVE = 1;
        EVENT_STATUS_NOT_STARTED = 2;
    }
}
