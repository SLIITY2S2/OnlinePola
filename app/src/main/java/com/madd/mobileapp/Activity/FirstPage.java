package com.madd.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.madd.mobileapp.R;

public class FirstPage extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_first_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(FirstPage.this, SignupActivity.class);

                startActivity(i);

                finish();

            }
        }, SPLASH_SCREEN_TIME_OUT);


    }
}