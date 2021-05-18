package com.example.eventmanagement;

import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.Models.EventTopicModel;
import com.example.eventmanagement.Models.SearchEventResponseModel;
import com.example.eventmanagement.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static List<EventModel> ACTIVE_EVENTS;
    public static List<EventModel> NOT_STARTED_EVENTS;
    public static List<EventModel> FILTERED_ACTIVE_EVENTS;
    public static List<EventModel> FILTERED_NOT_STARTED_EVENTS;
    public static List<EventTopicModel> ALL_EVENT_TOPICS;

    public static final int EVENT_STATUS_FINISHED;
    public static final int EVENT_STATUS_ACTIVE;
    public static final int EVENT_STATUS_NOT_STARTED;
    public static UserModel CURRENT_USER;
    public static boolean SEARCH_ACTIVE_EVENTS;

    static {
        ACTIVE_EVENTS = new ArrayList<>();
        NOT_STARTED_EVENTS = new ArrayList<>();
        FILTERED_ACTIVE_EVENTS = new ArrayList<>();
        FILTERED_NOT_STARTED_EVENTS = new ArrayList<>();
        ALL_EVENT_TOPICS = new ArrayList<>();

        EVENT_STATUS_FINISHED = 0;
        EVENT_STATUS_ACTIVE = 1;
        EVENT_STATUS_NOT_STARTED = 2;
        CURRENT_USER = new UserModel();
        SEARCH_ACTIVE_EVENTS = false;
    }
}
