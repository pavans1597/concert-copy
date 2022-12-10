package com.exza.tech.model.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity(name="clients")
@Table(name="clients")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyCode")
    private long companyCode ;

    @Column(name = "clientName")
    private String clientName;

    @Column(name = "subscription_exp")
    private Date subscription_exp;

    @Column(name = "subscription_start")
    private Date subscription_start;

    @Column(name = "apikey")
    private String apikey;

    @Column(name = "appCount")
    private int appCount;

    public Clients( String apikey ,String clientName, Date subscription_exp, Date subscription_start ,int appCount) {
        this.appCount =appCount ;
        this.clientName = clientName;
        this.subscription_exp = subscription_exp;
        this.subscription_start = subscription_start;
        this.apikey = apikey;

    }

    public long getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getSubscription_exp() {
        return subscription_exp;
    }

    public void setSubscription_exp(Date subscription_exp) {
        this.subscription_exp = subscription_exp;
    }

    public Date getSubscription_start() {
        return subscription_start;
    }

    public void setSubscription_start(Date subscription_start) {
        this.subscription_start = subscription_start;
    }

    public Clients() {
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }



    public int getappCount() {
        return appCount;
    }

    public void setappCount(int appCount) {
        this.appCount = appCount;
    }
}
