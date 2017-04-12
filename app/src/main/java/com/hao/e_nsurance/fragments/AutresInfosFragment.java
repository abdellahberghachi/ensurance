package com.hao.e_nsurance.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.adapters.VehiculeSpinnerAdapter;
import com.hao.e_nsurance.classes.Vehicule;

import java.util.ArrayList;
import java.util.List;

public class AutresInfosFragment extends Fragment {

    Spinner vehiculeSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_autres_infos, container, false);

        List<Vehicule> dataModels = new ArrayList<>();
        dataModels.add(new Vehicule(1,"BMW", "A5", "123-C-5", "1522365555", "Car"));
        dataModels.add(new Vehicule(2,"DACIA", "Lodgy", "126-b-5", "855555322", "Car"));
        dataModels.add(new Vehicule(3,"LAMBORGHINI", "Aventador", "125-b-5", "855555322", "Car"));

        VehiculeSpinnerAdapter adapter = new VehiculeSpinnerAdapter(getActivity(), R.layout.vehicule_spinner, R.id.marque_modele, dataModels);
        vehiculeSpinner = (Spinner) rootView.findViewById(R.id.vehicule_spinner);
        vehiculeSpinner.setAdapter(adapter);

        return rootView;
    }
}
