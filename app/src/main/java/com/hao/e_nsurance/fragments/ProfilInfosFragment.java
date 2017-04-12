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


public class ProfilInfosFragment extends Fragment {

    ImageView calPicker;
    TextInputEditText dateNaissance, email, lastName, firstName, tel;
    int year1, month1, day1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profil_infos, container, false);

        calPicker = (ImageView) rootView.findViewById(R.id.cal_picker);
        dateNaissance = (TextInputEditText) rootView.findViewById(R.id.date_naissance);

        final Calendar c = Calendar.getInstance();
        year1 = c.get(Calendar.YEAR);
        month1 = c.get(Calendar.MONTH);
        day1 = c.get(Calendar.DAY_OF_MONTH);

        calPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + 0 + month + "-" + year;
                        dateNaissance.setText(date);
                        year1 = year;
                        month1 = month - 1;
                        day1 = day;
                    }
                }, year1, month1, day1);

                datePickerDialog.show();
            }
        });

        email = (TextInputEditText) rootView.findViewById(R.id.tie_email);
        lastName = (TextInputEditText) rootView.findViewById(R.id.tie_nom);
        firstName = (TextInputEditText) rootView.findViewById(R.id.tie_prenom);
        tel = (TextInputEditText) rootView.findViewById(R.id.tie_tel);

        return rootView;
    }
}
