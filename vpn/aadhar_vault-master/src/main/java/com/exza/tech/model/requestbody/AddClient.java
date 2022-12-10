package com.exza.tech.model.requestbody;

public class AddClient {

    private String clientName;

    private Integer YearsOfSubscription;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getYearsOfSubscription() {
        return YearsOfSubscription;
    }

    public void setYearsOfSubscription(Integer yearsOfSubscription) {
        YearsOfSubscription = yearsOfSubscription;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public AddClient(String clientName, Integer yearsOfSubscription, String apikey) {
        this.clientName = clientName;
        YearsOfSubscription = yearsOfSubscription;
        this.apikey = apikey;
    }

    private String apikey;

    public AddClient() {
    }
}
