package com.madd.mobileapp.models;

public class Customer {
    private int cusId;
    private String name;
    private int mobile;
    private String email;
    private String password;


    public int getIdUser() {
        return cusId;
    }

    public void setIdUser(int idUser) {
        this.cusId = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
