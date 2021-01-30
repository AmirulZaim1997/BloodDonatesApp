package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.DateFormat;
import java.util.Calendar;

public class donationRecord extends AppCompatActivity {

    private TextView donor,event,date,location,staterecord;
    private EditText status;
    private Button save;
    DatabaseReference reff,reff2,reffname;
    Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_record);

        donor = findViewById(R.id.tvName);
        event = findViewById(R.id.tvEvent);
        date = findViewById(R.id.tvDate);
        location = findViewById(R.id.tvLocation);
        status = findViewById(R.id.etStatus);
        save = findViewById(R.id.savebtn);
        staterecord = findViewById(R.id.tvstaterecord);
        record = new Record();
        Calendar calendar = Calendar.getInstance();
        String currentdate = DateFormat.getDateInstance().format(calendar.getTime());

        event.setText(getIntent().getStringExtra("qrevent"));
        String event =getIntent().getStringExtra("qrevent");
        String email = getIntent().getStringExtra("email");
        reffname =FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
        Query queryname =reffname.orderByChild("email").equalTo(email);

        queryname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name  = snapshot.child("name").getValue().toString();
                    donor.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reff2 = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("DonationEvent");
        Query query = reff2.orderByChild("event").equalTo(event);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String locations  = snapshot.child("location").getValue().toString();
                        String states = snapshot.child("state").getValue().toString();
                        location.setText(locations);
                        staterecord.setText(states);
                    }
                }
                else{
                    Toast.makeText(donationRecord.this,"Event not exist",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(donationRecord.this,acapmain.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        date.setText(currentdate);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Record");
                record.setName(donor.getText().toString().trim());
                record.setLocation(location.getText().toString().trim());
                record.setDate(date.getText().toString().trim());
                record.setStatus(status.getText().toString().trim());
                record.setState(staterecord.getText().toString().trim());
                reff.push().setValue(record);
                Toast.makeText(donationRecord.this,"Data donation record insert success",Toast.LENGTH_LONG).show();


            }
        });

    }
}