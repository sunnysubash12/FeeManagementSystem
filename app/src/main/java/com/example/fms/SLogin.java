package com.example.fms;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SLogin extends AppCompatActivity {
    ImageView Home;
    Button button;
    Button dialog_close,dialog_reset;
    TextView forget;
    EditText USER, ID_s, PASS;
    EditText Em, Pas,comPass;

    String login_stu = "https://feemsystem.000webhostapp.com/Studentlogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);
        forget=findViewById(R.id.forget);
        button = findViewById(R.id.next);
        Home = findViewById(R.id.jame);
        USER = findViewById(R.id.stuuser);
        ID_s = findViewById(R.id.stuid);
        PASS = findViewById(R.id.stupass);
        Toolbar toolbar = findViewById(R.id.stu);
        setSupportActionBar(toolbar);
        Home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (USER.getText().toString().trim().equals("") || ID_s.getText().toString().trim().equals("") || PASS.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Details", Toast.LENGTH_SHORT).show();
                } else if (!USER.getText().toString().trim().equals("") && ID_s.getText().toString().trim().equals("") && !PASS.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Student ID", Toast.LENGTH_SHORT).show();
                } else if (USER.getText().toString().trim().equals("") && !ID_s.getText().toString().trim().equals("") && !PASS.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Username", Toast.LENGTH_SHORT).show();

                } else if (!USER.getText().toString().trim().equals("") && !ID_s.getText().toString().trim().equals("") && PASS.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz Enter your Password", Toast.LENGTH_SHORT).show();

                } else {
                    hideKeybaord(v);
                    Admin_Login(USER.getText().toString(), ID_s.getText().toString().trim(), PASS.getText().toString().trim());
                }

            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void Admin_Login(String Username, String STUID, String Password) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
//        loading.setVisibility(View.VISIBLE);
//        button.setVisibility(View.GONE);
        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(1, login_stu, new Response.Listener<String>() {
            public void onResponse(String response) {
                if (response.trim().equals("LOGIN")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    Intent id = new Intent(getApplicationContext(), Students.class);
                    id.putExtra("SID", STUID);
                    id.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(id);
                    finish();
//                    progressDialog.dismiss();

                } else if (response.trim().equals("LOGIN FAILED(INVALID USERNAME OR PASSWORD)")) {
                    USER.setText("");
                    ID_s.setText("");
                    PASS.setText("");

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
                n.put("id", STUID);
                return n;
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        View v = getLayoutInflater().inflate(R.layout.dialog,null);
        dialog_close=v.findViewById(R.id.close);
        dialog_reset=v.findViewById(R.id.reset);
        Em=v.findViewById(R.id.curentEmail);
        Pas=v.findViewById(R.id.Newpaaa);
        comPass=v.findViewById(R.id.confirmpaaa);
        alert.setView(v);
        dialog_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Em.getText().toString().trim().isEmpty() || Pas.getText().toString().trim().isEmpty() || comPass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Plz enter all details", Toast.LENGTH_SHORT).show();
                } else if (!Pas.getText().toString().trim().equals( comPass.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();

                } else if(Pas.getText().toString().trim().equals( comPass.getText().toString().trim())){
                    String npass = Pas.getText().toString().trim();
                    String Emi = Em.getText().toString().trim();
                    String cp = "https://feemsystem.000webhostapp.com/reset.php";
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com+";

                    if (Emi.matches(emailPattern))
                    {
                        uppass(cp,npass,Emi);
                    }
                    else
                    {
                        TextView t =v.findViewById(R.id.wrong);
                        t.setText("ex:- XYZ@mail.com");
                        t.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });

        AlertDialog dialog= alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

    public void uppass(String url, String newpa,String Email ){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();
//        ProgressDialog dialog = ProgressDialog.show(getApplicationContext(), "",
//                "Up", true);

//        String cnewpass = CNP.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Password Changed")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    Intent ne = new Intent(getApplicationContext(),SLogin.class);
                    progressDialog.dismiss();
                    startActivity(ne);
                    overridePendingTransition(0, 0);
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
                map.put("old", Email);
                map.put("npass",newpa);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }


}
