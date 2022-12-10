package com.exza.tech.model.responsebody;

public class JwtResponse {
    private String appId;
    private String  aadharRefrence ;

    public JwtResponse(String appId, String aadharRefrence) {
        this.appId = appId;
        this.aadharRefrence = aadharRefrence;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAadharRefrence() {
        return aadharRefrence;
    }

    public void setAadharRefrence(String aadharRefrence) {
        this.aadharRefrence = aadharRefrence;
    }
}
