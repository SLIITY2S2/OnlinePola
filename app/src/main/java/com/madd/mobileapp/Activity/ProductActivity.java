package com.madd.mobileapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;

public class ProductActivity extends AppCompatActivity implements ProductActivityInterface {

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";

    private Button btnOpenGallery, btninsert;
    private AppCompatImageView imgView;
    private Uri selectedImageUri;
    private TextView pname, pprice;

    Database myDb;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        myDb = new Database(this);

        btnOpenGallery = findViewById(R.id.btn_upload);
        btninsert = findViewById(R.id.btninsert);
        imgView = findViewById(R.id.imgimage);
        pname = findViewById(R.id.et_pname);
        pprice = findViewById(R.id.et_pprice);
    }
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imgView.setImageURI(selectedImageUri);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == btnOpenGallery){
            openImageChooser();
        }
    }

    public void onClickinsertProduct(View v) {
        if (v == btninsert) {
            String pName = pname.getText().toString().trim();
            String pPrice = pprice.getText().toString().trim();

            if (pName.equals("") || pPrice.equals("")) {

            } else {
                boolean isInserted = myDb.insertData(pName, pPrice, imgView);

                if (isInserted == true){

                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    pname.setText("");
                    pprice.setText("");
                    imgView.setImageURI(Uri.parse(""));
                }
                else{
                    Toast.makeText(this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}