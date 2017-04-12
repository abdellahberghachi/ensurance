package com.hao.e_nsurance.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.classes.Declaration;
import com.hao.e_nsurance.others.MapWrapperLayout;
import com.hao.e_nsurance.others.OnInfoWindowElemTouchListener;

import static java.lang.Integer.valueOf;

public class ConstateurActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double longi;
    double lat;
    String title;
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";
    private static final String PREF_ID = "Id";
    private ViewGroup infoWindow;
    private TextView infoTitle;
    private TextView infoSnippet;
    private Button infoButton;
    private OnInfoWindowElemTouchListener infoButtonListener;
    Polygon polygon;
    MapWrapperLayout mapWrapperLayout;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constateur);
        loadPreferences();
        database = FirebaseDatabase.getInstance("https://ensurance-bb713.firebaseio.com/");
        myRef = database.getReference("Declarations");
        mapWrapperLayout = (MapWrapperLayout) findViewById(R.id.map_relative_layout);
        this.infoWindow = (ViewGroup) getLayoutInflater().inflate(R.layout.info_window, null);
        this.infoTitle = (TextView) infoWindow.findViewById(R.id.title);
        this.infoSnippet = (TextView) infoWindow.findViewById(R.id.snippet);
        this.infoButton = (Button) infoWindow.findViewById(R.id.button);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.infoButtonListener = new OnInfoWindowElemTouchListener(infoButton,
                getResources().getDrawable(R.color.colorAccent), //btn_default_normal_holo_light
                getResources().getDrawable(R.color.colorPrimary)) //btn_default_pressed_holo_light
        {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                myRef.child(marker.getSnippet()).child("id_constateur").setValue(Integer.parseInt(settings.getString(PREF_ID, "0")));
                Toast.makeText(ConstateurActivity.this, marker.getSnippet(), Toast.LENGTH_SHORT).show();
            }
        };
        this.infoButton.setOnTouchListener(infoButtonListener);
        /*longi = getIntent().getExtras().getDouble("long");
        lat = getIntent().getExtras().getDouble("lat");
        title = getIntent().getExtras().getString("title");
        setTitle(title);*/

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMap.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Declaration declaration = snapshot.getValue(Declaration.class);
                    if (declaration.getId_constateur() == 0) {
                        LatLng pos = new LatLng(declaration.getLat(), declaration.getLongi());
                        mMap.addMarker(new MarkerOptions().position(pos).title("Accident").snippet(snapshot.getKey()));
                    }
                }

                // Instantiates a new Polygon object and adds points to define a rectangle
                PolygonOptions rectOptions = new PolygonOptions()
                        .add(new LatLng(33.58, -7.61),
                                new LatLng(33.59, -7.61),
                                new LatLng(33.59, -7.62),
                                new LatLng(33.58, -7.62),
                                new LatLng(33.58, -7.61));

                // Get back the mutable Polygon

                //rectOptions.fillColor(Color.parseColor(getResources().getString(R.color.orange)));
                rectOptions.fillColor(R.color.orange);
                rectOptions.strokeColor(Color.parseColor(getResources().getString(R.color.red)));
                polygon = mMap.addPolygon(rectOptions);

                mMap.setMaxZoomPreference(150);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(midPoint(polygon.getPoints().get(0).latitude, polygon.getPoints().get(0).longitude, polygon.getPoints().get(2).latitude, polygon.getPoints().get(2).longitude)));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapWrapperLayout.init(mMap, getPixelsFromDp(this, 39 + 20));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Setting up the infoWindow with current's marker info
                infoTitle.setText(marker.getTitle());
                infoSnippet.setText(marker.getSnippet());
                infoButtonListener.setMarker(marker);

                // We must call this to set the current marker and infoWindow references
                // to the MapWrapperLayout
                mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);
                return infoWindow;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.constateur_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.centrer) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(midPoint(polygon.getPoints().get(0).latitude, polygon.getPoints().get(0).longitude, polygon.getPoints().get(2).latitude, polygon.getPoints().get(2).longitude)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
        if (id == R.id.sign_out) {
            releasePreferences();
        }

        return super.onOptionsItemSelected(item);
    }

    public LatLng midPoint(double lat1, double lon1, double lat2, double lon2) {

        return new LatLng(((lat2 - lat1) / 2) + lat1, ((lon2 - lon1) / 2) + lon1);
    }

    private void releasePreferences() {

         settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        settings.edit().remove(PREF_UNAME).apply();
        settings.edit().remove(PREF_PASSWORD).apply();
        settings.edit().remove(PREF_ID).apply();
        startActivity(new Intent(getApplicationContext(), AuthEnregActivity.class));
        finish();
    }

    private void loadPreferences() {

         settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //emailprof.setText(settings.getString(PREF_UNAME, ""));

    }

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
