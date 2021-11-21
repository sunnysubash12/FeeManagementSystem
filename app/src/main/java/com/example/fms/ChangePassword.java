package com.example.fms;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {


    ImageView back;

    EditText CP, NP, CNP,bhb;
    Button change;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        back = findViewById(R.id.back);
        CP = findViewById(R.id.curentpass);
        NP = findViewById(R.id.Newpass);
        CNP = findViewById(R.id.confirmpass);
        change = findViewById(R.id.change);
        bhb=findViewById(R.id.bhb);
        Toolbar toolbar = findViewById(R.id.n);
        setSupportActionBar(toolbar);


        Intent idd = getIntent();
        String i = idd.getStringExtra("SI");
        bhb.setText(i);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CP.getText().toString().trim().isEmpty() || NP.getText().toString().trim().isEmpty() || CNP.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Plz enter all details", Toast.LENGTH_SHORT).show();
                } else if (!NP.getText().toString().trim().equals( CNP.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();

                } else if(NP.getText().toString().trim().equals( CNP.getText().toString().trim())){
                    String newpass = NP.getText().toString().trim();
                    String Oldpass = CP.getText().toString().trim();
                    String Id=bhb.getText().toString().trim();
                    String cp = "https://feemsystem.000webhostapp.com/Updatepassword.php";
                    updatepass(cp,Oldpass,newpass,Id);

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });
    }

    public void updatepass(String URL,String Oldpass, String Newpass, String Id) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();
//        ProgressDialog dialog = ProgressDialog.show(getApplicationContext(), "",
//                "Up", true);

//        String cnewpass = CNP.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Password Changed")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    progressDialog.dismiss();
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }

//                else if (response.trim().equals("Something went Wrong")) {
//                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

//                } else if (response.trim().equals("Plz enter Correct Current Password")) {
//                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("oldpass", Oldpass);
                map.put("pass",Newpass);
                map.put("student",Id);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
