package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewUser extends AppCompatActivity {

    private ListView userlist;
    FirebaseDatabase dbase;
    DatabaseReference refdb;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    UserData user;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        user = new UserData();
        userlist = findViewById(R.id.user_list);

        dbase = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/");
        refdb = dbase.getReference("Donor");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.list_user,R.id.name, list);
        refdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    user = ds.getValue(UserData.class);
                    uid = ds.getKey();
                    //Toast.makeText(getApplicationContext(),uid, Toast.LENGTH_LONG).show();
                    list.add(user.getName().toString());
                    //+ " | Type " + user.getBtype().toString() + " |");
                }

                //String target = new list.add(user.getName().toString());

                userlist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String keyname = refdb.child("Users").getKey();

                //Toast.makeText(getApplicationContext(),uid, Toast.LENGTH_LONG).show();
                String targetuser = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(ViewUser.this, Profile.class);
                intent.putExtra("name",targetuser);
                Toast.makeText(getApplicationContext(),targetuser, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }
}