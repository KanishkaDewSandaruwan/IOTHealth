package com.example.iothealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class DoctorRegisterDetails extends AppCompatActivity {

    Context context;
    private Button add;
    private EditText name,address,mobile, nic, dreg;
    private RadioGroup gender;
    private DatabaseReference reference;
    private RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register_details);

        context = this;

        add = findViewById(R.id.btnAddDoctorDetails);

        name = findViewById(R.id.editDName);
        address = findViewById(R.id.editDAddress);
        mobile = findViewById(R.id.editDMobile);
        nic = findViewById(R.id.editDNIC);
        dreg = findViewById(R.id.editDoctorRegistrationNumber);

        male = findViewById(R.id.radioDMale);
        female = findViewById(R.id.radioDFeMale);
        reference = FirebaseDatabase.getInstance().getReference("doctor");

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getAddress = address.getText().toString();
                String getMobile = mobile.getText().toString();
                String getNIC = nic.getText().toString();
                String getRehNum = dreg.getText().toString();
                String gender;

                if (male.isChecked()){
                    gender = "Male";
                }else if (female.isChecked()){
                    gender = "Female";
                }else{
                    gender = "NO";
                }

                if (!getName.isEmpty()){
                    if (!getAddress.isEmpty()){
                        if (gender != "NO"){
                            if (!getMobile.isEmpty()){
                                if (!getNIC.isEmpty()){
                                    if (!getRehNum.isEmpty()){
                                        if (isValidPhone(getMobile)){
                                            String id = reference.push().getKey();

                                            DoctorDetails details = new DoctorDetails(id, email, getName, getAddress,getNIC ,getMobile , gender, getRehNum);
                                            reference.child(id).setValue(details);
                                            Toast.makeText(context, "Detail Save Success", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(context, DoctorLogin.class));
                                        }else{
                                            Toast.makeText(context, "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(context, "Please Enter Doctor Registration Number", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(context, "Please Enter NIC Number", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(context, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(context, "Please Select Gender", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "Please Enter Address", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidPhone(String phone)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length() < 6 || phone.length() > 13)
            {
                check = false;

            }
            else
            {
                check = true;

            }
        }
        else
        {
            check=false;
        }
        return check;
    }
}