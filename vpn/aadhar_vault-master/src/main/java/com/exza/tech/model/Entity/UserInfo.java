package com.exza.tech.model.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="userInfo")
@Table(name="userInfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int Id;

    @Column(name = "userName")
    private String userName ;

    @Column(name = "password")
    private String password ;

    @ManyToOne
    @JoinColumn(name = "clientCode")
    private Clients clientCode ;

    public UserInfo() {
    }

    public UserInfo(int id, String userName, String password, Clients clientCode) {
        Id = id;
        this.userName = userName;
        this.password = password;
        this.clientCode = clientCode;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Clients getClientCode() {
        return clientCode;
    }

    public void setClientCode(Clients clientCode) {
        this.clientCode = clientCode;
    }
}
