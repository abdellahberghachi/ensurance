package com.hao.e_nsurance.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hao.e_nsurance.R;

public class ConstateurHomeActivity extends AppCompatActivity {
Button localiser,urgences;
    Intent callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constatateur_home);
        localiser=(Button)findViewById(R.id.localiser_accident);
        final android.support.v7.app.ActionBar abar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.action_bar, null);
        android.support.v7.app.ActionBar.LayoutParams params = new android.support.v7.app.ActionBar.LayoutParams(Gravity.CENTER);
        params.setMargins(0, 17, 0, 0);
        TextView titleText = (TextView) viewActionBar.findViewById(R.id.titleText);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Yananeska.ttf");
        titleText.setTypeface(typeface);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        localiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ConstateurActivity.class));

            }
        });
        urgences = (Button) findViewById(R.id.appeler_urgences);

        urgences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:150"));

                if (ContextCompat.checkSelfPermission(ConstateurHomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ConstateurHomeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(callIntent);
                }
            }
        });
    }
}
