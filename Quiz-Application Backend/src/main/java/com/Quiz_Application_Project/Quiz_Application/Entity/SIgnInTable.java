package com.Quiz_Application_Project.Quiz_Application.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="signInTabl")
public class SIgnInTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int signinId;
    private String email;
    private String username;
    private String password;
    private String imgUrl;
    public SIgnInTable() {
    }

    public SIgnInTable(String email, String username, String password, String imgUrl) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSigninId() {
        return signinId;
    }

    public void setSigninId(int signinId) {
        this.signinId = signinId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
