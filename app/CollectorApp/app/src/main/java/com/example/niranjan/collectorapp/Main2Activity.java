package com.example.niranjan.collectorapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.StrictMode;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Stack;

import static com.example.niranjan.collectorapp.R.id.visible;

public class Main2Activity extends AppCompatActivity{
    private ListView listView ;
    private ArrayAdapter<String> listAdapter ;
    String[] names;
    String n;
    HttpClient httpclient;
    HttpPost httppost,http;
    ResponseHandler<String> response;
    Stack<String> s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.list);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpclient = new DefaultHttpClient();
                    response = new BasicResponseHandler();
                    http = new HttpPost("http://10.0.2.2/test.php");
                    n = httpclient.execute(http,response);
                    listView(n);
                }
                catch(Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        t.start();
    }
    public void listView(String n){
        names = n.split(",");
        names[0]=String.format("        %s        ","Official Complaints");
            s = new Stack<String>();
            for (int i = 1; i < names.length; i++) {
                s.push(names[i]);
            }
            for (int i = 1; i < names.length; i++) {
                names[i] = s.pop();
            }
        CustomList customList = new CustomList(Main2Activity.this,names);
        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getView(i,view,adapterView);
                Intent in = new Intent(Main2Activity.this,Main3Activity.class);
                in.putExtra("name",names[i]);
                startActivity(in);
                //Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) convertView.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(
                    R.layout.simplerow, null);

        }
            convertView.setBackgroundColor(Color.argb(125,75,236,90));
        return convertView;

    }
}

