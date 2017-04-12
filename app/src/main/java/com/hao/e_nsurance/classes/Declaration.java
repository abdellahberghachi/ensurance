package com.hao.e_nsurance.classes;

/**
 * Created by user on 06/04/2017.
 */

public class Declaration {

    private int id_user;
    private double longi;
    private double lat;
    private String street;
    private String city;
    private String country;
    private int id_constateur;
    private String date;


    public Declaration(int id_user, double longi, double lat, String street, String city, String country, int id_constateur,String date) {
        this.id_user = id_user;
        this.longi = longi;
        this.lat = lat;
        this.street = street;
        this.city = city;
        this.country = country;
        this.id_constateur = id_constateur;
        this.date=date;

    }

    public Declaration() {
    }
// getter & setters

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId_constateur() {
        return id_constateur;
    }

    public void setId_constateur(int id_constateur) {
        this.id_constateur = id_constateur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
