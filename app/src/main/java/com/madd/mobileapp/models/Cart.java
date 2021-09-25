package com.madd.mobileapp.models;

import java.util.ArrayList;

public class Cart {

    private int id;
    private String pname;
    private double pprice;
    private int qty;


    public Cart(String product, int quantity) {
        pname = product;
        qty = quantity;
    }

    public Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }


    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
