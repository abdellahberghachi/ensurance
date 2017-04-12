package com.hao.e_nsurance.services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;


import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.activities.MapsTracking;
import com.hao.e_nsurance.classes.Declaration;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;

/**
 * Created by abarg on 07/04/2017.
 */
//listener
public class ListenerDeclaration extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if(intent!=null){

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://ensurance-bb713.firebaseio.com/");
        DatabaseReference myRef = database.getReference("Declarations");
        myRef.child(intent.getStringExtra("id_declaration")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Declaration declaration = dataSnapshot.getValue(Declaration.class);

                if (declaration.getId_constateur() != 0) {
                    Intent intent = new Intent(ListenerDeclaration.this, MapsTracking.class);
                    intent.putExtra("id_constateur",declaration.getId_constateur());
                    PendingIntent pIntent = PendingIntent.getActivity(ListenerDeclaration.this, 0, intent, 0);
                    NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.marker, "Tracking", pIntent).build();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alert")
                            .setContentText("An advisor has just answered your request")
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                            .addAction(action).setPriority(Notification.PRIORITY_HIGH);
                    builder.setContentIntent(pIntent);
                    builder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(11, builder.build());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}
        return START_STICKY;

    }
}
