package com.exza.tech.model.Entity;

import javax.persistence.*;

@Entity(name="APPLICATIONS")
@Table(name="APPLICATIONS")
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refid")
    private Integer refid ;

    @Column(name = "appId")
    private  String appId;

    @Column(name = "appName")
    private  String appName;

    @ManyToOne
    @JoinColumn(name = "clientCode")
    private Clients clientCode;

    @Lob
    @Column(name = "public_key")
    private String public_key ;

    @Lob
    @Column(name = "private_key")
    private String private_key ;

    @Column(name = "status")
    private  String status;

    @Column(name = "discription")
    private  String discription;

    public Applications() {
    }


    public Integer getRefid() {
        return refid;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Clients getClientCode() {
        return clientCode;
    }

    public void setClientCode(Clients clientCode) {
        this.clientCode = clientCode;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Applications( String appId, String appName,
                        Clients clientCode, String public_key,
                        String private_key, String status, String discription) {
        this.appId = appId;
        this.appName = appName;
        this.clientCode = clientCode;
        this.public_key = public_key;
        this.private_key = private_key;
        this.status = status;
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Applications{" +
                "refid=" + refid +
                ", appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", clientCode=" + clientCode +
                ", public_key='" + public_key + '\'' +
                ", private_key='" + private_key + '\'' +
                ", status='" + status + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }

    public void setRefid(Integer refid) {
        this.refid = refid;
    }
}
