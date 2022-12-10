package com.exza.tech.model.responsebody;

public class AadharResponse {


    public AadharResponse(String appId, String aadharNumber) {
        AppId = appId;
        this.aadharNumber = aadharNumber;
    }

    private String AppId;
    private String aadharNumber;

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
}
