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

public class DoctorAdapter extends ArrayAdapter {

    private Activity mContext;
    List<DoctorDetails> doctorDetailsList;

    public DoctorAdapter(Activity mContext, List<DoctorDetails> doctorDetailsList) {
        super(mContext, R.layout.doctor_one_item, doctorDetailsList);

        this.mContext = mContext;
        this.doctorDetailsList = doctorDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View DoclistItemView = inflater.inflate(R.layout.doctor_one_item, null, true);

        TextView doc_name = DoclistItemView.findViewById(R.id.docName);

        DoctorDetails doctorDetails = doctorDetailsList.get(position);

        doc_name.setText(doctorDetails.getName());

        return DoclistItemView;
    }
}
