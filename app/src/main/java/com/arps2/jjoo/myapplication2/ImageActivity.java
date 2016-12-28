package com.arps2.jjoo.myapplication2;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

public class ImageActivity extends AppCompatActivity {

    private double latitude, longitude, altitude;

    private ArchitectView architectView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        Bundle extras = getIntent().getExtras();
        longitude = extras.getDouble("longitude");
        latitude = extras.getDouble("latitude");
        altitude = extras.getDouble("altitude");

        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
        final StartupConfiguration config = new StartupConfiguration("B6+z8vluaxrHIUoMEigQLFG/7W7tvro2VM/T2tudMrFH8rlhMkePeKwrMMfl3s0CHbyQtohTIJtv+Pw6QaAop8cOCj8WxIGlcp2B64ln1JOa77TGaG6XYZYUGG45ViuOyZu4jIUWiu6rJINOPM06SlMLIUYXKr+TB/qcl5WD4dhTYWx0ZWRfX4ZUC918qS6zg37cPxqD3JmnOKdd/2rD7sPiuno0Hvl8EWzlKFVsyCkf6bdfqU2t+y3BmH67hR5s7YXyd/LtnWL5lPK126qVBREzoBhSONlb5ym7/EpuoWcDS3b/AS8JeuIuykouiKCWO8A25EYSi89GDp1ItMIdXqFXdMeICqHEQPH25KZu7wINe4TzuJED6QSxXK5gXhZ8Iu3VnMl49aippw4QF7rp3q9T+I9YS1Rm/OC/HYs6NlvnOgZs5HY2a9LSCqzYaaHrPzYp6ShScrnpQn2wy0oioEArVtxSbn3swpz65EiyJ/EselHWA7huma8oPIOjfK6RkFbV9NOPGACxavPO/Vdi3BX0rHVghYWJaNHphwdjcwA/QEryd0IpcMnP0T8Myo1b5H+dstMsVHADgBzGt1d3DpX50xm4rnMPtk8ArzPSo+NLnvzPfiEjnUpWsfDCQS177T0e0cGtC2Wa4wO8NJ/hA0Y4oO7KmtvzaRArBWGhKK4=");
        this.architectView.onCreate(config);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        architectView.onPostCreate();
        try {
            this.architectView.load("file:///android_asset/x_Demo_2_SolarSystem(Geo)/index.html");
        } catch (Exception e) {

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        architectView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        architectView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        architectView.onPause();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Image Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }



}
