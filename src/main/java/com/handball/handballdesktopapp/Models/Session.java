package com.handball.handballdesktopapp.Models;

public class Session {
    private int id;
    private String nom;
    private String date;



    public Session(int id, String nom, String date) {
        this.id = id;
        this.nom = nom;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
