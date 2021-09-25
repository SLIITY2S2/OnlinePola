package com.madd.mobileapp.models;

import java.io.Serializable;

public class Product implements Serializable {


    private int productCode;
    private String productName;
    private double price;
    private byte[] productImage;
    private int qty;

    public Product() {

    }
    public Product(String productName, int qty) {
        this.productName = productName;
        this.qty = qty;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
