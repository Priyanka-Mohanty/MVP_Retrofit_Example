
package com.example.priyankam.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultObject {
    @SerializedName("siteID")
    @Expose
    private String siteID;
    @SerializedName("siteName")
    @Expose
    private String siteName;
    @SerializedName("equipmentID")
    @Expose
    private String equipmentID;
    @SerializedName("equipmentType")
    @Expose
    private String equipmentType;
    @SerializedName("port")
    @Expose
    private String port;
    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("loadType")
    @Expose
    private String loadType;
    @SerializedName("pinNumber")
    @Expose
    private String pinNumber;

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }


}
