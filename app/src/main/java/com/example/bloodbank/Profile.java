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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView name,age,bloodtype,dater,dstat,state,city,phone;
    private Button call;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name_out);
        age = findViewById(R.id.age_out);
        bloodtype = findViewById(R.id.type_out);
        dater = findViewById(R.id.hstat_out);
        dstat = findViewById(R.id.dstat_out);
        state = findViewById(R.id.state_out);
        city = findViewById(R.id.city_out);
        phone = findViewById(R.id.phone_out);
        call = findViewById(R.id.call_bt);

        String namee = getIntent().getStringExtra("name");

        //Toast.makeText(getApplicationContext(),namee, Toast.LENGTH_LONG).show();

        db = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Donor");
        //Nanti ubah balik woiii
        Query query = db.orderByChild("name").equalTo(namee);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    for (DataSnapshot child: snapshot.getChildren()) {

                        String namedb = child.child("name").getValue().toString();
                        String agedb = child.child("age").getValue().toString();
                        String bloodtypedb = child.child("bloodType").getValue().toString();
                        String date = child.child("date").getValue().toString();
                        String status = child.child("status").getValue().toString();
                        String statedb = child.child("state").getValue().toString();
                        String citydb = child.child("city").getValue().toString();
                        String phonedb = child.child("phoneNo").getValue().toString();

                        name.setText(namedb);
                        age.setText(agedb);
                        bloodtype.setText(bloodtypedb);
                        dater.setText(date);
                        dstat.setText(status);
                        state.setText(statedb);
                        city.setText(citydb);
                        phone.setText(phonedb);

                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(getApplicationContext(),phonedb, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phonedb));
                                startActivity(intent);
                            }
                        });
                    }
                }
                else{
                    Toast.makeText(Profile.this,"Data not exist or not complete",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}