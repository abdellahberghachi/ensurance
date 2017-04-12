package com.hao.e_nsurance.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Vehicule;

import java.util.ArrayList;
import java.util.Calendar;


public class VehiculeInfosFragment extends Fragment {

    TextInputLayout matricule, marque, contrat, modele;
    String type, typeV;
    ArrayList<Vehicule> dataModels;
    Spinner sp;
    int pos, type_Vehicule;
    String[] res;
    private static final String PREFS_NAME = "preferences";
    SharedPreferences settings;
    private static final String PREF_ID = "Id";
    private static final String TAG_ID = "id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_vehicule_infos, container, false);

        contrat = (TextInputLayout) rootView.findViewById(R.id.num_contrat);
        marque = (TextInputLayout) rootView.findViewById(R.id.marque);
        matricule = (TextInputLayout) rootView.findViewById(R.id.matricule);
        modele = (TextInputLayout) rootView.findViewById(R.id.modele);
        sp = (Spinner) rootView.findViewById(R.id.spinner);

        settings = getActivity().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        Intent i = getActivity().getIntent();
        type = i.getStringExtra("type");
        if (type.equals("modifier")) {
            typeV = i.getStringExtra("typeV");
            pos = i.getIntExtra("pos", 0);
            contrat.getEditText().setText(i.getStringExtra("contrat"));
            marque.getEditText().setText(i.getStringExtra("marque"));
            matricule.getEditText().setText(i.getStringExtra("matricule"));
            modele.getEditText().setText(i.getStringExtra("modele"));


            if (typeV.equals(res[0])) {
                sp.setSelection(0);
            }
            if (typeV.equals(res[1])) {
                sp.setSelection(1);
            }
            if (typeV.equals(res[2])) {
                sp.setSelection(2);
            }
        }

        return rootView;
    }
}
