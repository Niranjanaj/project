package com.example.niranjan.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class register extends AppCompatActivity {
    Spinner s;
    final String[] choices = {"North Coimbatore","Kavundampalayam","Saravanampatti","rspuram","Gandhipuram","Saibabacolony","Thudiyalur"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        s=(Spinner)findViewById(R.id.spinner);
        try {
            ArrayAdapter<String> a = new ArrayAdapter<String>(register.this, android.R.layout.simple_spinner_item, choices);
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s.setAdapter(a);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void Reload(View v){
        try {
            Intent i = new Intent(register.this, CombinedActivity.class);
            startActivity(i);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
