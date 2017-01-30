package com.example.niranjan.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niranjan on 29-01-2017.
 */

public class Complaintofficial extends Fragment {
    EditText e1,e2,e3,e4;
    Button b1;
    String name,area,des,post,returnedstring;
    HttpClient httpclient;
    HttpPost httppost;
    ResponseHandler<String> response;
    List<NameValuePair> nameValuePairs;
    Context applicationContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.complaintofficial, container, false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        e1 = (EditText)v.findViewById(R.id.e1);
        e2 = (EditText)v.findViewById(R.id.e2);
        e3 = (EditText)v.findViewById(R.id.e3);
        e4 = (EditText)v.findViewById(R.id.e4);
        b1 = (Button)v.findViewById(R.id.b1);
        applicationContext = MainActivity.getContextOfApplication();
        nameValuePairs = new ArrayList<NameValuePair>();
        httpclient = new DefaultHttpClient();
        response = new BasicResponseHandler();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    name = e1.getText().toString();
                    area = e2.getText().toString();
                    des = e4.getText().toString();
                    post = e3.getText().toString();
                    if (name == null || area == null || des == null || post == null) {
                        Toast.makeText(applicationContext.getApplicationContext(), "Please enter all the details", Toast.LENGTH_LONG).show();
                    } else {
                        nameValuePairs.add(new BasicNameValuePair("name", name));
                        nameValuePairs.add(new BasicNameValuePair("area", area));
                        nameValuePairs.add(new BasicNameValuePair("complaint", des));
                        nameValuePairs.add(new BasicNameValuePair("post", post));
                        httppost = new HttpPost("http://10.0.2.2/official.php");
                        try {
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                            returnedstring = httpclient.execute(httppost, response);
                            Toast.makeText(applicationContext.getApplicationContext(), returnedstring, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(applicationContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                catch(Exception e){
                    Toast.makeText(applicationContext.getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Complaint Official");
    }
}
