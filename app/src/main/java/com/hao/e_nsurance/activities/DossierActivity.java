package com.hao.e_nsurance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.adapters.DossierAdapter;
import com.hao.e_nsurance.classes.Dossier;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DossierActivity extends AppCompatActivity {

    ListView dossierLv;
    List<Dossier> list;
    DossierAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dossier);

        list = new ArrayList<>();

        try {
            list.add(new Dossier(0, "15-01-2010", "Traité"));
            list.add(new Dossier(1, "30-07-2016", "En cours"));
            list.add(new Dossier(2, "02-05-2013", "Rejeté"));
            list.add(new Dossier(3, "20-10-2016", "En cours"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dossierLv = (ListView) findViewById(R.id.dossier_lv);
        adapter = new DossierAdapter(this, R.layout.listview_dossier, list);
        dossierLv.setAdapter(adapter);

        dossierLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), DossierDetailActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dossier_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.par_date) {

            Collections.sort(list, new Comparator<Dossier>() {
                @Override
                public int compare(Dossier d1, Dossier d2) {
                    return d1.getDate_ouverture().compareTo(d2.getDate_ouverture());
                }
            });
            adapter.notifyDataSetChanged();

            return true;
        }
        if (id == R.id.par_statut) {

            Collections.sort(list, new Comparator<Dossier>() {
                @Override
                public int compare(Dossier d1, Dossier d2) {
                    return d1.getStatut().compareTo(d2.getStatut());
                }
            });
            adapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
