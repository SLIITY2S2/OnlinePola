package com.madd.mobileapp.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.madd.mobileapp.Adapter.ProductAdapter;
import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;
import com.madd.mobileapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ActivityMainBinding binding;
    NavController navController;

    ArrayList<byte[]> pimage;
    ArrayList<String> pname;
    ArrayList<String> pprice;
    ArrayList<String> qty;
    ArrayList<String> id;
    RecyclerView recyclerView;
    Button checkout;
    TextView showValue, quan;
    TextView itemcount,fn;

    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_contact)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        showValue = (TextView) findViewById(R.id.checkout_amount);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        itemcount = (TextView) findViewById(R.id.item_count);
        quan = (TextView) findViewById(R.id.iteam_amount);
        checkout = (Button) findViewById(R.id.chekout);

        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = 1500;
        recyclerView.setLayoutParams(params);

        myDb = new Database(this);

        pimage = new ArrayList<byte[]>();
        pname = new ArrayList<>();
        pprice = new ArrayList<>();
        id = new ArrayList<>();
        qty = new ArrayList<>();

        displaydata();

        ProductAdapter adapter = new ProductAdapter(this, recyclerView, id, pname, pprice, pimage);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SummaryActivity.class);
                startActivity(i);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void displaydata() {
        Cursor cursor = myDb.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                id.add(cursor.getString(0));
                pname.add(cursor.getString(1));
                pprice.add(cursor.getString(2));
                pimage.add(cursor.getBlob(3));

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}