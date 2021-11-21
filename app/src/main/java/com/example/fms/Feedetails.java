package com.example.fms;

import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Feedetails extends AppCompatActivity {
    String Add_inv, viewfee;
    ImageView b, caal;
    ImageButton e1, e2, e3, e4, e5, e6, e7, e8;


    Spinner spinner, H;
    TextView r, viewtf, viewef, viewsf, viewit, viewbc, viewsc, viewdd, views;
    EditText A, B, C, D, E, F,X,Y;
    int i;
    ArrayList<String> Bill = new ArrayList<>();
    ArrayAdapter<String> bill;
    ArrayList<String> inv = new ArrayList<>();
    LinearLayout hu;
    Button delfee, upfee;
    String TAG = "Feedetails";
    String dat = "";
    private ProgressBar progressBar;
    private int progressStatus = 0;
    Handler handle= new Handler();
    String[] fee_Statu = {"Update", "Paid", "Pending"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedetails);
        Toolbar toolbar = findViewById(R.id.o);
        setSupportActionBar(toolbar);
        b = findViewById(R.id.b);
        r = findViewById(R.id.j);
        delfee = findViewById(R.id.delfee);
        X=findViewById(R.id.x);
        Y=findViewById(R.id.y);
        spinner = findViewById(R.id.billno);
        viewtf = findViewById(R.id.vtf);
        viewef = findViewById(R.id.vef);
        viewsf = findViewById(R.id.vsf);
        viewit = findViewById(R.id.vit);
        viewbc = findViewById(R.id.vbf);
        viewsc = findViewById(R.id.ssc);
        viewdd = findViewById(R.id.sdd);
        progressBar = findViewById(R.id.prog);
        progressBar.setMax(100);
        views = findViewById(R.id.ss);
        hu = findViewById(R.id.hu);
        e1 = findViewById(R.id.upa);
        e2 = findViewById(R.id.upb);
        e3 = findViewById(R.id.upc);
        e4 = findViewById(R.id.upd);
        e5 = findViewById(R.id.upe);
        e6 = findViewById(R.id.upf);
        e8 = findViewById(R.id.uph);

        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);
        F = findViewById(R.id.F);
        H = findViewById(R.id.H);
        upfee = findViewById(R.id.upfee);

        ArrayAdapter nw =new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,fee_Statu);
        H.setAdapter(nw);

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setVisibility(View.GONE);
                A.setVisibility(View.VISIBLE);
                viewtf.setVisibility(View.GONE);
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e2.setVisibility(View.GONE);
                B.setVisibility(View.VISIBLE);
                viewef.setVisibility(View.GONE);


            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e3.setVisibility(View.GONE);
                C.setVisibility(View.VISIBLE);
                viewsf.setVisibility(View.GONE);


            }
        });
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e4.setVisibility(View.GONE);
                D.setVisibility(View.VISIBLE);
                viewit.setVisibility(View.GONE);


            }
        });
        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e5.setVisibility(View.GONE);
                E.setVisibility(View.VISIBLE);
                viewbc.setVisibility(View.GONE);


            }
        });
        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e6.setVisibility(View.GONE);
                F.setVisibility(View.VISIBLE);
                viewsc.setVisibility(View.GONE);


            }

        });

//        caal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Calendar caal = Calendar.getInstance();
////                int year = caal.get(Calendar.YEAR);
////                int month = caal.get(Calendar.MONTH);
////                int day = caal.get(Calendar.DAY_OF_MONTH);
////
////                DatePickerDialog diialog = new DatePickerDialog(
////                        getApplicationContext(),
////                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
////                        DateSetListener,
////                        year, month, day);
////                diialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////                diialog.show();
//
//
//            }
//
//        });

        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e8.setVisibility(View.GONE);
                H.setVisibility(View.VISIBLE);
                views.setVisibility(View.GONE);


            }

        });

//        DateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                Log.d(TAG, "onDateSet: yyy/mm/dd: " + year + "/" + month + "/" + day);
//
//                dat = year + "/" + month + "/" + day;
//                G.setText(dat);
//
//            }
//        };


        upfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tution_fe = A.getText().toString().trim();
                String Exam_fe = B.getText().toString().trim();
                String Sports_fe = C.getText().toString().trim();
                String It_cos = D.getText().toString().trim();
                String Book_cos = E.getText().toString().trim();
                String Statio = F.getText().toString().trim();
                String duedat = X.getText().toString().trim();
                String Sta = String.valueOf(H.getSelectedItem());
                Y.setText(Sta);
                String yq= Y.getText().toString().trim();
                String ibil = String.valueOf(spinner.getSelectedItem());


                String up = "https://feemsystem.000webhostapp.com/upfee.php";
                updatefee(up, ibil, Tution_fe, Exam_fe, Sports_fe, It_cos, Book_cos, Statio, duedat, yq);


            }
        });


        Intent obj = getIntent();
        String no = obj.getStringExtra("st_id");
        r.setText(no);
        Add_inv = "https://feemsystem.000webhostapp.com/Dropdownfees.php?id=" + no;

        inv.add("Select Invoice number");
        ainv();


        bill = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, inv);
        spinner.setAdapter(bill);


        delfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bil = String.valueOf(spinner.getSelectedItem());
                String delurl = "https://feemsystem.000webhostapp.com/j.php?iudd=" + bil;
                Delfee(delurl);

            }
        });

        Button button = findViewById(R.id.fd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inbil = String.valueOf(spinner.getSelectedItem());

                if (inbil.equals("Select Invoice number")) {
                    hu.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Plz Select correct Invoice number", Toast.LENGTH_SHORT).show();
                } else {
                    viewfee = "https://feemsystem.000webhostapp.com/ViewFees.php?in=" + inbil;
                    hu.setVisibility(View.VISIBLE);
                    getfee();
                }


            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });
    }

    private void ainv() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Add_inv, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("all");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        list.add(object.getString("Invoice_number"));
                    }
                    inv.addAll(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void getfee() {
        hu.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 20;
                    // Update the progress bar and display the
                    //current value in the text view
                    handle.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, viewfee, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("all");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        viewtf.setText(object.getString("Tution_Fee"));
                        viewef.setText(object.getString("Exam_Fee"));
                        viewsf.setText(object.getString("Sports_Fee"));
                        viewit.setText(object.getString("It_Fee"));
                        viewbc.setText(object.getString("Book_Fee"));
                        viewsc.setText(object.getString("Stationary_Fee"));
                        viewdd.setText(object.getString("Due_Date"));
                        views.setText(object.getString("Status"));

                        A.setText(object.getString("Tution_Fee"));
                        B.setText(object.getString("Exam_Fee"));
                        C.setText(object.getString("Sports_Fee"));
                        D.setText(object.getString("It_Fee"));
                        E.setText(object.getString("Book_Fee"));
                        F.setText(object.getString("Stationary_Fee"));
                        X.setText(object.getString("Due_Date"));
                        Y.setText(object.getString("Status"));



                        if (views.getText().toString().equals("Pending")) {
                            views.setTextColor(Color.parseColor("#FF0000"));
                        } else {
                            views.setTextColor(Color.parseColor("#00FF00"));
                        }
                        progressBar.setVisibility(View.GONE);
                        hu.setVisibility(View.VISIBLE);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void Delfee(String URL) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if (response.trim().equals("Deleted")) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();

//                Intent intent = new Intent(getApplicationContext(), Feedetails.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(0, 0);
//                hu.setVisibility(View.GONE);


//                Intent a = new Intent(context, Admin.class);
//                context.startActivity(a);
//                progressDialog.dismiss();


//                }else if(response.trim().equals("Failed")){
//                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void updatefee(String UR, String inv, String TFee, String EFee, String SFee, String ITee, String BCee, String SCee, String duee, String Statuss) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

//        String cnewpass = CNP.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Fees Updated")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();



//                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//                    finish();
//                    e1.setVisibility(View.VISIBLE);
//                    e2.setVisibility(View.VISIBLE);
//                    e3.setVisibility(View.VISIBLE);
//                    e4.setVisibility(View.VISIBLE);
//                    e5.setVisibility(View.VISIBLE);
//                    e6.setVisibility(View.VISIBLE);
//                    e7.setVisibility(View.VISIBLE);
//                    e8.setVisibility(View.VISIBLE);
//                    viewtf.setVisibility(View.VISIBLE);;
//                    viewef.setVisibility(View.VISIBLE);
//                    viewsf.setVisibility(View.VISIBLE);
//                    viewit.setVisibility(View.VISIBLE);
//                    viewbc.setVisibility(View.VISIBLE);
//                    viewsc.setVisibility(View.VISIBLE);
//                    viewdd.setVisibility(View.VISIBLE);
//                    views.setVisibility(View.VISIBLE);
//                    A.setVisibility(View.GONE);
//                    B.setVisibility(View.GONE);
//                    C.setVisibility(View.GONE);
//                    D.setVisibility(View.GONE);
//                    E.setVisibility(View.GONE);
//                    F.setVisibility(View.GONE);
//                    G.setVisibility(View.GONE);
//                    H .setVisibility(View.GONE);


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
                map.put("invoic", inv);
                map.put("tutio", TFee);
                map.put("exa", EFee);
                map.put("sport", SFee);
                map.put("i", ITee);
                map.put("boo", BCee);
                map.put("sta", SCee);
                map.put("dat", duee);
                map.put("statu", Statuss);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}