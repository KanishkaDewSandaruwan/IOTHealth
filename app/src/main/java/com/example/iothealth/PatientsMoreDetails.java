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

public class PatientsMoreDetails extends AppCompatActivity {
    Button pdetailBack;
    TextView name,address,nic,email,gender,mobile, head;
    String pEmail,DPID,DOCEMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_more_details);

        pdetailBack = findViewById(R.id.btnPDtailBack);

        name = findViewById(R.id.txtPName);
        address = findViewById(R.id.txtPAddress);
        nic = findViewById(R.id.txtPNIC);
        email = findViewById(R.id.txtPEmail);
        gender = findViewById(R.id.txtPGender);
        mobile = findViewById(R.id.txtPMobile);
        head = findViewById(R.id.txtPDetailHead);

        Intent intent = getIntent();
        pEmail = intent.getStringExtra("EMAIL");
        DPID = intent.getStringExtra("DPID");
        DOCEMAIL = intent.getStringExtra("DOCEMAIL");

        pdetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientsMoreDetails.this, DoctorMain.class);
                intent.putExtra("EMAIL", DOCEMAIL);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference("doctorPatients")
                .orderByChild("email")
                .equalTo(pEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    PatientDetails patientDetails = snapshot.child(DPID).getValue(PatientDetails.class);

                    name.setText(patientDetails.getName());
                    address.setText(patientDetails.getAddress());
                    nic.setText(patientDetails.getNic());
                    email.setText(patientDetails.getEmail());
                    gender.setText(patientDetails.getGender());
                    mobile.setText(patientDetails.getMobile());

                    head.setText(patientDetails.getName()+" Details");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}