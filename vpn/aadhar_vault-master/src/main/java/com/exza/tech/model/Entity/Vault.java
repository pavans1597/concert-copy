package com.exza.tech.model.Entity;

import javax.persistence.*;

@Entity(name="VAULT")
@Table(name="VAULT")
public class Vault {

  public Vault() { }

  public Secured getSecured() {
    return secured;
  }

  public void setSecured(Secured secured) {
    this.secured = secured;
  }

  public String getVaultUuid() {
    return vaultUuid;
  }

  public void setVaultUuid(String vaultUuid) {
    this.vaultUuid = vaultUuid;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "refId")
  private int refId ;

  @OneToOne(cascade =CascadeType.ALL)
  @JoinColumn(name = "secured")
  private Secured secured;

  @Column(name = "appid")
  private String appid;

  @Column(name = "salt")
  private   String salt;

  @Override
  public String toString() {
    return "vault{" +
            "refId=" + refId +
            ", reference=" + secured +
            ", appid='" + appid + '\'' +
            ", salt='" + salt + '\'' +
            ", time='" + time + '\'' +
            ", metadata='" + metadata + '\'' +
            '}';
  }
  @Column(name = "time")
  private  String time;

  @Column(name = "metadata")
  private   String metadata;

  @Column(name = "vaultUuid")
  private String vaultUuid;

  public int getrefId() {
    return refId;
  }

  public void setrefId(int refId) {
    this.refId = refId;
  }

  public void setReference(Secured reference) {
    this.secured = reference;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public Vault(String appid, String salt, String time, String metadata , String uuid) {
    this.appid = appid;
    this.salt = salt;
    this.time = time;
    this.metadata = metadata;
    this.vaultUuid =uuid;
  }
}