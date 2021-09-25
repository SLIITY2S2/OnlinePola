package com.madd.mobileapp.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madd.mobileapp.Activity.SummaryActivity;
import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;
import com.madd.mobileapp.models.Cart;
import com.madd.mobileapp.models.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    ArrayList<Cart> cartList;
    Activity activity;
    Context context;
    int count=0;
    private Database myDb;
    RecyclerView recyclerView;
    private int id;
    private String pname;
    private float pprice;
    private int qty;
    ArrayList<String> price;;
    TextView total;

    public CartAdapter(ArrayList<Cart> arrayList,RecyclerView recyclerView, Activity activity, Context context) {
        this.cartList = arrayList;
        this.activity = activity;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public CartAdapter(TextView total) {
        this.total = total;
    }


    @NotNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlist,parent,false);

        return new CartAdapter.ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.orderproduct.setText(String.valueOf(cartList.get(position).getPname()));
        holder.orderprice.setText(String.valueOf(cartList.get(position).getPprice()));
        holder.qty.setText(String.valueOf(cartList.get(position).getQty()));

        myDb = new Database(context);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText qty;
                Button submit;

                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                dialog.setContentView(R.layout.dialog_cart_update);
                params.copyFrom(dialog.getWindow().getAttributes());
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                qty = (EditText)dialog.findViewById(R.id.edqty);
                submit = (Button)dialog.findViewById(R.id.btn_dialog_cart_update);

                qty.setText(String.valueOf(cartList.get(position).getQty()));

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(qty.getText().toString().isEmpty()){
                            qty.setError("Please Enter quantity");
                        }
                        else{
                            double total = CalTotal(Integer.parseInt(qty.getText().toString()), Double.valueOf(cartList.get(position).getPprice()));
                            myDb.updateCartItem(Integer.parseInt(String.valueOf(cartList.get(position).getId())), total ,Integer.parseInt(qty.getText().toString()));
                            cartList.get(position).setQty(Integer.parseInt(qty.getText().toString()));
                            cartList.get(position).setPprice(total);
                            holder.orderprice.setText(String.valueOf(cartList.get(position).getPprice()));
                            dialog.cancel();
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        });


             holder.btnDelete.setOnClickListener(new AdapterView.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String id = String.valueOf(cartList.get(position).getId());
                     myDb.deleteCartItem(id);
                     cartList.remove(position);
                     notifyDataSetChanged();
                     Toast.makeText(activity, "Item is Deleted", Toast.LENGTH_SHORT).show();

                 }
             });


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderproduct, orderprice,qty;
        ImageButton btnEdit, btnDelete;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            orderproduct = itemView.findViewById(R.id.orederproduct);
            orderprice = itemView.findViewById(R.id.orderprice);
            qty = itemView.findViewById(R.id.qty);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public double CalTotal(int qt, double price){

        return qt * price ;
    }

    public double CalTotalPrice(){

        double total = 0;
        cartList = new ArrayList<>(myDb.getCartItems());
        if(cartList.isEmpty()){
            Toast.makeText(context, "Cart is Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            for(int y = 0 ; y < cartList.size(); y++){
                double price = cartList.get(y).getPprice();
                total = total + price;
            }
        }
        return total;
    }


}
