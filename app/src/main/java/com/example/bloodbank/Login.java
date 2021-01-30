package com.example.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText email2, pass2;
    private Button login;
    private TextView forgot;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email2 = findViewById(R.id.email_in2);
        pass2 = findViewById(R.id.pass_in2);
        login = findViewById(R.id.login_bt2);
        forgot = findViewById(R.id.forgot_btn);

        mAuth = FirebaseAuth.getInstance();

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,
                        Reset.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
                //Toast.makeText(getApplicationContext(),"Login.Masuk", Toast.LENGTH_SHORT).show();

            }

            private void loginUser() {
                String email_log = email2.getText().toString().trim();
                String pass_log = pass2.getText().toString().trim();

                if (email_log.isEmpty()) {
                    email2.setError("Email Address is required");
                    email2.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email_log).matches()) {
                    email2.setError("Enter valid email address");
                    email2.requestFocus();
                    return;
                }

                if (pass_log.isEmpty()) {
                    pass2.setError("Password is required");
                    pass2.requestFocus();
                    return;
                }

                if (pass_log.length() < 6) {
                    pass2.setError("Minimum 6 characters");
                    pass2.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email_log,pass_log)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    //Toast.makeText(getApplicationContext(),"Login.Jadi", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    if(user.isEmailVerified()){
                                        String email = email_log.toString();
                                        Intent intent = new Intent(Login.this,Dashboard.class);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                    }
                                    else{
                                        user.sendEmailVerification();
                                        Toast.makeText(getApplicationContext(),"Check email to verify your account", Toast.LENGTH_LONG).show();
                                    }

                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Account does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}