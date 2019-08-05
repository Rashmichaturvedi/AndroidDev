package com.dits.dailyreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class GetAddressMapActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    Location location = null;
    boolean isLocationLoaded = false;
    TextView addressTxt;
    LatLng center;
    FloatingActionButton doneAddress;
    String addressString = "";
    String cityString = "";
    String stateString = "";
    String countryString = "";
    String postalCodeString = "";
    View mapView;
    String completeAddress;
    String key;
    DatabaseReference ref;
    protected static final int REQUEST_CHECK_SETTINGS = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address_map);
        ref = FirebaseDatabase.getInstance().getReference();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        addressTxt = findViewById(R.id.address_txt);
        doneAddress = findViewById(R.id.done_address);
        displayLocationSettingsRequest(this);
        mapView = mapFragment.getView();

        buildGoogleApiClient();
        doneAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap hm = new HashMap();
                hm.put("location" ,addressTxt.getText().toString());
                key = ref.push().getKey();
                ref.child(key).updateChildren(hm);
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }
                try {

                    Toast.makeText(GetAddressMapActivity.this, String.valueOf(location.getLatitude()+"/"+location.getLongitude()), Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
                finish();

            }
        });

    }

    private void buildGoogleApiClient() {
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);


        View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).
                getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 300);




        getCurrentLocation();

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                addressTxt.setText("getting your location...");
            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                center = mMap.getCameraPosition().target;
                getAddress(getApplicationContext(),center.latitude,center.longitude);
            }
        });



    }

    private void getCurrentLocation() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean re = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (re == true) {
            if (Build.VERSION.SDK_INT >= 23) {

                if (ActivityCompat.checkSelfPermission(GetAddressMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GetAddressMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, GetAddressMapActivity.this);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location!=null){
                isLocationLoaded = true;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15.0f));
                getAddress(getApplicationContext(),location.getLatitude(),location.getLongitude());
            }

        }else {

        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {
        //Set Address
        try {

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {
                cityString = addresses.get(0).getLocality();
                stateString = addresses.get(0).getAdminArea();
                countryString = addresses.get(0).getCountryName();
                postalCodeString = addresses.get(0).getPostalCode();
                completeAddress  = addresses.get(0).getAddressLine(0);
                try{
                    addressString = completeAddress.substring(0, completeAddress.indexOf(", "+cityString));
                }catch (Exception e){
                    e.printStackTrace();
                }

                addressTxt.setText(completeAddress);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        getCurrentLocation();
                        //Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(GetAddressMapActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            //Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });

    }
}
