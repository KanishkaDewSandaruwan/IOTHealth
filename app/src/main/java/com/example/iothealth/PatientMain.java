package com.example.iothealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientMain extends AppCompatActivity {

    Button btnDetails, mesure, history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        btnDetails = findViewById(R.id.btnMyDetails);
        mesure = findViewById(R.id.btnMeusre);
        history = findViewById(R.id.btnPHistory);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("PHONE");

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMain.this, MYDetails.class);
                intent.putExtra("PHONE", phone);
                startActivity(intent);
            }
        });

        mesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMain.this, Mesure.class);
                intent.putExtra("PHONE", phone);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMain.this, PatientsMedicalHistory.class);
                intent.putExtra("PHONE", phone);
                startActivity(intent);
            }
        });
    }
}