package com.example.bloodbank;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bloodRequest extends AppCompatActivity {

    private Spinner dropdown;
    private Spinner dropdown2;
    private CheckBox accident, cancer, pregnancy, transplant, surgery, others;
    private EditText hospital, message;
    private Button submit;
    private TextView home;

    DatabaseReference reff;
    ActionBar actionBar;

    Request request;
    int i = 0;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);

        setTitle("Blood Request");

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF0000"));


        accident = findViewById(R.id.cbaccident);
        cancer = findViewById(R.id.cbcancer);
        pregnancy = findViewById(R.id.cbpreg);
        transplant = findViewById(R.id.cbtrans);
        surgery = findViewById(R.id.cbsurgery);
        others = findViewById(R.id.cbothers);

        hospital = findViewById(R.id.hospitaledtxt);
        message = findViewById(R.id.msgedtxt);
        submit = findViewById(R.id.submitbtn);

        home = findViewById(R.id.hometxt);

        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Request");

        request = new Request();

        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1);

        //create a list of items for the spinner.
        String[] items = new String[]{"Whole Blood", "Platelets", "AB Plasma", "Double Red Cell", "Cord Blood"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown2 = findViewById(R.id.spinner2);

        String[] bloodtype = new String[]{"A +","A -", "B +", "B -", "AB -", "O +", "O -", "OH +", "Others"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodtype);
        dropdown2.setAdapter(adapter1);

        String a = "accident";
        String c = "cancer";
        String p = "pregnancy";
        String t = "transplant";
        String s = "surgery";
        String o = "others";

        /*reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    i = (int)snapshot.getChildrenCount();
                    maxid = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        //back to home page
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(bloodRequest.this, Dashboard.class);
                startActivity(intenthome);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accident.isChecked()) {
                    request.setReasons(a);
                }
                else {

                }

                if (cancer.isChecked()) {
                    request.setReasons(c);
                }
                else {

                }

                if (pregnancy.isChecked()) {
                    request.setReasons(p);
                }
                else {

                }

                if (transplant.isChecked()) {
                    request.setReasons(t);
                }
                else {

                }

                if (surgery.isChecked()) {
                    request.setReasons(s);
                }
                else {

                }

                if (others.isChecked()) {
                    request.setReasons(o);
                }
                else {

                }

                request.setBloodtype(dropdown.getSelectedItem().toString());
                request.setBloodgroup(dropdown2.getSelectedItem().toString());

                request.setHospital(hospital.getText().toString());
                request.setMessage(message.getText().toString());

                reff.push().setValue(request);
                Toast.makeText(bloodRequest.this, "Request has been send!", Toast.LENGTH_LONG).show();

                //hospital.setText("");
                //message.setText("");

                if (accident.isChecked()) {
                    accident.setChecked(false);
                    accident.setSelected(false);
                }
                else if (cancer.isChecked()) {
                    cancer.setChecked(false);
                    cancer.setSelected(false);
                }
                else if (pregnancy.isChecked()) {
                    pregnancy.setChecked(false);
                    pregnancy.setSelected(false);
                }
                else if (transplant.isChecked()) {
                    transplant.setChecked(false);
                    transplant.setSelected(false);
                }
                else if (surgery.isChecked()) {
                    surgery.setChecked(false);
                    surgery.setSelected(false);
                }
                else if (others.isChecked()) {
                    others.setChecked(false);
                    others.setSelected(false);
                }
            }
        });
    }

}