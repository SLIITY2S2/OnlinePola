package com.madd.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.madd.mobileapp.Adapter.CartAdapter;
import com.madd.mobileapp.Adapter.ProductAdapter;
import com.madd.mobileapp.R;
import com.madd.mobileapp.models.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MycartActivity extends AppCompatActivity {

    private List<Product> mCartList;
    RecyclerView recyclerView;
    ArrayList id;
    ArrayList pimage;
    ArrayList<Product> pname = new ArrayList<>();
    ArrayList pprice;
    ArrayList<String> qty;
    CartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);


        mCartList = (ArrayList<Product>)getIntent().getSerializableExtra("productArrayList");

/*

        for(int i = 0 ;i < mCartList.size(); i++ ){
            adapter  = new CartAdapter(this, recyclerView,mCartList.get(i).getProductName(),mCartList.get(i).getQty());

        }
*/
        recyclerView = findViewById(R.id.recycle);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






/*
        mCartList = ShoppingCartHelper.getCartList();

        for(int i=0 ; i<mCartList.size(); i++){
            mCartList.get(i).selected = false;
        }
        final ListView listView = (ListView) findViewById(R.id.ListViewCatalog);
        productAdapter = new ProductAdapter(mCartList, getLayoutInflater(),true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        if(productAdapter != null){
            productAdapter.notifyDataSetChanged();
        }

        double total = 0;
        for(Product p : mCartList){
            int quantity = ShoppingCartHelper.getProductQuantity(p);
            double price = p.getPrice();
            total = total + (price * quantity);
        }

        TextView priceview = (TextView) findViewById(R.id.checkout_amount);
        priceview.setText("Rs."+total);*/
    }
}