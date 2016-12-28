package com.arps2.jjoo.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.JavascriptInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.webkit.GeolocationPermissions;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.net.URISyntaxException;


public class MapActivity extends Activity implements GeolocationPermissions.Callback {

    WebView webView;
    private Handler handler = new Handler();
    private String temp;
    double latitude=127.0509571;
    double longitude=37.4881456;
    double altitude=100;
    Button mButton;
    TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        //Log.e("place",latlngss);

//        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
  //      mapFragment.getMapAsync(this);

        webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);


        Geoclient geoclient=new Geoclient();    //casting class

        webView.setWebChromeClient(geoclient);  //set webchromeclient for permission
        String origin="";
        geoclient.onGeolocationPermissionsShowPrompt(origin,this);  //for permission

        webView.addJavascriptInterface(new AndroidBridge(),"android");
        webView.loadUrl("file:///android_asset/map.html");
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view) {
                webView.loadUrl("javascript:setMessage('" + "도곡동 대림아크로빌" + "')");
            }
        });

    }

    public void ClickI(View view) {
        try {
            Intent ImageIntent = new Intent(this, ImageActivity.class);
            ImageIntent.putExtra("longitude",longitude);
            ImageIntent.putExtra("altitude",altitude);
            ImageIntent.putExtra("latitude",latitude);

            startActivity(ImageIntent);
        }catch (Exception e){

        }
    }

    @Override
    public void invoke(String s, boolean b, boolean b1) {

    }

    class Geoclient extends WebChromeClient{    //for display mylocation
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,Callback callback){

            super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin,true,false);
        }

    }

    private class AndroidBridge {

        public void setMessage(final String arg) {

            handler.post(new Runnable() {
                @Override
                public void run() {


                    Log.d("test11",""+arg+"");

                }

            });

        }

    }



 /*
    public void onMapReady(GoogleMap map){
        Intent intent = getIntent();
        double longitude = intent.getDoubleExtra("longitude",0);
        double latitude = intent.getDoubleExtra("latitude",0);
        double altitude = intent.getDoubleExtra("altitude",0);

        LatLng myPoint = new LatLng(longitude, latitude);

        Log.d("test222",latitude + " " +longitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPoint, 13));
        map.addMarker( new MarkerOptions()
                .title("MY")
                .snippet("House")
                .position(myPoint));
    }

   public void addToPoi(String str) {
        mCoder = new Geocoder(this);
        try {
            //주소값을 통하여 로케일을 받아온다
            addr = mCoder.getFromLocationName(str, 1);
            Double Lat =  addr.get(0).getLatitude();
            Double Lon =  addr.get(0).getLongitude();
            //해당 로케일로 좌표를 구성한다
            newLatLng = new LatLng(Lat, Lon);
        } catch (Exception e) {
            return;
        }
    }
    */
}