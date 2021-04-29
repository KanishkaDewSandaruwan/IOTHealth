package com.example.iothealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientsList extends AppCompatActivity {

    Context context;
    ListView patientListView;
    List<PatientDetails> patientDetailsList;
    DatabaseReference dRef;
    private DatabaseReference reference;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);
        context = this;
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
//        String DID = intent.getStringExtra("DID");

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientsList.this, DoctorMain.class);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });


        patientListView = (ListView) findViewById(R.id.listView);
        patientDetailsList = new ArrayList<>();

        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PatientDetails patientDetails = patientDetailsList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Add New Patients");
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(context, PatientsList.class));
                    }
                });

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        reference = FirebaseDatabase.getInstance().getReference("doctorPatients");
                        
                        String getName = patientDetails.getName();
                        String getEmail = patientDetails.getEmail();
                        String getAddress = patientDetails.getAddress();
                        String getNIC = patientDetails.getNic();
                        String getMobile = patientDetails.getMobile();
                        String getGender = patientDetails.getGender();
                        String getPID = patientDetails.getPID();
                        String getDoctorEmail = email;

                        String id = reference.push().getKey();

                        PatientDetails details = new PatientDetails(id, getPID, getEmail, getName, getAddress, getNIC, getMobile, getGender, getDoctorEmail);
                        reference.child(id).setValue(details);

                        Intent intent = new Intent(context, DoctorMain.class);
                        intent.putExtra("EMAIL", email);
                        startActivity(intent);

                        Toast.makeText(context, "This Patient Adding Success", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = FirebaseDatabase.getInstance().getReference("patients");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            patientDetailsList.clear();

            for (DataSnapshot ds : snapshot.getChildren()){
                PatientDetails patientDocDetails = ds.getValue(PatientDetails.class);
                patientDetailsList.add(patientDocDetails);
            }

            DocPatientArrayAdapter adapter = new DocPatientArrayAdapter(PatientsList.this, patientDetailsList);
            patientListView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}