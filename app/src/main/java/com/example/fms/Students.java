package com.example.fms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class Students extends AppCompatActivity {
    String url1;
    String Add_in,viewfe;
    Spinner KOL;
    TextView name,stf,sef,ssf,sit,sbc,ssc,sdd,ss,NAME,SID,SMAIL;
    Button button;
    ArrayAdapter<String> Bill;
    ArrayList<String> invo = new ArrayList<>();
    String no;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    Handler handler = new Handler();
    LinearLayout ll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
          Toolbar toolbar=findViewById(R.id.y);
          setSupportActionBar(toolbar);
        KOL=findViewById(R.id.KOL);
        ll=findViewById(R.id.ll);
        stf = findViewById(R.id.STUf);
        sef = findViewById(R.id.SEF);
        ssf = findViewById(R.id.SSF);
        sit = findViewById(R.id.SIT);
        sbc = findViewById(R.id.SBF);
        ssc = findViewById(R.id.SSC);
        sdd = findViewById(R.id.SSD);
        ss = findViewById(R.id.SSS);
        name=findViewById(R.id.head);
        NAME=findViewById(R.id.STUname);
        SID=findViewById(R.id.STUID);
        SMAIL=findViewById(R.id.STUM);
        button=findViewById(R.id.SGET);
        progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);

        Intent id=getIntent();
        no=id.getStringExtra("SID");

        Add_in="https://feemsystem.000webhostapp.com/Dropdownfees.php?id="+no;
        invo.add("Select Invoice number");
        url1="https://feemsystem.000webhostapp.com/std.php?idd="+no;
        inv();
        std();



        Bill =new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,invo);
        KOL.setAdapter(Bill);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  geti = String.valueOf(KOL.getSelectedItem());


                if (geti.equals("Select Invoice number")) {
                    ll.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Plz Select correct Invoice number", Toast.LENGTH_SHORT).show();
                } else {
                    viewfe="https://feemsystem.000webhostapp.com/ViewFees.php?in="+geti;
                    ll.setVisibility(View.VISIBLE);
                    fee();
                }

            }
        });

    }

    private void inv() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Add_in, new Response.Listener<String>() {
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
                    invo.addAll(list);
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

    private void fee() {
        ll.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 5;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, viewfe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("all");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        stf.setText(object.getString("Tution_Fee"));
                        sef.setText(object.getString("Exam_Fee"));
                        ssf.setText(object.getString("Sports_Fee"));
                        sit.setText(object.getString("It_Fee"));
                        sbc.setText(object.getString("Book_Fee"));
                        ssc.setText(object.getString("Stationary_Fee"));
                        sdd.setText(object.getString("Due_Date"));
                        ss.setText(object.getString("Status"));

                        if(ss.getText().toString().equals("Pending")){
                            ss.setTextColor(Color.parseColor("#FF0000"));
                        }else {
                            ss.setTextColor(Color.parseColor("#00FF00"));
                        }

                        progressBar.setVisibility(View.GONE);
                        ll.setVisibility(View.VISIBLE);



                    }
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

    private void std() {

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 20;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("all");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String fn =object.getString("First_Name");
                        String ln =object.getString("Last_Name");
                        name.setText("Welcome"+" "+fn+" "+ln);
                        NAME.setText(fn+" "+ln);
                        SID.setText(object.getString("Student_ID"));
                        SMAIL.setText(object.getString("Email"));
                        progressBar.setVisibility(View.GONE);

                    }
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




    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.menu_1, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case  R.id.Valida:
//                startActivity(new Intent(getApplicationContext(), SecQues.class));
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                finish();
//                return true;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(), SLogin.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                return true;
            case R.id.profile:
                Intent idd=new Intent(getApplicationContext(), ChangePassword.class);
                idd.putExtra("SI", no);
                idd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(idd);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
