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

public class FactureFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_facture, container, false);


        return rootView;
    }
}
