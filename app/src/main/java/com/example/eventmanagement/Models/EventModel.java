package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventModel {
    @SerializedName("pkEventId")
    @Expose
    private Integer pkEventId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("registrationStartTime")
    @Expose
    private String registrationStartTime;
    @SerializedName("registrationEndTime")
    @Expose
    private String registrationEndTime;
    @SerializedName("isVirtual")
    @Expose
    private Boolean isVirtual;
    @SerializedName("isPublic")
    @Expose
    private Boolean isPublic;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("eventTopic")
    @Expose
    private EventTopicModel eventTopic;
    @SerializedName("chamber")
    @Expose
    private ChamberModel chamber;

    public Integer getPkEventId() {
        return pkEventId;
    }

    public void setPkEventId(Integer pkEventId) {
        this.pkEventId = pkEventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(String registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public String getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(String registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public EventTopicModel getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(EventTopicModel eventTopic) {
        this.eventTopic = eventTopic;
    }

    public ChamberModel getChamber() {
        return chamber;
    }

    public void setChamber(ChamberModel chamber) {
        this.chamber = chamber;
    }
}