package com.example.bloodbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class updateUser extends AppCompatActivity {

    private TextView etName,etAge,etBloodGroup,etContactNo,etDonorstat,etState,etCity,etDonorID;
    private Button updateBtn,resetBtn;
    Donor donor;
    private DatabaseReference reff,reffupdate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etBloodGroup = findViewById(R.id.etBloodGroup);
        etContactNo = findViewById(R.id.etContactNo);
        etDonorstat = findViewById(R.id.etDonateStatus);
        etState = findViewById(R.id.etState);
        etCity = findViewById(R.id.etCityu);

        updateBtn = findViewById(R.id.updateBtn);
        resetBtn = findViewById(R.id.resetBtn);



        setTitle("APP TITLE");
        String emails = getIntent().getStringExtra("email");
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
        Query query = reff.orderByChild("email").equalTo(emails);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {



                    String namedb = child.child("name").getValue().toString();
                    String agedb = child.child("age").getValue().toString();
                    String bloodtypedb = child.child("bloodType").getValue().toString();
                    String dates = child.child("date").getValue().toString();
                    String status = child.child("status").getValue().toString();
                    String statedb = child.child("state").getValue().toString();
                    String citydb = child.child("city").getValue().toString();
                    String phonedb = child.child("phoneNo").getValue().toString();
                    String email = child.child("email").getValue().toString();

                    etName.setText(namedb);
                    etAge.setText(agedb);
                    etBloodGroup.setText(bloodtypedb);
                    etDonorstat.setText(status);
                    etState.setText(statedb);
                    etCity.setText(citydb);
                    etContactNo.setText(phonedb);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                         .build();

                firebaseUser.updateProfile(request)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(updateUser.this, "Succesful Update Profile", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(updateUser.this, "Failed to updated", Toast.LENGTH_SHORT).show();



                            }
                        });





            }
        });










        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot child: dataSnapshot.getChildren()) {

                            String namedb = child.child("name").getValue().toString();
                            String agedb = child.child("age").getValue().toString();
                            String bloodtypedb = child.child("bloodType").getValue().toString();
                            String date = child.child("date").getValue().toString();
                            String status = child.child("status").getValue().toString();
                            String statedb = child.child("state").getValue().toString();
                            String citydb = child.child("city").getValue().toString();
                            String phonedb = child.child("phoneNo").getValue().toString();

                            etName.setText(namedb);
                            etAge.setText(agedb);
                            etBloodGroup.setText(bloodtypedb);
                            etDonorstat.setText(status);
                            etState.setText(statedb);
                            etCity.setText(citydb);
                            etContactNo.setText(phonedb);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}