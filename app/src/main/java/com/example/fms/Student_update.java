package com.example.fms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class Student_update extends AppCompatActivity {

    EditText CFN, CLN, CEM, Cpass, bhtb;
    Button UDATE;
    ImageView to;
    Toolbar updaa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);
        TextView tv = findViewById(R.id.forme);
        to = findViewById(R.id.to);
        CFN = findViewById(R.id.first);
        CLN = findViewById(R.id.last);
        CEM = findViewById(R.id.Email);
        Cpass = findViewById(R.id.pass);
        UDATE = findViewById(R.id.Update);
        bhtb = findViewById(R.id.bhtb);
        updaa = findViewById(R.id.updaa);
        setSupportActionBar(updaa);
        Intent up = getIntent();
        String F = up.getStringExtra("f");
        String L = up.getStringExtra("l");
        String EM = up.getStringExtra("mail");
        String PA = up.getStringExtra("pass");
        String ID = up.getStringExtra("id");
        bhtb.setText(ID);
        CFN.setText(F);
        CLN.setText(L);
        Cpass.setText(PA);
        CEM.setText(EM);
        to.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Admin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        UDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CFN.getText().toString().trim().isEmpty() || CLN.getText().toString().trim().isEmpty() || CEM.getText().toString().trim().isEmpty() || Cpass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Plz enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    String f = CFN.getText().toString().trim();
                    String l = CLN.getText().toString().trim();
                    String Id = bhtb.getText().toString().trim();
                    String em = CEM.getText().toString().trim();
                    String pass = Cpass.getText().toString().trim();
                    String UD = "https://feemsystem.000webhostapp.com/UpdateStu.php";

                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com+";

                    if (em.matches(emailPattern)) {

                        update(UD, f, l, Id, em, pass);
                    } else {
                        tv.setText("ex:- XYZ@mail.com");
                        tv.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });


    }

    public void update(String URL, String F, String L, String Id, String E, String P) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Student Details Updated")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    Intent refresh = new Intent(getApplicationContext(), Admin.class);
                    startActivity(refresh);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();



                } else {
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
                map.put("F", F);
                map.put("L", L);
                map.put("S", Id);
                map.put("E", E);
                map.put("P", P);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
    @Override
    public void onBackPressed() {
        Intent setIntent= new Intent(getApplicationContext(),Admin.class);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(setIntent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}