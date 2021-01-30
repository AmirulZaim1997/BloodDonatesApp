package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class insertTest extends AppCompatActivity {

    private EditText bankevent,address,phone,state,donateevent,dtime,ddate,dlocation,dstate,latitud,longtitud;
    private Button addbank,addDonation;
    Bloodbank bloodbank;
    donation donation;
    DatabaseReference reff,reffdona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_test);

        bankevent = findViewById(R.id.inputbankName);
        address = findViewById(R.id.inputAddress);
        phone=findViewById(R.id.inputPhone);
        state=findViewById(R.id.inputState);
        addbank=findViewById(R.id.bankbtn);
        donateevent = findViewById(R.id.inputevent);
        dtime = findViewById(R.id.inputtime);
        ddate = findViewById(R.id.inputdate);
        dlocation = findViewById(R.id.inputlocation);
        dstate = findViewById(R.id.inputstate);
        addDonation = findViewById(R.id.donationBtn);
        latitud =findViewById(R.id.inputLat);
        longtitud = findViewById(R.id.inputLong);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        bloodbank = new Bloodbank();
        donation = new donation();
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Bloodbank");
        reffdona = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("DonationEvent");

        addbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodbank.setBankname(bankevent.getText().toString().trim());
                bloodbank.setAddress(address.getText().toString().trim());
                bloodbank.setPhone(phone.getText().toString().trim());
                bloodbank.setState(state.getText().toString().trim());
                bloodbank.setLatitud(latitud.getText().toString().trim());
                bloodbank.setLongtitud(longtitud.getText().toString().trim());
                reff.push().setValue(bloodbank);
                Toast.makeText(insertTest.this,"Data insert success",Toast.LENGTH_LONG).show();
                bankevent.setText("");
                address.setText("");
                phone.setText("");
                state.setText("");
                latitud.setText("");
                longtitud.setText("");

            }
        });



      ddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(insertTest.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        ddate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

      addDonation.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              donation.setEvent(donateevent.getText().toString().trim());
              donation.setDate(ddate.getText().toString().trim());
              donation.setTime(dtime.getText().toString().trim());
              donation.setLocation(dlocation.getText().toString().trim());
              donation.setState(dstate.getText().toString().trim());
              reffdona.push().setValue(donation);
              Toast.makeText(insertTest.this,"Data donation insert success",Toast.LENGTH_LONG).show();
              donateevent.setText("");
              ddate.setText("");
              dtime.setText("");
              dlocation.setText("");
              dstate.setText("");


          }
      });


        /*insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = name.getText().toString();
                String eventDate = etdate.getText().toString();
                String eventTime = time.getText().toString();
                String eventLocation = location.getText().toString();
                boolean isadded = mydb.addData(eventName,eventDate,eventTime,eventLocation);

                if(isadded ==true){
                    Toast.makeText(getApplicationContext(),"Data successfully inserted!",Toast.LENGTH_LONG).show();
                    name.setText("");
                    etdate.setText("");
                    time.setText("");
                    location.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Data insert fail",Toast.LENGTH_SHORT).show();
                }
            }
        });*/




    }
}