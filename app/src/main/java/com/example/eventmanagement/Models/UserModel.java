package com.example.eventmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("directPhone")
    @Expose
    private Object directPhone;
    @SerializedName("fkPersonPrefixId")
    @Expose
    private Integer fkPersonPrefixId;
    @SerializedName("position")
    @Expose
    private Object position;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fkLanguageId")
    @Expose
    private Integer fkLanguageId;
    @SerializedName("avatarName")
    @Expose
    private Object avatarName;
    @SerializedName("isDeactivated")
    @Expose
    private Boolean isDeactivated;
    @SerializedName("passwordChangeNeeded")
    @Expose
    private Boolean passwordChangeNeeded;
    @SerializedName("chamber")
    @Expose
    private Object chamber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getDirectPhone() {
        return directPhone;
    }

    public void setDirectPhone(Object directPhone) {
        this.directPhone = directPhone;
    }

    public Integer getFkPersonPrefixId() {
        return fkPersonPrefixId;
    }

    public void setFkPersonPrefixId(Integer fkPersonPrefixId) {
        this.fkPersonPrefixId = fkPersonPrefixId;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFkLanguageId() {
        return fkLanguageId;
    }

    public void setFkLanguageId(Integer fkLanguageId) {
        this.fkLanguageId = fkLanguageId;
    }

    public Object getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(Object avatarName) {
        this.avatarName = avatarName;
    }

    public Boolean getIsDeactivated() {
        return isDeactivated;
    }

    public void setIsDeactivated(Boolean isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

    public Boolean getPasswordChangeNeeded() {
        return passwordChangeNeeded;
    }

    public void setPasswordChangeNeeded(Boolean passwordChangeNeeded) {
        this.passwordChangeNeeded = passwordChangeNeeded;
    }

    public Object getChamber() {
        return chamber;
    }

    public void setChamber(Object chamber) {
        this.chamber = chamber;
    }
}
