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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PatientLogin extends AppCompatActivity {

    Context context;
    private Button login;
    private EditText p_logPhone, p_logPassword;
    private TextView p_reg;
    private FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        context = this;
        login = findViewById(R.id.btnPatientLogin);

        p_logPhone = findViewById(R.id.editPatientLoginEmail);
        p_logPassword = findViewById(R.id.editPatientLoginPassword);

        p_reg = findViewById(R.id.textRegPatient);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  getPhone = p_logPhone.getText().toString();
                String getPassword = p_logPassword.getText().toString();


                if (!getPhone.isEmpty()){
                    if (!getPassword.isEmpty()){

                        reference = FirebaseDatabase.getInstance().getReference("patients");
                        Query query = reference.orderByChild("mobile").equalTo(getPhone);

                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    String passwordDB = snapshot.child(getPhone).child("password").getValue(String.class);
                                    if (!passwordDB.isEmpty()){

                                    if (passwordDB.equals(getPassword)){

                                            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, PatientMain.class);
                                            intent.putExtra("PHONE", getPhone);
                                            startActivity(intent);
                                        }else{
                                            Toast t = Toast.makeText(context, "Phone Number or Password is Wrong", Toast.LENGTH_SHORT);
                                            t.show();
                                        }
                                    }
                                }else{
                                    Toast t = Toast.makeText(context, "Phone Number or Password is Wrong", Toast.LENGTH_SHORT);
                                    t.show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

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

        p_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PatientRegisterDetails.class));
            }
        });
    }
}