package com.hao.e_nsurance.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.hao.e_nsurance.R;

public class AboutActivity extends AppCompatActivity {

    TextView version, email1, email2, email3, appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        version = (TextView) findViewById(R.id.version);
        try {
            version.setText(getString(R.string.version) + getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        email1 = (TextView) findViewById(R.id.email1);
        email2 = (TextView) findViewById(R.id.email2);
        email3 = (TextView) findViewById(R.id.email3);

        Linkify.addLinks(email1, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(email2, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(email3, Linkify.EMAIL_ADDRESSES);
        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email1.getText()));
                startActivity(intent);
            }
        });
        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email2.getText()));
                startActivity(intent);
            }
        });
        email3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email3.getText()));
                startActivity(intent);
            }
        });

        appName = (TextView) findViewById(R.id.app_name);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Yananeska.ttf");
        appName.setTypeface(typeface);
    }
}
