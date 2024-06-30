package com.ches.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.window.SplashScreen;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    AppCompatButton b1,b2;
    String apiurl="https://courseapplogix.onrender.com/addstudents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.clg);
        e4=(EditText) findViewById(R.id.dob);
        e5=(EditText) findViewById(R.id.course);
        e6=(EditText) findViewById(R.id.mob);
        e7=(EditText) findViewById(R.id.mail);
        e8=(EditText) findViewById(R.id.address);
        b1=(AppCompatButton) findViewById(R.id.regbtn);
        b2=(AppCompatButton) findViewById(R.id.viewbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //read value
                String fname=e1.getText().toString();
                String lname=e2.getText().toString();
                String clg=e3.getText().toString();
                String dob=e4.getText().toString();
                String course=e5.getText().toString();
                String mobile=e6.getText().toString();
                String email=e7.getText().toString();
                String address=e8.getText().toString();

                //json object
                JSONObject stud=new JSONObject();
                try {
                    stud.put("firstname",fname);
                    stud.put("lastname",lname);
                    stud.put("college",clg);
                    stud.put("dob",dob);
                    stud.put("course",course);
                    stud.put("mobile",mobile);
                    stud.put("email",email);
                    stud.put("address",address);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //json object req
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        stud,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

               //Req queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);

               // Toast.makeText(getApplicationContext(),fname+lname+clg+dob+course+mobile+email+address , Toast.LENGTH_SHORT).show();
            }
        });
     b2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i=new Intent(getApplicationContext(),com.ches.studentapp.View.class);
             startActivity(i);
         }
     });
    }
}