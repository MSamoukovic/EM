package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventTopicModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameTitleId")
    @Expose
    private String nameTitleId;
    @SerializedName("descriptionTitleId")
    @Expose
    private String descriptionTitleId;
    @SerializedName("nameTranslations")
    @Expose
    private List<TranslationModel> nameTranslations = null;
    @SerializedName("descriptionTranslations")
    @Expose
    private List<TranslationModel> descriptionTranslations = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTitleId() {
        return nameTitleId;
    }

    public void setNameTitleId(String nameTitleId) {
        this.nameTitleId = nameTitleId;
    }

    public String getDescriptionTitleId() {
        return descriptionTitleId;
    }

    public void setDescriptionTitleId(String descriptionTitleId) {
        this.descriptionTitleId = descriptionTitleId;
    }

    public List<TranslationModel> getNameTranslations() {
        return nameTranslations;
    }

    public void setNameTranslations(List<TranslationModel> nameTranslations) {
        this.nameTranslations = nameTranslations;
    }

    public List<TranslationModel> getDescriptionTranslations() {
        return descriptionTranslations;
    }

    public void setDescriptionTranslations(List<TranslationModel> descriptionTranslations) {
        this.descriptionTranslations = descriptionTranslations;
    }
}
