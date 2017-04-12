package com.hao.e_nsurance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.fragments.VehiculeContratFragment;
import com.hao.e_nsurance.fragments.VehiculeInfosFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class AjoutVehiculeActivity extends AppCompatActivity {

    String[] res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutvehicule);

        res = getResources().getStringArray(R.array.typevehicule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Véhicule", VehiculeInfosFragment.class)
                .add("Contrat d'assurance", VehiculeContratFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profil_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.valider) {
            if (type.equals("ajouter")) {
                marque.setErrorEnabled(false);
                modele.setErrorEnabled(false);
                matricule.setErrorEnabled(false);
                contrat.setErrorEnabled(false);
                if (marque.getEditText().getText().toString().equals("")) {
                    marque.setErrorEnabled(false);
                    marque.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                }
                if (modele.getEditText().getText().toString().equals("")) {
                    modele.setErrorEnabled(false);
                    modele.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                }
                if (matricule.getEditText().getText().toString().equals("")) {
                    matricule.setErrorEnabled(false);
                    matricule.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                }
                if (contrat.getEditText().getText().toString().equals("")) {
                    contrat.setErrorEnabled(false);
                    contrat.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                }
                if (!marque.getEditText().getText().toString().equals("") && !modele.getEditText().getText().toString().equals("") && !matricule.getEditText().getText().toString().equals("") && !contrat.getEditText().getText().toString().equals("")) {
                    sp.getSelectedItem().toString();
                    if (sp.getSelectedItem().toString().equals(res[0])) {
                        type_Vehicule = 3;
                    }
                    if (sp.getSelectedItem().toString().equals(res[1])) {
                        type_Vehicule = 1;
                    }
                    if (sp.getSelectedItem().toString().equals(res[2])) {
                        type_Vehicule = 2;
                    }
                      String url = "http://www.mnarweb.com/ensurance/web/api/vehicule/add";
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("matricule", matricule.getEditText().getText().toString());
                    params.put("marque", marque.getEditText().getText().toString());
                    params.put("modele", modele.getEditText().getText().toString());
                    params.put("type_id", type_Vehicule);
                    params.put("num_carte_grise", contrat.getEditText().getText().toString());
                    params.put("user_id", settings.getString(PREF_ID, ""));
                    client.post(url, params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            try {
                                Vehicule vm = new Vehicule(response.getInt(TAG_ID),marque.getEditText().getText().toString(), modele.getEditText().getText().toString(), matricule.getEditText().getText().toString(), contrat.getEditText().getText().toString(), sp.getSelectedItem().toString());
                                Intent intent = new Intent();
                                intent.putExtra("vehicule", vm);
                                setResult(2, intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Toast.makeText(getApplicationContext(), "Erreur "+statusCode, Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            Toast.makeText(getApplicationContext(), "Erreur "+statusCode, Toast.LENGTH_LONG).show();

                        }


                    });
                }


            }

            if (type.equals("modifier")) {

                String url = "http://www.mnarweb.com/ensurance/web/api/vehicule/edit/";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("id", getIntent().getStringExtra("idVehicule"));
                params.put("matricule", matricule.getEditText().getText().toString());
                params.put("marque", marque.getEditText().getText().toString());
                params.put("modele", modele.getEditText().getText().toString());
                params.put("type_id", type_Vehicule);
                params.put("num_carte_grise", contrat.getEditText().getText().toString());
                params.put("user_id", settings.getString(PREF_ID, ""));
                client.put(url+getIntent().getStringExtra("idVehicule"), params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            Vehicule vm = new Vehicule(response.getInt(TAG_ID),marque.getEditText().getText().toString(), modele.getEditText().getText().toString(), matricule.getEditText().getText().toString(), contrat.getEditText().getText().toString(), sp.getSelectedItem().toString());
                            Intent intent = new Intent();
                            intent.putExtra("vehicule", vm);
                            intent.putExtra("pos", pos);
                            setResult(2, intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(getApplicationContext(), "Erreur "+statusCode, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(getApplicationContext(), "Erreur "+statusCode, Toast.LENGTH_LONG).show();

                    }
                });
            }
            }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
