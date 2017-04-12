package com.hao.e_nsurance.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.services.ServiceLocation;

public class MapsTracking extends FragmentActivity implements OnMapReadyCallback {
    Button start, stop;
    GeoFire geoFire;

    Intent intent;
    private GoogleMap mMap;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tracking);
        CancelNotification(this,11);
         i = getIntent();

        if(i!=null){trackingAgent(""+i.getIntExtra("id_constateur",0));

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(MapsTracking.this, ServiceLocation.class);
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MapsTracking.this, ServiceLocation.class));

            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLngg = new LatLng(i.getDoubleExtra("lati",0),i.getDoubleExtra("longi",0));
        MarkerOptions markerpos = new MarkerOptions();
        markerpos.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        markerpos.position(latLngg);
        markerpos.title(getResources().getString(R.string.votre_position));
        markerpos.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngg));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        mMap.addMarker(markerpos);


    }

    public void trackingAgent(String id){


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://ensurance-bb713.firebaseio.com/");
        DatabaseReference myRef = database.getReference("Users").child(id);
        geoFire = new GeoFire(myRef);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geoFire.getLocation("", new LocationCallback() {
                    @Override
                    public void onLocationResult(String key, GeoLocation location) {
                        if (location != null) {
                            //mMap.clear();
                            LatLng latLng = new LatLng(location.latitude, location.longitude);
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                            markerOptions.position(latLng);
                            markerOptions.title("Constateur");
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                            mMap.addMarker(markerOptions);

                        } else {
                            System.out.println(String.format("There is no location for key %s in GeoFire", key));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.err.println("There was an error getting the GeoFire location: " + databaseError);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void CancelNotification(Context ctx, int notifyId) {
        String  s = Context.NOTIFICATION_SERVICE;
        NotificationManager mNM = (NotificationManager) ctx.getSystemService(s);
        mNM.cancel(notifyId);
    }

}
