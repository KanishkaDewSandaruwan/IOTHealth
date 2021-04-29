package com.example.iothealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;

public class EditPatients extends AppCompatActivity {

    Context context;
    private Button edit;
    private EditText name,address,mobile, nic;
    private RadioGroup gender;
    private DatabaseReference reference;
    private RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patients);

        context = this;

        name = findViewById(R.id.editUpdateName);
        address = findViewById(R.id.editUpdateAddress);
        mobile = findViewById(R.id.editUpdateMobile);
        nic = findViewById(R.id.editUpdateNIC);


    }
}