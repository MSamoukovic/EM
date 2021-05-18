package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChamberModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fkCountryId")
    @Expose
    private Integer fkCountryId;
    @SerializedName("titleId")
    @Expose
    private String titleId;

    @SerializedName("nameTranslations")
    @Expose
    private String nameTranslations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkCountryId() {
        return fkCountryId;
    }

    public void setFkCountryId(Integer fkCountryId) {
        this.fkCountryId = fkCountryId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getNameTranslations() {
        return nameTranslations;
    }

    public void setNameTranslations(String nameTranslations) {
        this.nameTranslations = nameTranslations;
    }
}
