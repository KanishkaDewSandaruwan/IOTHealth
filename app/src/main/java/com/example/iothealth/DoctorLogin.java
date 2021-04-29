package com.example.iothealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorLogin extends AppCompatActivity {

    Button DocLog;
    TextView DocReg;
    EditText Doc_Email, Doc_Password;
    FirebaseAuth DLAuth;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        context = this;

        DocReg = findViewById(R.id.textDreg);
        DocLog = findViewById(R.id.btnDoctorLogin);

        Doc_Email = findViewById(R.id.editDoctorEmailLog);
        Doc_Password = findViewById(R.id.editDoctorPasswordLog);
        DLAuth = FirebaseAuth.getInstance();

        DocReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, DoctorRegistration.class));
            }
        });

        DocLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  getEmai = Doc_Email.getText().toString();
                String getPassword = Doc_Password.getText().toString();

                if (!getEmai.isEmpty()){
                    if (!getPassword.isEmpty()){
                        DLAuth.signInWithEmailAndPassword(getEmai, getPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast t = Toast.makeText(context, "Login Success Successfully", Toast.LENGTH_SHORT);
                                t.show();
                                Intent intent = new Intent(DoctorLogin.this, DoctorMain.class);
                                intent.putExtra("EMAIL", getEmai);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Login Error !!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast t = Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }else{
                    Toast t = Toast.makeText(context, "Please Enter Email", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
}