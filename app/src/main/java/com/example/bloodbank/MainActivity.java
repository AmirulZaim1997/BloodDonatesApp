package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText email, pass, vpass;
    private Button reg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_in);
        pass = findViewById(R.id.pass_in);
        vpass = findViewById(R.id.vpass_in);
        reg = findViewById(R.id.reg_bt);

        mAuth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        }

    private void Register() {
        String email_reg = email.getText().toString().trim();
        String pass_reg = pass.getText().toString().trim();
        String vpass_reg = vpass.getText().toString().trim();

        if (email_reg.isEmpty()) {
            email.setError("Email Address is required");
            email.requestFocus();
            return;
        }

        if (pass_reg.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        if (vpass_reg.isEmpty()) {
            vpass.setError("Password is required");
            vpass.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_reg).matches()) {
            email.setError("Enter valid email address");
            email.requestFocus();
            return;
        }

        if (pass_reg.length() < 6) {
            pass.setError("Minimum 6 characters");
            pass.requestFocus();
            return;
        }

        if (!vpass_reg.equals(pass_reg)){
            vpass.setError("Password did not match");
            vpass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email_reg,pass_reg)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        User user = new User(email_reg);

                        FirebaseDatabase.getInstance("https://bloodbank-6be0b-default-rtdb.firebaseio.com/").getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(MainActivity.this,Login.class));
                                    //Toast.makeText(getApplicationContext(),"Register Jadi", Toast.LENGTH_SHORT).show();
                                }else{
                                    //Toast.makeText(getApplicationContext(),"Register X Jadi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        //Toast.makeText(getApplicationContext(),"Register X masuk", Toast.LENGTH_SHORT).show();
                    }

                }
            });
    }


}
