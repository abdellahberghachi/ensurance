package com.hao.e_nsurance.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Vehicule;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abarg on 20/03/2017.
 */

public class VehiculeAdapter extends ArrayAdapter<Vehicule> {
    private ArrayList<Vehicule> dataSet;
    Context mContext;
    List<Vehicule> itemsVehicule;
    String url;
    Vehicule vm;

    public VehiculeAdapter(Context context, int resource, List<Vehicule> items) {
        super(context, resource, items);
        itemsVehicule = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Vehicule dataModel = getItem(position);
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listviewmodel_vehicule, null);
        }

        TextView idVehicule = (TextView) v.findViewById(R.id.idVehicule);
        TextView marque = (TextView) v.findViewById(R.id.marque);
        TextView type = (TextView) v.findViewById(R.id.typeVehicule);
        TextView matricule = (TextView) v.findViewById(R.id.matricule);
        TextView numContrart = (TextView) v.findViewById(R.id.numContrart);
        TextView modele = (TextView) v.findViewById(R.id.modelee);
        TextView delete = (TextView) v.findViewById(R.id.deleteItem);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 vm = itemsVehicule.get(position);
                new AlertDialog.Builder(view.getRootView().getContext())
                        .setTitle(R.string.annuler_constat)
                        .setMessage(R.string.etes_vous_sur_supp_vehicule)
                        .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                url = "http://mnarweb.com/ensurance/web/api/vehicule/delete/";
                                AsyncHttpClient client = new AsyncHttpClient();
                                client.delete(url+vm.getId(), null, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                                        super.onSuccess(statusCode, headers, response);
                                        itemsVehicule.remove(position);
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                                        super.onFailure(statusCode, headers, responseString, throwable);
                                        Toast.makeText(getContext(), "Erreur", Toast.LENGTH_LONG).show();

                                    }

                                    @Override
                                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                        super.onFailure(statusCode, headers, throwable, errorResponse);
                                        Toast.makeText(getContext(), "Erreur"+statusCode, Toast.LENGTH_LONG).show();

                                    }

                                });
                            }

                        })
                        .setNegativeButton(R.string.reprendre, null)
                        .show();
            }
        });
        if (idVehicule != null) {
            idVehicule.setText(""+dataModel.getId());
        }
        if (marque != null) {
            marque.setText(dataModel.getName());
        }
        if (type != null) {
            type.setText(dataModel.getType());
        }
        if (matricule != null) {
            matricule.setText(dataModel.getMarticule());
        }

        if (numContrart != null) {
            numContrart.setText(dataModel.getContrat_number());
        }
        if (modele != null) {
            modele.setText(dataModel.getModele());
        }
        return v;
    }

}
