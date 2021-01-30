package com.example.bloodbank;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {

    private EditText email_reset;
    private Button reset;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email_reset = findViewById(R.id.email_in3);
        reset = findViewById(R.id.reset_bt);

        mAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPass();

            }

            private void resetPass() {
                String email = email_reset.getText().toString().trim();

                if (email.isEmpty()) {
                    email_reset.setError("Email Address is required");
                    email_reset.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_reset.setError("Enter valid email address");
                    email_reset.requestFocus();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Reset password request have been sent to email", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Email does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}