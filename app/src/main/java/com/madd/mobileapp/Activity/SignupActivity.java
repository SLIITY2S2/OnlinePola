package com.madd.mobileapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;
import com.madd.mobileapp.ui.home.HomeFragment;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private TextView cname, cmobileno, cemail, cpassword ;
    private Button btninsert;
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //get database connection
        myDb = new Database(this);

        //binding
        cname = findViewById(R.id.etname);
        cmobileno = findViewById(R.id.etphone);
        cemail = findViewById(R.id.etemail);
        cpassword = findViewById(R.id.etpassword);
        btninsert = findViewById(R.id.btninsert);
    }

    public void OnClickinsertCustomer(View v){
        if (v == btninsert) {
            String name = cname.getText().toString().trim();
            String mobileno = cmobileno.getText().toString().trim();
            String email =cemail.getText().toString().trim();
            String password = cpassword.getText().toString().trim();
            if(name.equals("")){
                Toast.makeText(this, "Please Enter names", Toast.LENGTH_SHORT).show();
            }else if(email.equals("")){
                Toast.makeText(this, "Please Enter email", Toast.LENGTH_SHORT).show();
            }else if(password.equals("")){
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }else if(mobileno.length()==0){
                Toast.makeText(this, "Please Enter Mobile no", Toast.LENGTH_SHORT).show();
            }else if(password.length() < 6){
                Toast.makeText(this, "Password length should be greater than 6 ", Toast.LENGTH_SHORT).show();
            }else if(!isValidEmailId(email)) {
                Toast.makeText(this, "Email is invalid ", Toast.LENGTH_SHORT).show();
            }else{
                boolean isInserted = myDb.insertCustomer(name, Integer.parseInt(mobileno),email,password);
                if (isInserted){
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    cname.setText("");
                    cmobileno.setText("");
                    cemail.setText("");
                    cpassword.setText("");
                    Intent i = new Intent(SignupActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    HomeFragment myObj = new HomeFragment();
                    myObj.setArguments(bundle);
                    startActivity(i);
                }else{
                    Toast.makeText(this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}