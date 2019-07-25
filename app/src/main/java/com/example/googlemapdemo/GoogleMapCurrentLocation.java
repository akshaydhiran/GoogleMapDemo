package com.example.googlemapdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapCurrentLocation extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_current_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    private static final int RC_LOCATION_PER = 1001;
    String city1, state1;

    protected void getLocation() {
        if (true) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

            //You can still do this if you like, you might get lucky:
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Toast.makeText(this, "request permiossions", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(GoogleMapCurrentLocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, RC_LOCATION_PER);
                return;
            }
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                Toast.makeText(this, "location not null", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                LatLng myLoc = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(myLoc).title("You are here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
//                Geocoder geocoder = new Geocoder(getApplicationContext());
//                List<Address> addressList = null;
//                try {
//                    addressList = geocoder.getFromLocation(latitude, longitude, 1);
//                    city1 = addressList.get(0).getLocality();
//                    state1 = addressList.get(0).getAdminArea();
//
//                } catch (IOException e) {
//                    Log.e("error_decoding_Address",e.getMessage());
//                }
//                String addrString = addressList.get(0).getLocality();
//                Log.d("locality", addrString);
                Toast.makeText(GoogleMapCurrentLocation.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
                //searchNearestPlace(voice2text);
            } else {
                Toast.makeText(this, "request Location update", Toast.LENGTH_SHORT).show();
                //This is what you need:
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
            }
        } else {
            //prompt user to enable location....
            //.................
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng myLoc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(myLoc).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
        Toast.makeText(this, location.getLatitude() + " , " + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Toast.makeText(this, "status changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        getLocation();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }
}