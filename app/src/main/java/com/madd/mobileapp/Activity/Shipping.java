package com.madd.mobileapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.madd.mobileapp.Database.Database;
import com.madd.mobileapp.R;

public class Shipping extends AppCompatActivity {

    TextView fname, lname, streetAddress, phoneno;
    Button submit;
    Spinner spinner;
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        myDb = new Database(this);

        fname = findViewById(R.id.etfirstname);
        lname = findViewById(R.id.etlastname);
        streetAddress = findViewById(R.id.etshippingaddress);
        phoneno = findViewById(R.id.edcontact);
        submit = (Button) findViewById(R.id.btnSubmit);
        spinner = (Spinner) findViewById(R.id.city_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void OnClickinsertShipping(View v) {
        if (v == submit) {
            String firstname = fname.getText().toString().trim();
            String lastname = lname.getText().toString().trim();
            String stddress = streetAddress.getText().toString().trim();
            String phone = phoneno.getText().toString().trim();
            String location = spinner.getSelectedItem().toString();

            if(firstname.equals("")){
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
            }
            else if(lastname.equals("")){
                Toast.makeText(this, "Please Enter lastname", Toast.LENGTH_SHORT).show();
            }
            else if(stddress.equals("")){
                Toast.makeText(this, "Please Enter address", Toast.LENGTH_SHORT).show();
            }
            else if(phone.equals("")){
                Toast.makeText(this, "Please Enter phone number", Toast.LENGTH_SHORT).show();
            }
            else if(location.equals("")){
                Toast.makeText(this, "Please Enter location", Toast.LENGTH_SHORT).show();
            }

            else{
                boolean isInserted = myDb.insertShipping(firstname,lastname,stddress, location, Integer.parseInt(phone));

                if (isInserted){
                    Toast.makeText(this, "Data Inserted. Thank you", Toast.LENGTH_SHORT).show();

                    fname.setText("");
                    lname.setText("");
                    streetAddress.setText("");
                    phoneno.setText("");

                    Intent i = new Intent(Shipping.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }


        }

    }

}