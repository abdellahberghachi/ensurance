package com.hao.e_nsurance.classes;

import java.io.Serializable;

/**
 * Created by abarg on 20/03/2017.
 */

public class Vehicule implements Serializable {

    private String name;
    private String type;
    private String contrart_number;
    private String modele;
    private String marticule;
    private int id;


    public Vehicule(int id,String marque, String modele, String matricule, String contrat, String type) {

        this.id = id;
        this.name = marque;
        this.type = type;
        this.contrart_number = contrat;
        this.modele = modele;
        this.modele = modele;
        this.marticule = matricule;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMarticule() {
        return marticule;
    }

    public String getContrat_number() {
        return contrart_number;
    }

    public String getModele() {
        return modele;
    }
}
