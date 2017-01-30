package com.example.niranjan.app;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    HttpClient httpclient;
    HttpPost httppost;
    ResponseHandler<String> response;
    List<NameValuePair> nameValuePairs;
    String returnedstring;
    EditText ed1,ed2;
    String username,password;
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        contextOfApplication = getApplicationContext();
        ed1 = (EditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        TextView reg = (TextView)findViewById(R.id.textView3);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);
            }
        });
        nameValuePairs = new ArrayList<NameValuePair>();
        httpclient = new DefaultHttpClient();
        response = new BasicResponseHandler();
    }


    public void official(View v){
        Intent i = new Intent(MainActivity.this,OfficialActivity.class);
        startActivity(i);
    }


    public void Cact(View v){
        username = ed1.getText().toString();
        password = ed2.getText().toString();
        nameValuePairs.add(new BasicNameValuePair("username",username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        try {
            httppost = new HttpPost("http://10.0.2.2/user_in.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
            returnedstring = httpclient.execute(httppost,response);
            if(returnedstring.equals("Valid user!")) {
                Intent i = new Intent(MainActivity.this, CombinedActivity.class);
                startActivity(i);
            }
            else
                Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
