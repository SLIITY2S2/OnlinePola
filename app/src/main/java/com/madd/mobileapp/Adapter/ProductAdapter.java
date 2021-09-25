package com.madd.mobileapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;
import com.madd.mobileapp.models.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private ArrayList<Product> productList;
    RecyclerView recyclerView;
    private ArrayList id, pname, pprice, pimage, qty;
    Context context;
    int count=0;
    private Database myDb;
    List<Product> mCartList;
    LayoutInflater layoutInflater;
    Product p;
    String fname;
    String ppname, price;

    boolean b;




    public ProductAdapter(Context context, RecyclerView recyclerView, ArrayList id, ArrayList pname, ArrayList pprice, ArrayList pimage){

        this.context = context;
        this.recyclerView = recyclerView;
        this.id = id;
        this.pname = pname;
        this.pprice = pprice;
        this.pimage = pimage;

    }



    public ProductAdapter(List<Product> mCartList, LayoutInflater layoutInflater, boolean b) {
        this.mCartList = mCartList;
        this.layoutInflater = layoutInflater;
        this.b = b;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list,parent,false);

        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productname.setText( String.valueOf(pname.get(position)));
        holder.productprice.setText(String.valueOf(pprice.get(position)));
        holder.imageView.setImageURI(Uri.parse((String.valueOf((pimage.get(position))))));


        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                holder.display.setText(String.valueOf(count));


            }
        });

        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (count == 0) {
                    Toast.makeText(context, "Cant decrease quantity less than 0", Toast.LENGTH_SHORT).show();
                }
                else{
                    count--;
                    holder.display.setText(String.valueOf(count));
                }


            }
        });


        holder.cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb = new Database(context);
                  int quantity =   Integer.parseInt(String.valueOf(holder.display.getText()));
                  double price = Double.valueOf(String.valueOf(holder.productprice.getText()));
                  double total = price * quantity;
                     boolean isInserted = myDb.insertCart1(String.valueOf(holder.productname.getText()),total,quantity);

                    if(isInserted){
                        Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }


    public ArrayList<Product> getSelectedProducts(){
        return productList;
    }


    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView productname, productprice, display, itemcount, fname;
        Button btnIncrease, btnDecrease ;
        Button cartImage;
        ConstraintLayout parentLayout;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            productname = (TextView)itemView.findViewById(R.id.tvpname);
            productprice = (TextView)itemView.findViewById(R.id.tvPPrice);
            btnIncrease = (Button) itemView.findViewById(R.id.btnadd);
            btnDecrease = (Button) itemView.findViewById(R.id.btnremove);
            display = (TextView) itemView.findViewById(R.id.iteam_amount);
            cartImage = (Button) itemView.findViewById(R.id.btnaddc);
            itemcount = (TextView) itemView.findViewById(R.id.item_count);
            fname = (TextView)itemView.findViewById(R.id.fname);
            parentLayout=itemView.findViewById(R.id.parentLayout);

        }


    }


}
