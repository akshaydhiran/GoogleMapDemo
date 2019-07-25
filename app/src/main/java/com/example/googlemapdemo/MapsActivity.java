package com.example.googlemapdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements View.OnClickListener {
    Button a1,a2,a3,a4,a5;
    public void init(){
        a1 = findViewById(R.id.GoogleMapMarker); // initialise a1
        a2 = findViewById(R.id.GoogleMapCurrentLocation); // initialise a2
        a3 = findViewById(R.id.StreetView); //initialise a3
        a4 = findViewById(R.id.a4); // initialise a4
        a5 = findViewById(R.id.a5);  // initialise a5
        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        a5.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        init();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId(); // v is an object is a object for View Onclick class.
        Intent intent = null; // intent is used to show messages.
        switch (id){  // here we use switch for giving id for multiple butttons we used for to distinguish.
            case R.id.GoogleMapMarker : intent = new Intent(MapsActivity.this, GoogleMapMarkerDemo.class); break;
            case R.id.GoogleMapCurrentLocation : intent = new Intent(MapsActivity.this, GoogleMapCurrentLocation.class); break;
            case R.id.StreetView : intent = new Intent(MapsActivity.this, StreetViewDemo.class); break;

        }
        if (intent!=null)
            startActivity(intent);  // here we use startACtivity for using other buttons because it is crash instead.

    }


}
