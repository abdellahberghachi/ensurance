package com.hao.e_nsurance.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.hao.e_nsurance.R;

import java.util.Calendar;


public class VehiculeContratFragment extends Fragment {

    ImageView calPicker1, calPicker2;
    TextInputEditText valable_du, valable_au;
    int year1, month1, day1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_vehicule_contrat, container, false);

        calPicker1 = (ImageView) rootView.findViewById(R.id.cal_picker1);
        calPicker2 = (ImageView) rootView.findViewById(R.id.cal_picker2);
        valable_du = (TextInputEditText) rootView.findViewById(R.id.tie_valable_du);
        valable_au = (TextInputEditText) rootView.findViewById(R.id.tie_valable_au);

        final Calendar c = Calendar.getInstance();
        year1 = c.get(Calendar.YEAR);
        month1 = c.get(Calendar.MONTH);
        day1 = c.get(Calendar.DAY_OF_MONTH);

        calPicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + 0 + month + "-" + year;
                        valable_du.setText(date);
                        year1 = year;
                        month1 = month - 1;
                        day1 = day;
                    }
                }, year1, month1, day1);

                datePickerDialog.show();
            }
        });

        calPicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + 0 + month + "-" + year;
                        valable_au.setText(date);
                        year1 = year;
                        month1 = month - 1;
                        day1 = day;
                    }
                }, year1, month1, day1);

                datePickerDialog.show();
            }
        });

        return rootView;
    }
}
