package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleModel {
    @SerializedName("pkRoleId")
    @Expose
    private Integer pkRoleId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("titleId")
    @Expose
    private String titleId;
    @SerializedName("default")
    @Expose
    private Boolean _default;
    @SerializedName("adminUserType")
    @Expose
    private Boolean adminUserType;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("isSystem")
    @Expose
    private Boolean isSystem;
    @SerializedName("isBuiltin")
    @Expose
    private Boolean isBuiltin;
    @SerializedName("functions")
    @Expose
    private Object functions;

    public Integer getPkRoleId() {
        return pkRoleId;
    }

    public void setPkRoleId(Integer pkRoleId) {
        this.pkRoleId = pkRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public Boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    public Boolean getAdminUserType() {
        return adminUserType;
    }

    public void setAdminUserType(Boolean adminUserType) {
        this.adminUserType = adminUserType;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getIsBuiltin() {
        return isBuiltin;
    }

    public void setIsBuiltin(Boolean isBuiltin) {
        this.isBuiltin = isBuiltin;
    }

    public Object getFunctions() {
        return functions;
    }

    public void setFunctions(Object functions) {
        this.functions = functions;
    }
}
