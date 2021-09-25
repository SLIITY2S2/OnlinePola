package com.madd.mobileapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.madd.mobileapp.Adapter.CartAdapter;
import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;
import com.madd.mobileapp.models.Cart;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Database myDb;
    CartAdapter adapter;
    ArrayList<Cart> cartArrayList;
    Button btnconfirm;
    TextView total;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        recyclerView = findViewById(R.id.recyclerView);
        btnconfirm = (Button) findViewById(R.id.btnconf);
        total = (TextView)findViewById(R.id.tvTotal);

        myDb = new Database(this);

        double totalprice = CalTotalPrice();
        total.setText(String.valueOf(totalprice));

        cartArrayList = new ArrayList<>(myDb.getCartItems());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new CartAdapter(cartArrayList, recyclerView, this, getApplicationContext());
        recyclerView.setAdapter(adapter);



        //cartAdapter = new CartAdapter(total);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0 ; i < cartArrayList.size(); i++){
                    boolean isInserted = myDb.insertitem(cartArrayList.get(i).getPname(),
                            Integer.parseInt(String.valueOf(cartArrayList.get(i).getQty())),
                            Double.valueOf(String.valueOf(cartArrayList.get(i).getPprice())));
                    if(isInserted){
                        for(int x= 0; x < cartArrayList.size(); x++){

                            myDb.deleteCartItem(String.valueOf(cartArrayList.get(x).getId()));
                            cartArrayList.remove(x);
                            if(cartArrayList.isEmpty()){
                                Intent intent = new Intent(SummaryActivity.this,Shipping.class);
                                startActivity(intent);
                            }
                        }
                    }
                    else{
                        Toast.makeText(SummaryActivity.this, "Data not inserted to the table", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public double CalTotalPrice(){
        double total = 0;
        cartArrayList = new ArrayList<>(myDb.getCartItems());
            if(cartArrayList.isEmpty()){
                Toast.makeText(SummaryActivity.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
            }
            else{
                for(int y = 0 ; y < cartArrayList.size(); y++){
                    double price = cartArrayList.get(y).getPprice();
                    total = total + price;
                }
            }
        return total;
    }


}
