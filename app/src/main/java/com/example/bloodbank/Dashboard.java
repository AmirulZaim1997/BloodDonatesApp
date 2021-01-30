package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    private ImageButton register_donors,user_details,blood_bank,donation_event,donors_detail,patient_blood_request,donation_record;
    private TextView username,signout,personaldetail;
    DatabaseReference reffemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        register_donors = findViewById(R.id.register_donors_img);
        user_details = findViewById(R.id.user_icon_img);
        blood_bank = findViewById(R.id.blood_bank_img);
        donation_event = findViewById(R.id.donationevent_img);
        donors_detail = findViewById(R.id.donorsdetails_img);
        patient_blood_request = findViewById(R.id.patientBlood_img);
        donation_record =findViewById(R.id.DonationRecordIMG);
        username = findViewById(R.id.username_tv);
        signout = findViewById(R.id.signout);
        personaldetail = findViewById(R.id.personal_detail_txt);

        String emails = getIntent().getStringExtra("email");
        username.setText(emails);
        reffemail = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
        Query queryemail = reffemail.orderByChild("email").equalTo(emails);
        queryemail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        personaldetail.setText("PERSONAL RECORD");
                    }
                }
                else{
                        personaldetail.setText("REGISTER DONOR");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        user_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reffemail = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
                Query queryemail = reffemail.orderByChild("email").equalTo(emails);
                queryemail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Intent intent = new Intent(Dashboard.this,updateUser.class);
                                intent.putExtra("email",emails);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(Dashboard.this,"Not register as a donor",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Dashboard.this,registerDonor.class);
                            intent.putExtra("email",emails);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        donation_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,allDonor.class);
                startActivity(intent);
            }
        });

        register_donors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reffemail = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
                Query queryemail = reffemail.orderByChild("email").equalTo(emails);
                queryemail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Intent intent = new Intent(Dashboard.this,viewDonor.class);
                                intent.putExtra("email",emails);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(Dashboard.this,"Not register as a donor",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Dashboard.this,registerDonor.class);
                            intent.putExtra("email",emails);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Splash.class);
                startActivity(intent);
            }
        });

        donation_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,DonationEvent.class);
                startActivity(intent);
            }
        });

        blood_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,bloodBanklist.class);
                startActivity(intent);
            }
        });

        donors_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,ViewUser.class);
                startActivity(intent);
            }
        });

        patient_blood_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,bloodRequest.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {

    }
}

