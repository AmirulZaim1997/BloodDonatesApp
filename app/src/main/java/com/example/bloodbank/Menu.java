package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    private Button view,acapmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        view = findViewById(R.id.view_btn);
        acapmenu = findViewById(R.id.acapbtn);


        acapmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentacap = new Intent(Menu.this,acapmain.class);
                intentacap.putExtra("email",getIntent().getStringExtra("email"));
                startActivity(intentacap);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, ViewUser.class));
            }
        });
    }
}