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

public class PatientRegisterDetails extends AppCompatActivity {

    Context context;
    private Button add;
    private EditText name,address,mobile, nic,password,confPass,email;
    private RadioGroup gender;
    private DatabaseReference reference;
    private RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register_details);

        context = this;

        add = findViewById(R.id.btnAddDetails);

        name = findViewById(R.id.editName);
        address = findViewById(R.id.editAddress);
        mobile = findViewById(R.id.editMobile);
        nic = findViewById(R.id.editNIC);
        password = findViewById(R.id.editPassword);
        confPass = findViewById(R.id.editConfPassword);
        email = findViewById(R.id.editPEmails);

        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFeMale);


        reference = FirebaseDatabase.getInstance().getReference("patients");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getAddress = address.getText().toString();
                String getMobile = mobile.getText().toString();
                String getNIC = nic.getText().toString();
                String getPassword = password.getText().toString();
                String getConfPass = confPass.getText().toString();
                String getEmail = email.getText().toString();
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
                                    if (isValidPhone(getMobile)){
                                        if (!getPassword.isEmpty()){
                                            if (!getConfPass.isEmpty()){
                                                if (getPassword.equals(getConfPass)){
                                                    if (!getEmail.isEmpty()){


                                                        String id = reference.push().getKey();

                                                        PatientDetails details = new PatientDetails(id, getEmail, getName, getAddress, getNIC,getMobile, gender,getPassword);
                                                        reference.child(getMobile).setValue(details);
                                                        Toast.makeText(context, "Detail Save Success", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(context, PatientLogin.class));

                                                    }else{
                                                        Toast.makeText(context, "Please Enter Email", Toast.LENGTH_SHORT).show();
                                                    }
                                                }else{
                                                    Toast.makeText(context, "Password is Not Match", Toast.LENGTH_SHORT).show();
                                                }

                                            }else{
                                                Toast.makeText(context, "Please Enter Conform Password", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show();
                                        }

                                    }else{
                                        Toast.makeText(context, "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
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