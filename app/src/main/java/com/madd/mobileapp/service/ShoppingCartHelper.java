package com.madd.mobileapp.service;

import com.madd.mobileapp.models.Cart;
import com.madd.mobileapp.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
    Product p;

    private static Map<ArrayList<Product>, Cart> cartMap = new HashMap<ArrayList<Product>, Cart>();



    public static void setQuantity(ArrayList<Product> pname, int quantity) {
        Product p = new Product();
        // Get the current cart entry
        Cart curEntry = cartMap.get(p.getProductName());

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(p.getProductName());
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new Cart(p.getProductName(), quantity);
            cartMap.put(pname, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQty(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        Cart curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQty();

        return 0;
    }

    public static void removeProduct(String product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<>(cartMap.keySet().size());
        for(List<Product> p : cartMap.keySet()) {
            cartList.add((Product) p);
        }

        return cartList;
    }


}
