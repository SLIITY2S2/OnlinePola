package com.madd.mobileapp.models;

public class Items {

    private int Itemcode;
    private String Itemname;
    private int qty;
    private double price;

    public Items(String itemname, int qty, double price) {

        Itemname = itemname;
        this.qty = qty;
        this.price = price;
    }

    public int getItemcode() {
        return Itemcode;
    }

    public void setItemcode(int itemcode) {
        Itemcode = itemcode;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
