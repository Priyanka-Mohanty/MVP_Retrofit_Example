package com.example.priyankam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultStatusPost {
    @SerializedName("siteID")
    @Expose
    private String siteID;
    @SerializedName("status")
    @Expose
    private String status;

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "{" +
                "siteId='" + siteID + '\'' +
                ", status=" + status +
                '}';
    }
}
