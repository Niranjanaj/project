package com.example.niranjan.app;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class register extends AppCompatActivity {
    Spinner s;
    EditText e1,e2,e3,e4,e5,e6,e8;
    final String[] choices = {"Select","North Coimbatore","Kavundampalayam","Saravanampatti","rspuram","Gandhipuram","Saibabacolony","Thudiyalur"};
    String name=null,username=null,phonenumber=null,password=null,email=null,area=null,dob=null,returnedstring;
    HttpClient httpclient;
    HttpPost httppost;
    ResponseHandler<String> response;
    List<NameValuePair> nameValuePairs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);
        e5 = (EditText)findViewById(R.id.e5);
        e6 = (EditText)findViewById(R.id.e6);
        e8 = (EditText)findViewById(R.id.e8);
        s=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> a = new ArrayAdapter<String>(register.this, android.R.layout.simple_spinner_item, choices);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(a);
        nameValuePairs = new ArrayList<NameValuePair>();
        httpclient = new DefaultHttpClient();
        response = new BasicResponseHandler();
    }

    public void Reload(View v){
        name = e1.getText().toString();
        username = e2.getText().toString();
        phonenumber = e3.getText().toString();
        password = e4.getText().toString();
        email = e6.getText().toString();
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dob = e8.getText().toString();
        if(password.equals(e5.getText().toString())){
           if(name==null||username==null||phonenumber==null||password==null||email==null||area==null||dob==null){
               Toast.makeText(getApplicationContext(),"Please enter all the details",Toast.LENGTH_LONG).show();
           }
            else{
               nameValuePairs.add(new BasicNameValuePair("name", name));
               nameValuePairs.add(new BasicNameValuePair("username",username));
               nameValuePairs.add(new BasicNameValuePair("phone",phonenumber));
               nameValuePairs.add(new BasicNameValuePair("password", password));
               nameValuePairs.add(new BasicNameValuePair("email",email));
               nameValuePairs.add(new BasicNameValuePair("area",area));
               nameValuePairs.add(new BasicNameValuePair("dob",dob));
               httppost = new HttpPost("http://10.0.2.2/useracc.php");
               try {
                   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                   returnedstring = httpclient.execute(httppost,response);
                   Intent i = new Intent(register.this,CombinedActivity.class);
                   startActivity(i);
                   Toast.makeText(getApplicationContext(),returnedstring,Toast.LENGTH_LONG).show();
               }
               catch(Exception e){
                   Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
               }
           }

        }
        else{
            Toast.makeText(getApplicationContext(),"Password mismatched",Toast.LENGTH_LONG).show();
        }
            /*Intent i = new Intent(register.this, CombinedActivity.class);
            startActivity(i);*/
    }
}
