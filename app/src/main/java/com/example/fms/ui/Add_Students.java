package com.example.fms.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Add_Students extends Fragment {
    String ADD = "https://feemsystem.000webhostapp.com/addStudents.php";
    Button done;
    EditText fn;
    EditText ln;
    EditText mpass;
    EditText pass;
    EditText em;
    EditText un;
    String x = "STD123";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_students, null);
        fn = v.findViewById(R.id.fname);
        ln = v.findViewById(R.id.lname);
        un = v.findViewById(R.id.User);
        em = v.findViewById(R.id.email);
        mpass = v.findViewById(R.id.UP);
        pass = v.findViewById(R.id.UCP);
        done = v.findViewById(R.id.done);

        TextView t = v.findViewById(R.id.format);


        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s = "";
                Random random = new Random();
                int randomLen = random.nextInt(9) + 1;
                for (int i = 0; i < randomLen; i++) {
                    s = s + "abcdefghijklmnopqrstuvwxyz".charAt(random.nextInt(26));
                }
                String First_name = fn.getText().toString().trim();
                String Last_name = ln.getText().toString().trim();
                String User_name = un.getText().toString().trim();
                String E_mail = em.getText().toString().trim();
                String Main = mpass.getText().toString().trim();
                String Confirm = pass.getText().toString().trim();

                if (First_name.equals("") || Last_name.equals("") || User_name.equals("") ||
                        E_mail.equals("") || Main.equals("") || Confirm.equals("")) {
                    Toast.makeText(Add_Students.this.getContext(), "Please enter your All details", Toast.LENGTH_SHORT).show();


                } else if (!Main.equals(Confirm)) {
                    Toast.makeText(Add_Students.this.getContext(), "Password Do not Match", Toast.LENGTH_SHORT).show();
                } else {


                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com+";

                    if (E_mail.matches(emailPattern))
                    {
                        Add_student(First_name, Last_name, User_name, E_mail, Main);
                        fn.setText("");
                        ln.setText("");
                        un.setText("");
                        em.setText("");
                        mpass.setText("");
                        pass.setText("");
                    }
                    else
                    {
                        t.setText("ex:- XYZ@mail.com");
                        t.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                    }




                }
            }
        });
        return v;
    }

    public void Add_student(String FN, String LN, String UN, String EM, String Pass) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Adding...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Registered Successfully")) {
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
                map.put("IOJ",x);
                map.put("fname", FN);
                map.put("lname", LN);
                map.put("user", UN);
                map.put("email", EM);
                map.put("pass", Pass);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}