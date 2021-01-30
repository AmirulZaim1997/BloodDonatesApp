package com.example.bloodbank;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class viewDonor extends AppCompatActivity {
    private TextView hometv1,abouttv,namedis,blooddis;
    private ImageView logo;
    private RecyclerView recyclerView;
    private HorizontalScrollView horizontalScrollView3;
    private ArrayList<Record> list;
    private TableUserAdapter tableUserAdapter;
    private ImageButton scan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    String name;
    DatabaseReference reff,reffrecord;

    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_donor);

        hometv1=findViewById(R.id.hometv1);
        abouttv=findViewById(R.id.abouttv);
        cardView=findViewById(R.id.cardview);
        namedis=findViewById(R.id.namedis);
        blooddis=findViewById(R.id.blooddis);
        logo=findViewById(R.id.imageView);
        scan = findViewById(R.id.scanqrbtn);
        horizontalScrollView3=findViewById(R.id.horizontalScrollView3);

        String email = getIntent().getStringExtra("email");

        logo=findViewById(R.id.logo);
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
        Query query = reff.orderByChild("email").equalTo(email);

        recyclerView =findViewById(R.id.donorhistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        tableUserAdapter = new TableUserAdapter(this,list);
        recyclerView.setAdapter(tableUserAdapter);

        reffrecord = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Record");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    name=dataSnapshot.child("name").getValue().toString();
                    String bloodType=dataSnapshot.child("bloodType").getValue().toString();
                    Toast.makeText(viewDonor.this,name,Toast.LENGTH_LONG).show();
                    namedis.setText(name);
                    blooddis.setText(bloodType);
                    Query queryname = reffrecord.orderByChild("name").equalTo(name);

                    queryname.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                Record donor = dataSnapshot1.getValue(Record.class);
                                list.add(donor);
                            }
                            tableUserAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent qr = new Intent(viewDonor.this, QrCodeActivity.class);
                        startActivityForResult(qr,REQUEST_CODE_QR_SCAN);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        permissionDeniedResponse.getRequestedPermission();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(viewDonor.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Intent intent = new Intent(viewDonor.this,donationRecord.class);
            intent.putExtra("qrevent",result);
            intent.putExtra("email",getIntent().getStringExtra("email"));
            startActivity(intent);

        }
    }
}
