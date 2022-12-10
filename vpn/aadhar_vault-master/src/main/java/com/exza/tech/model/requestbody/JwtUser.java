package com.exza.tech.model.requestbody;

public class JwtUser {
    private String aadharnumber;
    private String appid;
    private Metadata metadata;

    public String getAadharnumber() {
        return aadharnumber;
    }

    public void setAadharnumber(String aadharnumber) {
        this.aadharnumber = aadharnumber;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public JwtUser() {
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "aadharnumber='" + aadharnumber + '\'' +
                ", appid='" + appid + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }


}
