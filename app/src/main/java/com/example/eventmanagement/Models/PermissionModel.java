package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PermissionModel {
    @SerializedName("functionAliases")
    @Expose
    private List<Object> functionAliases = null;

    public List<Object> getFunctionAliases() {
        return functionAliases;
    }

    public void setFunctionAliases(List<Object> functionAliases) {
        this.functionAliases = functionAliases;
    }
}
