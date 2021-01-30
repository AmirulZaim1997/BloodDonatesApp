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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bloodBanklist extends AppCompatActivity {

    private ListView lvblood;
    private Spinner spinstate;
    List<Bloodbank> arrayList;
    DatabaseReference reff;
    String locaArray [] ={"All Blood Banks","Perak","Kedah","Perlis","Kelantan","Pulau Pinang","Terengganu","Pahang","Selangor","Negeri Sembilan","Melaka","Johor","Sabah","Sarawak"};
    String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_banklist);

        lvblood = findViewById(R.id.lvblood);
        spinstate = findViewById(R.id.spinstate);
        arrayList = new ArrayList<>();
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank");

        ArrayAdapter arrayAdapter = new ArrayAdapter(bloodBanklist.this, android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,locaArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinstate.setAdapter(arrayAdapter);



        spinstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = spinstate.getSelectedItem().toString();
                if(position == 0 )
                {
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 1){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Perak");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 2){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Kedah");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 3){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Perlis");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 4){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Kelantan");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 5){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Pulau Pinang");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 6){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Terengganu");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 7){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Pahang");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 8){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Selangor");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 9){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Negeri Sembilan");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 10){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Melaka");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 11){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Johor");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 12){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Sabah");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if (position == 13){
                    Query query = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Bloodbank").orderByChild("state").equalTo("Sarawak");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            arrayList.clear();

                            for (DataSnapshot bank : dataSnapshot.getChildren()){
                                Bloodbank bloodbank = bank.getValue(Bloodbank.class);
                                arrayList.add(bloodbank);
                            }
                            ListAdapter adapter = new ListAdapter(bloodBanklist.this,arrayList);
                            lvblood.setAdapter(adapter);
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


        lvblood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bloodbank bloodbank = arrayList.get(position);
                String name = bloodbank.getBankname();
                String bankaddress = bloodbank.getAddress();
                String bankcontact = bloodbank.getPhone();
                String bankstate = bloodbank.getState();
                String latitud = bloodbank.getLatitud();
                String longtitud = bloodbank.getLongtitud();
                Intent intentinfobank = new Intent(bloodBanklist.this,blood_information.class);
                intentinfobank.putExtra("bankname",name);
                intentinfobank.putExtra("bankaddress",bankaddress);
                intentinfobank.putExtra("bankphone",bankcontact);
                intentinfobank.putExtra("bankstate",bankstate);
                intentinfobank.putExtra("latitud",latitud);
                intentinfobank.putExtra("longtitud",longtitud);

                startActivity(intentinfobank);

            }
        });


    }
}