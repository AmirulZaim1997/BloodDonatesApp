package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class reminder extends AppCompatActivity {

    private EditText description;
    private Button set;
    private TextView event,location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        event = findViewById(R.id.eteventr);
        location = findViewById(R.id.etLocation);
        description = findViewById(R.id.etDes);
        set = findViewById(R.id.setbtn);

        String name = getIntent().getStringExtra("name");
        String locations = getIntent().getStringExtra("location");
        event.setText(name);
        location.setText(locations);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!location.getText().toString().isEmpty()&&!description.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, event.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, false);
                    intent.putExtra(Intent.EXTRA_EMAIL,"");
                    startActivity(intent);

                }else{
                    Toast.makeText(reminder.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}