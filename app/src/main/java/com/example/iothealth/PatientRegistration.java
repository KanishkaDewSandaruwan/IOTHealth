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

public class PatientRegistration extends AppCompatActivity {

    Context context;
    private Button Patient_Register;
    private EditText p_Reg_email, p_Reg_password, p_Reg_RePassword;
    private TextView p_login;
    private FirebaseAuth pAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        context = this;
        p_login = findViewById(R.id.textPatientLogin);

        p_Reg_email = findViewById(R.id.editPatientRegEmail);
        p_Reg_password = findViewById(R.id.editPatientRegPassword);
        p_Reg_RePassword = findViewById(R.id.editPatientRegRePassword);

        Patient_Register = findViewById(R.id.btnPatientRegister);
        pAuth = FirebaseAuth.getInstance();

        p_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientRegistration.this, PatientLogin.class));
            }
        });

        Patient_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = p_Reg_email.getText().toString();
                String password = p_Reg_password.getText().toString();
                String rePassword = p_Reg_RePassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        if (password.length() > 6) {
                            if (!rePassword.isEmpty()) {
                                if (password.equals(rePassword)) {

                                    pAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            Toast t = Toast.makeText(context, "Account Creating Success!.. Please Add Yor Personal Details", Toast.LENGTH_SHORT);
                                            t.show();
                                            Intent intent = new Intent(PatientRegistration.this, PatientRegisterDetails.class);
                                            intent.putExtra("EMAIL", email);
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
                                    p_Reg_password.clearFocus();
                                    p_Reg_RePassword.clearFocus();
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