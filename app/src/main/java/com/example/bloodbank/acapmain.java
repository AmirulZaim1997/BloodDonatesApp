package com.example.bloodbank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class acapmain extends AppCompatActivity {

    private Button event,bank,insert;
    private ImageButton scan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acapmain);

        event = findViewById(R.id.btnEvent);
        bank = findViewById(R.id.btnBank);
        insert = findViewById(R.id.insertbtn);
        scan = findViewById(R.id.scanbtn);

        Toast.makeText(acapmain.this,"Firebase Connection Success",Toast.LENGTH_LONG).show();


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentinsert = new Intent(acapmain.this,insertTest.class);
                startActivity(intentinsert);

            }
        });



        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentEvent = new Intent(acapmain.this,DonationEvent.class);
                startActivity(intentEvent);

            }
        });

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBank = new Intent(acapmain.this,bloodBanklist.class);
                startActivity(intentBank);
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent qr = new Intent(acapmain.this, QrCodeActivity.class);
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
                AlertDialog alertDialog = new AlertDialog.Builder(acapmain.this).create();
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
            Intent intent = new Intent(acapmain.this,donationRecord.class);
            intent.putExtra("qrevent",result);
            intent.putExtra("email",getIntent().getStringExtra("email"));
            startActivity(intent);

        }
    }
}