package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchEventRequestModel {

    @SerializedName("pageNum")
    @Expose
    private Integer pageNum;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("isVirtual")
    @Expose
    private Boolean isVirtual;
    @SerializedName("isPublic")
    @Expose
    private Boolean isPublic;
    @SerializedName("fkEventTopicId")
    @Expose
    private Integer fkEventTopicId;
    @SerializedName("eventStatus")
    @Expose
    private Integer eventStatus;
    @SerializedName("fkChamberId")
    @Expose
    private Integer fkChamberId;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public Integer getFkEventTopicId() {
        return fkEventTopicId;
    }

    public void setFkEventTopicId(Integer fkEventTopicId) {
        this.fkEventTopicId = fkEventTopicId;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Integer getFkChamberId() {
        return fkChamberId;
    }

    public void setFkChamberId(Integer fkChamberId) {
        this.fkChamberId = fkChamberId;
    }

}
