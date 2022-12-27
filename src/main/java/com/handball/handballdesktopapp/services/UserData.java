package com.handball.handballdesktopapp.services;

import com.handball.handballdesktopapp.DAO.resultats;
import javafx.collections.ObservableList;

import java.util.List;

public final class UserData {
    private final static UserData userData = new UserData();

    private UserData(){}

    public static UserData getUserData() {
        return userData;
    }
    private Integer session_id;
    private String nom_joueur;
    private String nom_session;


    private ObservableList<resultats> resultatsJoueur ;
    private ObservableList<resultats> resultatsJoueurList ;

    private String level;
    private  String date_session;

    public String getDate_session() {
        return date_session;
    }

    public void setDate_session(String date_session) {
        this.date_session = date_session;
    }

    public boolean isLevel5() {
        return isLevel5;
    }

    public void setLevel5(boolean level5) {
        isLevel5 = level5;
    }

    private boolean isLevel5;


    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    private float moyenne;
    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = session_id;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }


    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public ObservableList<resultats> getResultatsJoueur() {
        return resultatsJoueur;
    }

    public void setResultatsJoueur(ObservableList<resultats> resultatsJoueur) {
        this.resultatsJoueur = resultatsJoueur;
    }


    public ObservableList<resultats> getResultatsJoueurList() {
        return resultatsJoueurList;
    }

    public void setResultatsJoueurList(ObservableList<resultats> resultatsJoueurList) {
        this.resultatsJoueurList = resultatsJoueurList;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNom_session() {
        return nom_session;
    }

    public void setNom_session(String nom_session) {
        this.nom_session = nom_session;
    }
}
