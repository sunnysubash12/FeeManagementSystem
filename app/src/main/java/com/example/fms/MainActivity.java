package com.example.fms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            public void run() {
               startActivity(new Intent(MainActivity.this, Home.class));
               overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
               finish();
            }
        }, 2000);
    }
}