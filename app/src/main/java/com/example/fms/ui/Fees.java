package com.example.fms.ui;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fees extends Fragment {
    EditText If, tf, ef, sf, bc, sc, Date;
    TextView in;
    ImageView cal;
    Button Fee;
    Spinner spin, status;
    String fee_add = "https://feemsystem.000webhostapp.com/Addfees.php";
    String id = "https://feemsystem.000webhostapp.com/DropdownStudent.php";
    ArrayList<String> listitems = new ArrayList<>();
    ArrayAdapter<String> ID, stat;
    String[] fee_Status = {"Select Status", "Paid", "Pending"};
    DatePickerDialog.OnDateSetListener mDateSetListener;
    String TAG = "Fees";
    String date = "";
    int X;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_fee_details, null);
        tf = v.findViewById(R.id.tution);
        ef = v.findViewById(R.id.exam);
        cal=v.findViewById(R.id.cal);
        sf = v.findViewById(R.id.sports);
        If = v.findViewById(R.id.it);
        bc = v.findViewById(R.id.book);
        sc = v.findViewById(R.id.stationary);
        in = v.findViewById(R.id.invoice);
        Date = v.findViewById(R.id.duedate);
        status = v.findViewById(R.id.status);
        Fee = v.findViewById(R.id.fees);
        spin = v.findViewById(R.id.spin);

        listitems.add("Select Student Id");
        populateSpinner();

        stat = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, fee_Status);
        status.setAdapter(stat);
        ID = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, listitems);
        spin.setAdapter(ID);





        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyy/mm/dd: " + year + "/" + month + "/" + day);

                date = year + "/" + month + "/" + day;
                Date.setText(date);

            }
        };


        Fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tution_fee = tf.getText().toString();
                String Exam_fee = ef.getText().toString();
                String Sports_fee = sf.getText().toString();
                String It_cost = If.getText().toString();
                String Book_cost = bc.getText().toString();
                String Station = sc.getText().toString();
                String duedate = Date.getText().toString();
                String s = "";

                if (Tution_fee.isEmpty() || Exam_fee.isEmpty() || Sports_fee.isEmpty() ||
                        It_cost.isEmpty() || Book_cost.isEmpty() ||
                        Station.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your All details", Toast.LENGTH_SHORT).show();
                } else {
                    String  ID = String.valueOf(spin.getSelectedItem());
                    String  Status = String.valueOf(status.getSelectedItem());
                    Random random = new Random();
                    X = random.nextInt(80000)+70000;
                    s = String.valueOf(X);
                    if (ID.equals("Select Student Id")){
                        Toast.makeText(getContext(), "Plz Select correct Student ID", Toast.LENGTH_SHORT).show();
                    }else if (Status.equals("Select Status")) {
                        Toast.makeText(getContext(), "Plz Select Status of Fees", Toast.LENGTH_SHORT).show();


                    }else {
                        Add_fee(s, ID, Tution_fee, Exam_fee, Sports_fee, It_cost, Book_cost, Station, duedate, Status);
                        tf.setText(""); ef.setText(""); sf.setText(""); If.setText("");
                        bc.setText(""); Date.setText(""); sc.setText("");
                    }

                }
            }
        });

        return v;
    }

    private void populateSpinner() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("all");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        list.add(object.getString("Student_ID"));
                    }
                    listitems.addAll(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void Add_fee(String in, String id, String TF, String EF, String SF, String IT, String BC, String SC, String due, String Status) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Adding...");
        progressDialog.show();

        StringRequest sRequest = new StringRequest(Request.Method.POST, fee_add, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Fees details Added")) {
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                } else {

                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("stu_id", id);
                map.put("invoice", in);
                map.put("tution", TF);
                map.put("exam", EF);
                map.put("sports", SF);
                map.put("it", IT);
                map.put("book", BC);
                map.put("stat", SC);
                map.put("date", due);
                map.put("status", Status);
                return map;
            }

        };
        RequestQueue rQ = Volley.newRequestQueue(getContext());
        rQ.add(sRequest);

    }
}