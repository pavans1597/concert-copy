package com.exza.tech.model.Entity;
import javax.persistence.*;

@Entity(name="REQUESTED")
@Table(name="REQUESTED")
public class Requested {

    public String getEMPLOYEENAME() {
        return EMPLOYEENAME;
    }

    public void setEMPLOYEENAME(String EMPLOYEENAME) {
        this.EMPLOYEENAME = EMPLOYEENAME;
    }

    public String getEMPLOYEEID() {
        return EMPLOYEEID;
    }

    public void setEMPLOYEEID(String EMPLOYEEID) {
        this.EMPLOYEEID = EMPLOYEEID;
    }

    public Clients getClientCode() {
        return clientCode;
    }

    public void setClientCode(Clients clientCode) {
        this.clientCode = clientCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID ;

    @Column(name = "AADHARID")
    private String AADAHRID;

    @Column(name = "TIME")
    private String  TIME;

    @Column(name = "AppId")
    private String AppId;

    @Column(name = "EMPLOYEENAME")
    private String EMPLOYEENAME;

    @Column(name = "EMPLOYEEID")
    private String  EMPLOYEEID;

    @ManyToOne
    @JoinColumn(name = "clientCode")
    private Clients clientCode;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAADAHRID() {
        return AADAHRID;
    }

    public void setAADAHRID(String AADAHRID) {
        this.AADAHRID = AADAHRID;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String AppId) {
        this.AppId = AppId;
    }


    public Requested() {

    }

    public Requested(String AADAHRID, String TIME, String AppId) {

        this.AADAHRID = AADAHRID;
        this.TIME = TIME;
        this.AppId = AppId;
    }


}
