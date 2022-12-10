package com.exza.tech.model.requestbody;

public class Register {

    private String username ;
    private String password ;
    private String companyName ;
    private String role ;
    private String apikey ;
    private String emailId ;


    public Register(String username, String password, String companyName, String role, String apikey  , String emailId) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.role = role;
        this.apikey = apikey;
        this.emailId=emailId ;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Register() {
    }

    @Override
    public String toString() {
        return "register{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", apikey='" + apikey + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
