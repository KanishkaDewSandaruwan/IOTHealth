package com.example.iothealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MYDetails extends AppCompatActivity {

    Button patientsBack;
    TextView name,address,nic,email,gender,mobile;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_y_details);

        patientsBack = findViewById(R.id.btnDtailBack);

        name = findViewById(R.id.txtName);
        address = findViewById(R.id.txtAddress);
        nic = findViewById(R.id.txtNIC);
        email = findViewById(R.id.txtEmail);
        gender = findViewById(R.id.txtGender);
        mobile = findViewById(R.id.txtMobile);

        Intent intent = getIntent();
        phone = intent.getStringExtra("PHONE");

        patientsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MYDetails.this, PatientMain.class);
                intent.putExtra("PHONE", phone);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference("patients")
                .orderByChild("mobile")
                .equalTo(phone);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    PatientDetails patientDetails = snapshot.child(phone).getValue(PatientDetails.class);

                    name.setText(patientDetails.getName());
                    address.setText(patientDetails.getAddress());
                    nic.setText(patientDetails.getNic());
                    email.setText(patientDetails.getEmail());
                    gender.setText(patientDetails.getGender());
                    mobile.setText(patientDetails.getMobile());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}