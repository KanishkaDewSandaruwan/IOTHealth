package com.example.iothealth;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DocPatientArrayAdapter extends ArrayAdapter {

    private Activity mContext;
    List<PatientDetails> patientDetailsList;

    public DocPatientArrayAdapter(Activity mContext, List<PatientDetails> patientDetailsList) {
        super(mContext, R.layout.one_doctor_layout, patientDetailsList);

        this.mContext = mContext;
        this.patientDetailsList = patientDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.one_doctor_layout, null, true);

        TextView patient_name = listItemView.findViewById(R.id.text_view_doc_name);
        TextView patient_email = listItemView.findViewById(R.id.text_view_doc_email);

        PatientDetails patientDetails = patientDetailsList.get(position);

        patient_name.setText(patientDetails.getName());
        patient_email.setText(patientDetails.getEmail());

        return listItemView;
    }
}

