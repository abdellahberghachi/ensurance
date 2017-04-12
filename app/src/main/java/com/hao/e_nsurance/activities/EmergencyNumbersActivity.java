package com.hao.e_nsurance.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hao.e_nsurance.R;

public class EmergencyNumbersActivity extends AppCompatActivity {

    private Button police, ambulance, depannage, assurance, gendarmerie, pompier;
    Intent callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hao.e_nsurance.R.layout.activity_numberemergency);

        police = (Button) findViewById(R.id.police);
        ambulance = (Button) findViewById(R.id.ambulance);
        depannage = (Button) findViewById(R.id.Assistance);
        assurance = (Button) findViewById(R.id.Assurance);
        assurance = (Button) findViewById(R.id.Assurance);
        pompier = (Button) findViewById(R.id.pompiers);
        gendarmerie = (Button) findViewById(R.id.Gendarmerie);

        callIntent = new Intent(Intent.ACTION_CALL);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent.setData(Uri.parse("tel:190"));

                if (ContextCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyNumbersActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
        gendarmerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent.setData(Uri.parse("tel:177"));

                if (ContextCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyNumbersActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent.setData(Uri.parse("tel:150"));

                if (ContextCompat.checkSelfPermission(EmergencyNumbersActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyNumbersActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
        pompier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent.setData(Uri.parse("tel:150"));

                if (ContextCompat.checkSelfPermission(EmergencyNumbersActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyNumbersActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
        depannage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent.setData(Uri.parse("tel:0123456789"));

                if (ContextCompat.checkSelfPermission(EmergencyNumbersActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyNumbersActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
        assurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent.setData(Uri.parse("tel:0987654321"));

                if (ContextCompat.checkSelfPermission(EmergencyNumbersActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EmergencyNumbersActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startActivity(callIntent);

                } else {

                    Snackbar.make(getCurrentFocus(), R.string.permission_refuse, Snackbar.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
