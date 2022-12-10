package com.exza.tech.model.Entity;
import javax.persistence.*;

@Entity
@Table(name = "SECURED")
public class Secured {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrence")
    private int refrence ;

    @Column(name = "encrypteddata")
    private String encrypteddata;


    @Column(name = "aadhaarUuid")
    private String aadhaarUuid;

    public String getAadhaarUuid() {
        return aadhaarUuid;
    }

    public void setAadhaarUuid(String aadhaarUuid) {
        this.aadhaarUuid = aadhaarUuid;
    }

    public int getRefrence() {
        return refrence;
    }

    public void setRefrence(int refrence) {
        this.refrence = refrence;
    }

    public String getEncrypteddata() {
        return encrypteddata;
    }


    public void setEncrypteddata(String encrypteddata) {
        this.encrypteddata = encrypteddata;
    }
}
