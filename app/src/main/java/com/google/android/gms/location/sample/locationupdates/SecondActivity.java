package com.google.android.gms.location.sample.locationupdates;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class SecondActivity extends ActionBarActivity implements SensorEventListener{

    // define the display assembly compass picture
    private ImageView image;

    // record the compass picture angle turned
    private float currentDegree = 0f;

            // device sensor manager
            private SensorManager mSensorManager;

    TextView tvHeading;
 LatLng oldLocation;

//googlemap
//private static final LatLng DAVAO = new LatLng(7.0722, 125.6131);
    private GoogleMap map;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//
        image = (ImageView) findViewById(R.id.imageViewCompass);

        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvHeading);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Intent intent = getIntent();// New location cordinates
//        String Lati = intent.getStringExtra("Lati");
//        String Long = intent.getStringExtra("Long");//test code
//        double NewLati=Double.valueOf(Lati).doubleValue();
//        double NewLong=Double.valueOf(Long).doubleValue();
//        Current = new LatLng(NewLati, NewLong);
        //    Bundle extras = getIntent().getExtras();

        String oldLoc = intent.getStringExtra("saveLocation");
        String saveLati = intent.getStringExtra("saveLati");
        String saveLong = intent.getStringExtra("saveLong");//test code
//        double NewSaveLati=Double.valueOf(saveLati).doubleValue();
//        double NewSaveLong=Double.valueOf(saveLong).doubleValue();
        Float NewSaveLati= Float.parseFloat(saveLati);
        Float NewSaveLong= Float.parseFloat(saveLong);
//        Float NewSaveLati = intent.getFloatExtra(saveLati, Float.parseFloat(null));
//        Float NewSaveLong = intent.getFloatExtra(saveLong, Float.parseFloat(null));
        oldLocation = new LatLng(NewSaveLati, NewSaveLong);
//
// Google map
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        Marker davao = map.addMarker(new MarkerOptions().position(oldLocation).title(oldLoc));

        // zoom in the camera to Davao city
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(oldLocation, 15));

        // animate the zoom process
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

    }

    public void onclickmap(View view){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    public void onclickhybrid(View view){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    public void onclickterrain(View view){
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }



    @Override
    protected void onResume() {
        super.onResume();
        // for the system's orientation sensor registered listeners

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get the angle around the z-axis rotated

        float degree = Math.round(event.values[0]);

        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");
        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
