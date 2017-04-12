package com.hao.e_nsurance.classes;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 29/03/2017.
 */

public class Dossier {

    private int num_dossier;
    private Date date_ouverture;
    private Date date_fermeture;
    private String statut;

    public int getNum_dossier() {
        return num_dossier;
    }

    public void setNum_dossier(int num_dossier) {
        this.num_dossier = num_dossier;
    }

    public Date getDate_ouverture() {
        return date_ouverture;
    }

    public void setDate_ouverture(Date date_ouverture) {
        this.date_ouverture = date_ouverture;
    }

    public Date getDate_fermeture() {
        return date_fermeture;
    }

    public void setDate_fermeture(Date date_fermeture) {
        this.date_fermeture = date_fermeture;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Dossier(int num_dossier, String date_ouverture, String statut) throws ParseException {
        this.num_dossier = num_dossier;
        this.date_ouverture = stringToDate(date_ouverture);
        this.statut = statut;
        Log.d("Date", this.date_ouverture.toString());
    }

    public Date stringToDate(String dateS) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = format.parse(dateS);
        return date;
    }
}
