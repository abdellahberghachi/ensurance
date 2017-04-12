package com.hao.e_nsurance.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.adapters.TemoinAdapter;
import com.hao.e_nsurance.classes.Temoin;

import java.util.ArrayList;
import java.util.List;

public class ObservationsFragment extends Fragment {

    FloatingActionButton fab;
    TemoinAdapter temoinAdapter;
    List<Temoin> temoinList;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_observations, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        temoinList = new ArrayList<>();
        temoinList.add(new Temoin(0, "OL", "Heitem", "0123456789", "h@h.h", "Maroc"));
        temoinList.add(new Temoin(0, "BER", "Abdellah", "0987654321", "a@a.a", "Maroc"));
        temoinList.add(new Temoin(0, "LMO", "Omar", "0123987456", "o@o.o", "Maroc"));

        temoinAdapter = new TemoinAdapter(getContext(), R.layout.listview_temoin, temoinList, new TemoinAdapter.OnButtonClickListener() {
            @Override
            public void onModify(int position) {
                showChangeLangDialog(position);
            }

            @Override
            public void onDelete(int position) {
                //temoinAdapter.remove(temoinAdapter.getItem(position));
                temoinList.remove(position);
                temoinAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(temoinAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });

        temoinAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if(temoinAdapter.getCount() >= 4) fab.setVisibility(View.GONE);
                else fab.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.ajouter_temoin, null);
        dialogBuilder.setView(dialogView);

        final EditText nom = (EditText) dialogView.findViewById(R.id.nom);
        final EditText prenom = (EditText) dialogView.findViewById(R.id.prenom);
        final EditText tel = (EditText) dialogView.findViewById(R.id.tel);
        final EditText email = (EditText) dialogView.findViewById(R.id.email);
        final EditText adresse = (EditText) dialogView.findViewById(R.id.adresse);
        dialogBuilder.setTitle(R.string.temoin);
        dialogBuilder.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                temoinList.add(temoinList.size()
                        , new Temoin(temoinList.size() + 1, nom.getText().toString(), prenom.getText().toString(), tel.getText().toString(), email.getText().toString(), adresse.getText().toString()));
                temoinAdapter.notifyDataSetChanged();
            }
        });
        dialogBuilder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void showChangeLangDialog(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.ajouter_temoin, null);
        dialogBuilder.setView(dialogView);

        final EditText nom = (EditText) dialogView.findViewById(R.id.nom);
        final EditText prenom = (EditText) dialogView.findViewById(R.id.prenom);
        final EditText tel = (EditText) dialogView.findViewById(R.id.tel);
        final EditText email = (EditText) dialogView.findViewById(R.id.email);
        final EditText adresse = (EditText) dialogView.findViewById(R.id.adresse);
        nom.setText(temoinAdapter.getItem(position).getNom());
        prenom.setText(temoinAdapter.getItem(position).getPrenom());
        tel.setText(temoinAdapter.getItem(position).getTel());
        email.setText(temoinAdapter.getItem(position).getEmail());
        adresse.setText(temoinAdapter.getItem(position).getAdresse());
        dialogBuilder.setTitle(R.string.temoin);
        dialogBuilder.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                temoinList.get(position).setNom(nom.getText().toString());
                temoinList.get(position).setPrenom(prenom.getText().toString());
                temoinList.get(position).setTel(tel.getText().toString());
                temoinList.get(position).setEmail(email.getText().toString());
                temoinList.get(position).setAdresse(adresse.getText().toString());
                temoinAdapter.notifyDataSetChanged();
            }
        });
        dialogBuilder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
