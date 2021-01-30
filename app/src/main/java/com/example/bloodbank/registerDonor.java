package com.example.bloodbank;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class registerDonor extends AppCompatActivity {

    private TextView bloodtv,donortv,datetv,addresstv;
    private EditText name,phoneNo,age, status,D_date,city;

    private Button reset,save;
    Spinner sp1,sp2;
   // String[] city = {"Select City","Selangor","W.Persekutuan","Perak","Pahang","N.Sembilan","Johor","Kelantan","Perlis","Terengganu","P.Pinang","Sabah","Sarawak","Melaka"};
   // ArrayAdapter<String> adapter;
   // String record="";
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference reff;
    Donor donor;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donor);
        bloodtv = findViewById(R.id.bloodtv);
        donortv = findViewById(R.id.detailtv);
        datetv = findViewById(R.id.datetv);
        addresstv = findViewById(R.id.Addresstv);
        name = findViewById(R.id.D_name);
        phoneNo = findViewById(R.id.D_phoneno);
        sp2 = (Spinner)findViewById(R.id.bloodtype);
        status = findViewById(R.id.status);
        D_date = findViewById(R.id.D_date);
        age = findViewById(R.id.age);
        city = findViewById(R.id.city);
        sp1 = (Spinner) findViewById(R.id.spinner);
        reset = findViewById(R.id.resetBtn);
        save = findViewById(R.id.saveBtn);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String email = getIntent().getStringExtra("email");

        D_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        registerDonor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        D_date.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
        List<String> bloodType = new ArrayList<>();
        bloodType.add(0, "Select Blood Type");
        bloodType.add("A+");
        bloodType.add("A-");
        bloodType.add("B+");
        bloodType.add("B-");
        bloodType.add("AB+");
        bloodType.add("AB-");
        bloodType.add("O+");
        bloodType.add("O-");

        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodType);

        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp2.setAdapter(Adapter);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Blood Type")) {

                } else {
                    String item = adapterView.getItemAtPosition(i).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        List<String> state = new ArrayList<>();
        state.add(0, "Select State");
        state.add("Selangor");
        state.add("W.Persekutuan");
        state.add("Perak");
        state.add("Pahang");
        state.add("N.Sembilan");
        state.add("Johor");
        state.add("Kelantan");
        state.add("Perlis");
        state.add("Terengganu");
        state.add("P.Pinang");
        state.add("Sabah");
        state.add("Sarawak");
        state.add("Melaka");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, state);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(dataAdapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose city")) {

                } else {
                    String item = adapterView.getItemAtPosition(i).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        donor = new Donor();
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // int Date=Integer.parseInt(D_date.getText().toString().trim());
                int Age = Integer.parseInt(age.getText().toString().trim());
                int PhoneNo = Integer.parseInt(phoneNo.getText().toString().trim());

                donor.setName(name.getText().toString().trim());
                donor.setPhoneNo(PhoneNo);
                donor.setBloodType(sp2.getSelectedItem().toString());
                donor.setStatus(status.getText().toString().trim());
                donor.setDate(D_date.getText().toString().trim());
                donor.setAge(Age);
                donor.setState(city.getText().toString().trim());
                donor.setCity(sp1.getSelectedItem().toString());
                donor.setEmail(email);
                //reff.push().setValue(donor);
                reff.child(String.valueOf(maxid + 1)).setValue(donor);
                Toast.makeText(registerDonor.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    name.getText().clear();
                name.getText().clear();
                phoneNo.getText().clear();
                age.getText().clear();
               // sp2.getSelectedItem().clear();
                status.getText().clear();
                D_date.getText().clear();
                city.getText().clear();
               //
                // sp2.getText().clear();




            }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* name.getText().clear();
                name.getText().clear();
                phoneNo.getText().clear();
                age.getText().clear();
                sp2.getSelectedItem();
                status.getText().clear();
                D_date.getText().clear();
                city.getText().clear();*/

                //Intent intent = new Intent(registerDonor.this,viewDonor.class);
               // startActivity(intent);

            }
        });
    }
}