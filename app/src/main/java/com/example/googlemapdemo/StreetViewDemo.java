package com.example.googlemapdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewSource;

import static android.os.Build.VERSION_CODES.N;

public class StreetViewDemo extends AppCompatActivity implements LocationListener {
    //private StreetViewPanoramaView mStreetViewPanoramaView;
    //private StreetViewPanorama streetViewPanorama;
    private static final LatLng CALIFORNIA = new LatLng(-33.87365, 151.20689);
    private static final int RC_LOCATION_PER = 111;
    LocationManager locationManager;

    public double latitude;
    public double longitude;
    public Criteria criteria;
    public String bestProvider;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view_demo);

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        // Only set the panorama to SYDNEY on startup (when no panoramas have been
                        // loaded which is when the savedInstanceState is null).
                        if (savedInstanceState == null) {
                            panorama.setPosition(CALIFORNIA);
                        }
                    }
                });

        getLocation();

        //   StreetViewPanoramaFragment streetViewPanoramaFragment =
        //                (StreetViewPanoramaFragment) getFragmentManager()
        //                        .findFragmentById(R.id.streetviewpanorama);
        //        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
     //   mStreetViewPanoramaView = new StreetViewPanoramaView(this,
       //         new StreetViewPanoramaOptions().position(-33.87365));

        //        streetViewPanorama.setPanningGesturesEnabled(true);
        //        streetViewPanorama.setZoomGesturesEnabled(true);
        //        streetViewPanorama.setStreetNamesEnabled(true);



        //        streetViewPanorama.setPosition(SAN_FRAN);
        //        streetViewPanorama.setPosition(SAN_FRAN,20);
        //        streetViewPanorama.setPosition(SAN_FRAN, StreetViewSource.OUTDOOR);
        //        streetViewPanorama.setPosition(SAN_FRAN,20,StreetViewSource.OUTDOOR);
        //
        //
        //        StreetViewPanoramaLocation location = streetViewPanorama.getLocation();
        //        if (location != null && location.links != null) {
        //            streetViewPanorama.setPosition(location.links[0].panoId);
        //        }
        //
        //         final float ZOOM_BY = 0.5f;
        //        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
        //                .zoom(streetViewPanorama.getPanoramaCamera().zoom + ZOOM_BY)
        //                .tilt(streetViewPanorama.getPanoramaCamera().tilt)
        //                .bearing(streetViewPanorama.getPanoramaCamera().bearing)
        //                .build();
        //
        //
        //
        //        final int PAN_BY = 30;
        //        StreetViewPanoramaCamera Camera = new StreetViewPanoramaCamera.Builder()
        //                .zoom(streetViewPanorama.getPanoramaCamera().zoom)
        //                .tilt(streetViewPanorama.getPanoramaCamera().tilt)
        //                .bearing(streetViewPanorama.getPanoramaCamera().bearing - PAN_BY)
        //                .build();
        //
        //
        //
        //        float tilt = streetViewPanorama.getPanoramaCamera().tilt + 30;
        //        tilt = (tilt > 90) ? 90 : tilt;
        //
        //        StreetViewPanoramaCamera previous = streetViewPanorama.getPanoramaCamera();
        //
        //        StreetViewPanoramaCamera Cameraa = new StreetViewPanoramaCamera.Builder(previous)
        //                .tilt(tilt)
        //                .build();
        //
        //
        //        long duration = 1000;
        //        StreetViewPanoramaCamera cameras =
        //                new StreetViewPanoramaCamera.Builder()
        //                        .zoom(streetViewPanorama.getPanoramaCamera().zoom)
        //                        .tilt(streetViewPanorama.getPanoramaCamera().tilt)
        //                        .bearing(streetViewPanorama.getPanoramaCamera().bearing - 60)
        //                        .build();
        //        streetViewPanorama.animateTo(camera, duration);



    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        Toast.makeText(this, longitude+", "+latitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    //public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
    //              streetViewPanorama.setPosition(new LatLng(-33.87365, 151.20689));
    //
    //
    //          }


    String city1,state1;
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
                ActivityCompat.requestPermissions(StreetViewDemo.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},RC_LOCATION_PER);
                return;
            }
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                LatLng myLoc = new LatLng(latitude, longitude);
//                mMap.addMarker(new MarkerOptions().position(myLoc).title("You are here"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
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
                Toast.makeText(StreetViewDemo.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
                //searchNearestPlace(voice2text);
            }
            else{
                //This is what you need:
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
            }
        }
        else
        {
            //prompt user to enable location....
            //.................
        }
    }
}
