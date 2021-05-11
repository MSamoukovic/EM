package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
    @SerializedName("succeeded")
    @Expose
    private Boolean succeeded;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private Object errors;
    @SerializedName("data")
    @Expose
    private LoginResponseDataModel data;

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public LoginResponseDataModel getData() {
        return data;
    }

    public void setData(LoginResponseDataModel data) {
        this.data = data;
    }

}
