package com.exza.tech.model.requestbody;

public class RequestJWT {
private String AppId;
private String refuuid;
    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getrefuuid() {
        return refuuid;
    }

    public void setrefuuid(String refuuid) {
        this.refuuid = refuuid;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    private Metadata metadata;

    @Override
    public String toString() {
        return "requestJWT{" +
                "AppId='" + AppId + '\'' +
                ", refuuid=" + refuuid +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
