package com.example.fms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ALogin extends AppCompatActivity {
    private static String url = "https://feemsystem.000webhostapp.com/login.php";
    String URL_LOGIN = "https://feemsystem.000webhostapp.com/Adminlogin.php";
    EditText ap;
    EditText aun;
    TextView req;
    Button button;
    ImageView Home2;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alogin);
        req=findViewById(R.id.re);
        aun = findViewById(R.id.aun);
        ap = findViewById(R.id.ap);
        Home2=findViewById(R.id.home2);
        loading = findViewById(R.id.loading);
        button = findViewById(R.id.next2);
        Toolbar toolbar =findViewById(R.id.adm);
        setSupportActionBar(toolbar);
        Home2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (aun.getText().toString().trim().equals("") && ap.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Details", Toast.LENGTH_SHORT).show();
                } else if (!aun.getText().toString().trim().equals("") && ap.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Password", Toast.LENGTH_SHORT).show();
                } else if (aun.getText().toString().trim().equals("") && !ap.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Username", Toast.LENGTH_SHORT).show();
//                } else if (!aun.getText().toString().trim().equals("Admin123") || !ap.getText().toString().trim().equals("Teacher")) {
//                    Toast.makeText(getApplicationContext(), "Username Or Password is Invalid", Toast.LENGTH_SHORT).show();
                } else {
//                    loading.setVisibility(View.VISIBLE);
//                    button.setVisibility(View.GONE);
//                    Intent intent = new Intent(getApplicationContext(), Admin.class);
//                    Toast.makeText(getApplicationContext(), "Successfully Signed in", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                    finish();
                    Admin_Login(aun.getText().toString(), ap.getText().toString().trim());
                    hideKeybaord(v);
                }
//                loading.setVisibility(View.VISIBLE);
//                button.setVisibility(View.GONE);
//                Intent intent = new Intent(getApplicationContext(), Admin.class);
//                Toast.makeText(getApplicationContext(), "Successfully Signed in", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//                finish();
            }
        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getApplicationContext(),"Plz wait for 24h your request for new Password has been sent to our Technical Department",Toast.LENGTH_LONG).show();


            }
        });


    }

    private void Admin_Login(String Username, String Password) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
//        loading.setVisibility(View.VISIBLE);
//        button.setVisibility(View.GONE);
        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(1, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                if (response.trim().equals("LOGIN")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ALogin.this.getApplicationContext(), Admin.class));
                    finish();


                } else if (response.trim().equals("LOGIN FAILED(INVALID USERNAME OR PASSWORD)")) {
                    aun.setText(""); ap.setText("");
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                loading.setVisibility(View.GONE);
//                button.setVisibility(View.VISIBLE);
                progressDialog.dismiss();


            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                Map n = new HashMap();
                n.put("user", Username);
                n.put("pass", Password);
                return n;
            }
        });
    }
    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
