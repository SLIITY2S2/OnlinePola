package com.madd.mobileapp.models;

public class Shipping {

    private int shiId;
    private String fname;
    private String lname;
    private String streetAddress;
    private String location;
    private int phoneNo;


    public int getShiId() {
        return shiId;
    }

    public void setShiId(int shiId) {
        this.shiId = shiId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

}
