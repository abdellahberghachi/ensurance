package com.hao.e_nsurance.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.hao.e_nsurance.R;

public class IntroApp extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        addSlide(AppIntroFragment.newInstance("Assistance-Dépannage ", "Votre voiture est immobilisée ? il vous suffit d'activer la géolocalisation de votre smartphone et de déclarer votre accident par un simple clic. Nous sommes là dans l'heure.", R.drawable.assiatance,Color.parseColor("#1f8aa7")));
        addSlide(AppIntroFragment.newInstance("Déclaration et suivi de sinistre ", "En cas de sinistre , gagnez du temps . Déclarez-le via l'appli et on vous envoie un constateur pour compléter votre déclaration", R.drawable.ic_constat,Color.parseColor("#1f8aa7")));
        addSlide(AppIntroFragment.newInstance("Service conseil constat amiable ", "Un accrochage ? Restez zen . Pour ne rien oublier dans votre constat amiable on vous aide a le remplir sur place ", R.drawable.intro1,Color.parseColor("#1f8aa7")));

        setBarColor(Color.parseColor("#1f8aa7"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(true);
        setProgressButtonEnabled(true);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(getApplicationContext(),AuthEnregActivity.class));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
       startActivity(new Intent(getApplicationContext(),AuthEnregActivity.class));
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
