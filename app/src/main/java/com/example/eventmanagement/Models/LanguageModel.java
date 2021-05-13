package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageModel {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("lang")
    @Expose
    private String lang;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
