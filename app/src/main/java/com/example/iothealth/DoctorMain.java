package com.example.iothealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorMain extends AppCompatActivity {

    Button addnewPatient;
    Context context;
    ListView patientList;
    List<PatientDetails> patientDetailsList;
    DatabaseReference dRef,databaseReference;
    String email;
    TextView didView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        context = this;
        addnewPatient = findViewById(R.id.btnDAddPatients);
        didView = findViewById(R.id.txtDID);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");





        addnewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorMain.this, PatientsList.class);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });

        patientList = (ListView) findViewById(R.id.patientList);
        patientDetailsList = new ArrayList<>();

        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PatientDetails patientDetails = patientDetailsList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                String getEmail = patientDetails.getEmail();
                String DPID = patientDetails.getDPID();

                builder.setTitle(patientDetails.getName() + "Details");
                builder.setPositiveButton("History", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DoctorMain.this, MedicalHistory.class);
                        intent.putExtra("EMAIL", getEmail);
                        intent.putExtra("DPID", DPID);
                        intent.putExtra("DOCEMAIL", email);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DoctorMain.this, PatientsMoreDetails.class);
                        intent.putExtra("EMAIL", getEmail);
                        intent.putExtra("DPID", DPID);
                        intent.putExtra("DOCEMAIL", email);
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference = FirebaseDatabase.getInstance().getReference("doctorPatients").child(patientDetails.getDPID());
                        databaseReference.removeValue();

                        Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, DoctorMain.class);
                        intent.putExtra("EMAIL", email);
                        startActivity(intent);
                    }
                });
                builder.show();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = FirebaseDatabase.getInstance().getReference("doctorPatients")
                .orderByChild("doctorEmail")
                .equalTo(email);
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            patientDetailsList.clear();

                for (DataSnapshot ds : snapshot.getChildren()){
                    PatientDetails patientDetails = ds.getValue(PatientDetails.class);
                    patientDetailsList.add(patientDetails);
                }

                DocPatientArrayAdapter adapter = new DocPatientArrayAdapter(DoctorMain.this, patientDetailsList);
                patientList.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}