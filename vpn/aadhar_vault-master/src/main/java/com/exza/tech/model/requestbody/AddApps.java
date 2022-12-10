package com.exza.tech.model.requestbody;

public class AddApps {
    private String appName ;
    private String appId ;
    private String desc ;
    private Long clientCode ;

    public AddApps(String appName, String desc, Long clientCode,String appId) {
        this.appName = appName;
        this.desc = desc;

        this.appId = appId;
        this.clientCode = clientCode;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDesc() {
        return desc;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getClientCode() {
        return clientCode;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    @Override
    public String toString() {
        return "AddApps{" +
                "appName='" + appName + '\'' +
                ", appId='" + appId + '\'' +
                ", desc='" + desc + '\'' +
                ", clientCode=" + clientCode +
                '}';
    }
}
