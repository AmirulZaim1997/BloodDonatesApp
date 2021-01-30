 package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DonationEvent extends AppCompatActivity {

    private Spinner spinlocation;
    private ListView lvevent;
    String locaArray [] ={"All Events","Perak","Kedah","Perlis","Kelantan","Pulau Pinang","Terengganu","Pahang","Selangor","Negeri Sembilan","Melaka","Johor","Sabah","Sarawak"};
    String state;
    List<donation> arrayEvent;
    DatabaseReference reff,reffspin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_event);

        spinlocation = findViewById(R.id.spinstate);
        lvevent = findViewById(R.id.lvevent);
        arrayEvent = new ArrayList<>();
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent");



        ArrayAdapter arrayAdapter = new ArrayAdapter(DonationEvent.this, android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,locaArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinlocation.setAdapter(arrayAdapter);

        spinlocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = spinlocation.getSelectedItem().toString();
                if(position == 0 )
                {
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 1){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Perak");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 2){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Kedah");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 3){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Perlis");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 4){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Kelantan");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 5){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Pulau Pinang");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 6){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Terengganu");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 7){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Pahang");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 8){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Selangor");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 9){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Negeri Sembilan");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 10){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Melaka");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 11){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Johor");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 12){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Sabah");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 13){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("DonationEvent").orderByChild("state").equalTo("Sarawak");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayEvent.clear();

                            for (DataSnapshot event : dataSnapshot.getChildren()){
                                donation donation = event.getValue(com.example.bloodbank.donation.class);
                                arrayEvent.add(donation);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(DonationEvent.this,arrayEvent);
                            lvevent.setAdapter(customAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        lvevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                donation donation = arrayEvent.get(position);
                String nameevent = donation.getEvent();
                String locationevent = donation.getLocation();
                Intent intentrem = new Intent(DonationEvent.this, reminder.class);
                intentrem.putExtra("name",nameevent);
                intentrem.putExtra("location",locationevent);
                startActivity(intentrem);
            }
        });



    }
}