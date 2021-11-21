package com.example.fms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SecQues extends AppCompatActivity {

    ImageView Home22;
    Button A,B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_ques);
        Home22=findViewById(R.id.jamel);
        A=findViewById(R.id.quest);
        B=findViewById(R.id.upquest);
        Toolbar toolbar =findViewById(R.id.val);
        setSupportActionBar(toolbar);
        Home22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Students.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),answer.class);
                startActivity(i);
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),update_ans.class);
                startActivity(i);
            }
        });

    }
}