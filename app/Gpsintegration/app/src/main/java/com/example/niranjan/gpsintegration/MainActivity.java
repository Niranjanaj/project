package com.example.niranjan.gpsintegration;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Address address;
    List<Address> addresses;
    EditText edit1;
    private LocationManager locManager;
    private LocationListener locListener = new locationlistener();
    Geocoder geocoder;
    int permission;
    private boolean gps_enabled ,network_enabled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edit1 = (EditText)findViewById(R.id.text1);
        geocoder = new Geocoder(this, Locale.getDefault());
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if (!gps_enabled && !network_enabled) {
                    Toast.makeText(getApplicationContext(), "Enable gps and internet", Toast.LENGTH_LONG).show();
                }
                try {
                    if (gps_enabled)
                        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
                    if (network_enabled)
                        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
                } catch (SecurityException e) {
                    Toast.makeText(getApplicationContext(), "Error1:" + e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class locationlistener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                try {
                    locManager.removeUpdates(locListener);
                }
                catch(SecurityException e){
                    Toast.makeText(getApplicationContext(),"Error2:"+e,Toast.LENGTH_LONG).show();
                }
                try{
                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                }
                catch(IOException i){
                    Toast.makeText(getApplicationContext(),"Error:"+i,Toast.LENGTH_LONG).show();
                }
                address=addresses.get(0);
                String name;
                name=address.getSubLocality();
                edit1.setText(name);
            }
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
