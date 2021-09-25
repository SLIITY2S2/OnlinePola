package com.madd.mobileapp;


import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.madd.mobileapp.Adapter.CartAdapter;
import com.madd.mobileapp.models.Cart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class IT20221850{

    private static CartAdapter cartAdapter;
    private static int qty;
    private static double price;
    private static ArrayList<Cart> cartArrayList;
    private static RecyclerView recyclerView;
    private static Context context;
    private static Activity activity;

    @BeforeClass
    public static void init(){
        cartAdapter = new CartAdapter(cartArrayList,recyclerView,activity,context);
    }
    @Before
    public void setup(){
        qty = 3;
        price = 120.00;
    }
    @Test
    public void TestCalTotal(){
       double total = cartAdapter.CalTotal(qty,price);
       assertEquals(360.00,total,0.0);
    }
    @After
    public void clear(){
        qty = 0;
        price = 0.0;
    }
    @AfterClass
    public static void clearAll(){
        cartAdapter = null ;
    }
}