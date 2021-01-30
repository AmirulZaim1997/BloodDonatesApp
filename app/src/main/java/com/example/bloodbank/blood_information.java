package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class blood_information extends AppCompatActivity implements OnMapReadyCallback {

    private TextView tvPlace,address,contact;
    private Button direction;
    List<Bloodbank> arrayList;
    DatabaseReference reff;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient client;
    String latitude,longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_information);

        tvPlace= findViewById(R.id.tvPlace);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);
        direction = findViewById(R.id.directionbtn);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemap);
        client = LocationServices.getFusedLocationProviderClient(this);

        if(ActivityCompat.checkSelfPermission(blood_information.this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(blood_information.this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(blood_information.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }

        arrayList = new ArrayList<>();
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank");
        //String bankname = getIntent().getStringExtra("name");
       // Integer
        tvPlace.setText(getIntent().getStringExtra("bankname"));
        address.setText(getIntent().getStringExtra("bankaddress")+" , "+getIntent().getStringExtra("bankstate"));
        contact.setText(getIntent().getStringExtra("bankphone"));
        String bankadd = tvPlace.getText().toString();
        List<Address> addressList = null;

        supportMapFragment.getMapAsync(this::onMapReady);

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bankname = getIntent().getStringExtra("bankname");
                String source = latitude.trim()+","+longtitude.trim();
                String destination = bankname.toString().trim();
                Displaytrack(source,destination);

            }


        });

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 100 && grantResults.length > 0 && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else{
            Toast.makeText(getApplicationContext(),"Permission denied",Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if(location != null){
                        latitude = String.valueOf(location.getLatitude());
                        longtitude = String.valueOf(location.getLongitude());
                    }
                    else{
                        LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000).setFastestInterval(1000).setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback(){
                            @Override
                            public void onLocationResult(LocationResult locationResult) {

                                Location location1 = locationResult.getLastLocation();
                                latitude =String.valueOf(location1.getLatitude());
                                longtitude = String.valueOf(location1.getLongitude());


                            }
                        };

                        client.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                    }
                }
            });

        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }


    private void Displaytrack(String source, String destination) {

        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+destination);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map =googleMap;
        LatLng latLng = new LatLng(Double.parseDouble(getIntent().getStringExtra("latitud")), Double.parseDouble(getIntent().getStringExtra("longtitud")));

        map.addMarker(new MarkerOptions().position(latLng).title(tvPlace.getText().toString()));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

    }


}
