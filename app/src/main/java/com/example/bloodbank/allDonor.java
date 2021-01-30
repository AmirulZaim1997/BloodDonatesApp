package com.example.bloodbank;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.bloodbank.R.menu.menu;

public class allDonor extends AppCompatActivity {
    private ImageView logo;
    private RecyclerView recyclerView;
    private TableViewAdapter tableViewAdapter;
    private HorizontalScrollView horizontalScrollView;
    private ArrayList<Record> list;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_donor);

        horizontalScrollView = findViewById(R.id.horizontalScrollView2);
        logo=findViewById(R.id.logo);
        reff = FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference().child("Record");



        recyclerView =findViewById(R.id.recyclerViewDeliveryProductList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        tableViewAdapter = new TableViewAdapter(this,list);
        recyclerView.setAdapter(tableViewAdapter);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Record donor = dataSnapshot.getValue(Record.class);
                    list.add(donor);
                }
                tableViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Location Here..");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                tableViewAdapter.getFilter().filter(s.toString());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
