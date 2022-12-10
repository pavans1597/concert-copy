package com.exza.tech.model.responsebody;
import com.exza.tech.model.Entity.Clients;

import java.util.Date;

public class LoginResponse {
    public String message;
    public int status;
    private  String  Name ;
    private String  UserId ;

    private Date subscription_exp ;
    private Clients clientCode;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Date getSubscription_exp(Date subscription_exp) {
        return this.subscription_exp;
    }

    public void setSubscription_exp(Date subscription_exp) {
        this.subscription_exp = subscription_exp;
    }

    public Clients getClientCode() {
        return clientCode;
    }

    public void setClientCode(Clients clientCode) {
        this.clientCode = clientCode;
    }


    public LoginResponse() {

    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


