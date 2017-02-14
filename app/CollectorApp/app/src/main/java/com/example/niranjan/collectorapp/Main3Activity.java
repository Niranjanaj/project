package com.example.niranjan.collectorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class Main3Activity extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    String passed;
    Intent i;
    HttpClient httpclient;
    HttpPost httppost;
    ResponseHandler<String> response;
    List<NameValuePair> nameValuePairs,name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3);
            i = getIntent();
            passed = i.getStringExtra("name");
            t1 = (TextView) findViewById(R.id.t1);
            t2 = (TextView) findViewById(R.id.t2);
            t3 = (TextView) findViewById(R.id.t3);
            t4 = (TextView) findViewById(R.id.t4);
            nameValuePairs = new ArrayList<NameValuePair>();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
          Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    httpclient = new DefaultHttpClient();
                    response = new BasicResponseHandler();
                    httppost = new HttpPost("http://10.0.2.2/activity.php");
                    nameValuePairs.add(new BasicNameValuePair("name",passed));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                    passed=httpclient.execute(httppost, response);
                    setTextView(passed);
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
             }
          });
        t.start();
    }
    public void setTextView(String passed){
        String[] detail;
        detail = passed.split(",");
        t1.setText(detail[0]);
        t2.setText(detail[2]);
        t3.setText(detail[1]);
        t4.setText(detail[3]);
    }
}
