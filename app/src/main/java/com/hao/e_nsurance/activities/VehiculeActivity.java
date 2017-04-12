package com.hao.e_nsurance.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hao.e_nsurance.R;
import com.hao.e_nsurance.adapters.VehiculeAdapter;
import com.hao.e_nsurance.classes.Vehicule;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VehiculeActivity extends AppCompatActivity {

    ArrayList<Vehicule> dataModels;
    GridView listView;
    VehiculeAdapter adapter;
    SharedPreferences settings;
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_ID = "Id";
    String url;
    RotateLoading progress;
    String type ;
    String[] res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (GridView) findViewById(R.id.vehicule_list);
        progress = (RotateLoading) findViewById(R.id.rotateloading);
        res = getResources().getStringArray(R.array.typevehicule);
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        dataModels = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         url = "http://mnarweb.com/ensurance/web/api/vehicules?user_id=";
        AsyncHttpClient client = new AsyncHttpClient();
        progress.start();

        client.get(url+settings.getString(PREF_ID, "0"), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject e = response.getJSONObject(i);
                        if ( e.getString("typeId").equals("1")) {
                            type = res[1];
                        }
                        if ( e.getString("typeId").equals("2")) {
                            type = res[2];
                        }
                        if ( e.getString("typeId").equals("3")) {
                            type = res[0];
                        }
                        dataModels.add(new Vehicule(e.getInt("id"),e.getString("marque"), e.getString("modele"), e.getString("matricule"), e.getString("numCarteGrise"), type));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new VehiculeAdapter(getApplicationContext(), R.layout.listviewmodel_vehicule, dataModels);
                progress.stop();
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_LONG).show();
                progress.stop();
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(), "Erreur"+statusCode, Toast.LENGTH_LONG).show();
                progress.stop();
            }


        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in = new Intent(getApplicationContext(), AjoutVehiculeActivity.class);
                in.putExtra("type", "modifier");
                in.putExtra("pos", i);
                in.putExtra("idVehicule", ((TextView) (view.findViewById(R.id.idVehicule))).getText().toString());
                in.putExtra("matricule", ((TextView) (view.findViewById(R.id.matricule))).getText().toString());
                in.putExtra("typeV", ((TextView) (view.findViewById(R.id.typeVehicule))).getText().toString());
                in.putExtra("marque", ((TextView) (view.findViewById(R.id.marque))).getText().toString());
                in.putExtra("contrat", ((TextView) (view.findViewById(R.id.numContrart))).getText().toString());
                in.putExtra("modele", ((TextView) (view.findViewById(R.id.modelee))).getText().toString());
                startActivityForResult(in, 1);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), AjoutVehiculeActivity.class);
                in.putExtra("type", "ajouter");
                startActivityForResult(in, 2);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            int position = data.getIntExtra("pos", 0);

            Vehicule vm = (Vehicule) data.getSerializableExtra("vehicule");
            dataModels.set(position, vm);
            adapter.notifyDataSetChanged();
        }
        if (requestCode == 2 && data != null) {
            Vehicule vm = (Vehicule) data.getSerializableExtra("vehicule");
            dataModels.add(vm);
            adapter.notifyDataSetChanged();
        }
    }
}
