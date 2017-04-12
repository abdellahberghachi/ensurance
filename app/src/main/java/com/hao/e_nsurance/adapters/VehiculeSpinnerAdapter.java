package com.hao.e_nsurance.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Vehicule;

import java.util.List;

/**
 * Created by abarg on 20/03/2017.
 */

public class VehiculeSpinnerAdapter extends ArrayAdapter<Vehicule> {

    Context mContext;
    List<Vehicule> itemsVehicule;
    LayoutInflater inflater;
    int resource;

    public VehiculeSpinnerAdapter(Context context, int resource, int id, List<Vehicule> items) {
        super(context, id, items);
        itemsVehicule = items;
        this.resource = resource;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final Vehicule dataModel = getItem(position);

        if (view == null) {
            view = inflater.inflate(resource, null);
        }


        TextView marque = (TextView) view.findViewById(R.id.marque_modele);
        TextView matricule = (TextView) view.findViewById(R.id.matricule);

        marque.setText(dataModel.getName() + " " + dataModel.getModele());
        matricule.setText(dataModel.getMarticule());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
