package com.example.fms.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fms.Admin;
import com.example.fms.Feedetails;
import com.example.fms.R;
import com.example.fms.Student_update;
import com.example.fms.Userlist;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<Userlist> list;
    Context context;


    public UserAdapter(List<Userlist> list, Context context) {
        this.list = list;
        this.context = context;

    }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_details, null);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        Userlist mylist = list.get(position);
        holder.tvid.setText(mylist.getId());
        holder.FULLNAME.setText(mylist.getFn() + " " + mylist.getLn());
        holder.tvemail.setText(mylist.getEm());
        holder.tvusername.setText(mylist.getUn());
        holder.tvpass.setText(mylist.getPass());
        holder.stuup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent up = new Intent(context, Student_update.class);
                up.putExtra("id", mylist.getId());
                up.putExtra("f", mylist.getFn());
                up.putExtra("l", mylist.getLn());
                up.putExtra("mail", mylist.getEm());
                up.putExtra("pass", mylist.getPass());
                up.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(up);
                ((Activity) context).finish();


            }
        });

        holder.studel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("CONFIRM");
                alert.setMessage("Are you sure you want you want to delete this student?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String del_fee = "https://feemsystem.000webhostapp.com/deletetest.php?idd=" + mylist.getId();
                                String delurl = "https://feemsystem.000webhostapp.com/delstu.php?idd=" + mylist.getId();
                                Delstud(delurl);
                                Delfee(del_fee);
                            }
                        })
                        .setNegativeButton("No", null );


                alert.show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.butt));

                Intent i = new Intent(context, Feedetails.class);
                i.putExtra("st_id", mylist.getId());
                i.putExtra("full", mylist.getFn() + " " + mylist.getLn());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvid, FULLNAME, tvemail, tvusername, tvpass;
        CardView cardView;
        Button stuup, studel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.myid);
            FULLNAME = itemView.findViewById(R.id.myfulname);
            tvemail = itemView.findViewById(R.id.myemail);
            tvusername = itemView.findViewById(R.id.username);
            tvpass = itemView.findViewById(R.id.password);
            cardView = itemView.findViewById(R.id.cd);
            studel = itemView.findViewById(R.id.delstu);
            stuup = itemView.findViewById(R.id.upstu);

        }
    }

    public void Delstud(String URL) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if (response.trim().equals("Deleted")) {
//                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                Intent a = new Intent(context, Admin.class);
                context.startActivity(a);
                progressDialog.dismiss();
                ((Activity) context).overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//);
                ((Activity) context).finish();

//                }else if(response.trim().equals("Failed")){
//                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void Delfee(String URL) {
//        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Deleting...");
//        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if (response.trim().equals("Deleted")) {
                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                Intent a = new Intent(context, Admin.class);
//                context.startActivity(a);
//                progressDialog.dismiss();
                ((Activity) context).overridePendingTransition(0, 0);
                ;
//);
                ((Activity) context).finish();

//                }else if(response.trim().equals("Failed")){
//                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
//                progressDialog.dismiss();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}