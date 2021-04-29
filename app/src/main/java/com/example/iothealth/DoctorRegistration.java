package com.example.iothealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorRegistration extends AppCompatActivity {

    private EditText dEmail, dPassword, dConfPassword;
    private Button Dreg;
    private FirebaseAuth dAuth;
    private TextView dlog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        dlog = findViewById(R.id.textDoctorLog);

        dEmail = findViewById(R.id.doctorRegEmailAdd);
        dPassword = findViewById(R.id.doctorRegPass);
        dConfPassword = findViewById(R.id.doctorRegConfPass);
        context = this;
        Dreg = findViewById(R.id.doctorReg);

        dAuth = FirebaseAuth.getInstance();

        dlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, DoctorLogin.class));
            }
        });

        Dreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Doctor_Email = dEmail.getText().toString();
                String Doctor_Password = dPassword.getText().toString();
                String Doctor_RePassword = dConfPassword.getText().toString();

                if (!Doctor_Email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(Doctor_Email).matches()) {
                    if (!Doctor_Password.isEmpty()) {
                        if (Doctor_Password.length() > 6) {
                            if (!Doctor_RePassword.isEmpty()) {
                                if (Doctor_Password.equals(Doctor_RePassword)) {

                                    dAuth.createUserWithEmailAndPassword(Doctor_Email, Doctor_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            Toast t = Toast.makeText(context, "Register Successfully.. Please Add Other Details", Toast.LENGTH_SHORT);
                                            t.show();
                                            Intent intent = new Intent(DoctorRegistration.this, DoctorRegisterDetails.class);
                                            intent.putExtra("EMAIL", Doctor_Email);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast t = Toast.makeText(context, "Register Error !!", Toast.LENGTH_SHORT);
                                            t.show();
                                        }
                                    });
                                } else {
                                    Toast t = Toast.makeText(context, "Password is Not Match", Toast.LENGTH_SHORT);
                                    t.show();
                                    dPassword.clearFocus();
                                    dConfPassword.clearFocus();
                                }
                            } else {
                                Toast t = Toast.makeText(context, "Please Confirm Password", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        } else {
                            Toast t = Toast.makeText(context, "Password is Short. Please enter More Than 6 Letters", Toast.LENGTH_SHORT);
                            t.show();
                        }

                    } else {
                        Toast t = Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT);
                        t.show();
                    }
                } else {
                    Toast t = Toast.makeText(context, "Please Enter Email or Valid Email", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
}