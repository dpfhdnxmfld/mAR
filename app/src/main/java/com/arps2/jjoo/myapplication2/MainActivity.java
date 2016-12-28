package com.arps2.jjoo.myapplication2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    private double longitude;
    private double latitude;
    private float accuracy;
    private double altitude;
    TextView tv;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            tv = (TextView)findViewById(R.id.textView1);
            Log.d("test2", "onLocationChanged, location:" + location);
            longitude = location.getLongitude(); //경도
            latitude = location.getLatitude();   //위도
            altitude = location.getAltitude();   //고도
            accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            Log.d("test1", "My: " + longitude + " " + latitude);
            a = 1;
            tv.append(" " + Double.toString(longitude) + " " + Double.toString(latitude));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }


    };

    public void ClickR(View view) {

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        longitude = 37.488367;
        latitude = 127.050914;
        altitude = 10;
        a = 0;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, mLocationListener);
        lm.removeUpdates(mLocationListener);

        try {
            Intent mapIntent = new Intent(this, MapActivity.class);
            mapIntent.putExtra("longitude",longitude);
            mapIntent.putExtra("altitude",altitude);
            mapIntent.putExtra("latitude",latitude);

            startActivity(mapIntent);
        }catch (Exception e){

        }

// Make the Intent explicit by setting the Google Maps package


// Attempt to start an activity that can handle the Intent

    }

    public void ClickV(View view) {
        Intent intent = new Intent(this, ArActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}


