package com.handball.handballdesktopapp.DAO;

public class joueurDetails {
    @Override
    public String toString() {
        return "joueurDetails{" +
                "session_id='" + session_id + '\'' +
                ", session_date='" + session_date + '\'' +
                ", nom_session='" + nom_session + '\'' +
                ", nom_joueur='" + nom_joueur + '\'' +
                ", level='" + level + '\'' +
                ", A25='" + A25 + '\'' +
                ", A35='" + A35 + '\'' +
                ", A24='" + A24 + '\'' +
                ", A34='" + A34 + '\'' +
                ", A2_5='" + A2_5 + '\'' +
                ", A3_5='" + A3_5 + '\'' +
                ", A2_4='" + A2_4 + '\'' +
                ", A3_4='" + A3_4 + '\'' +
                '}';
    }

    public joueurDetails(String session_id, String session_date, String nom_session, String nom_joueur, String level, String a25, String a35, String a24, String a34, String a2_5, String a3_5, String a2_4, String a3_4) {
        this.session_id = session_id;
        this.session_date = session_date;
        this.nom_session = nom_session;
        this.nom_joueur = nom_joueur;
        this.level = level;
        A25 = a25;
        A35 = a35;
        A24 = a24;
        A34 = a34;
        A2_5 = a2_5;
        A3_5 = a3_5;
        A2_4 = a2_4;
        A3_4 = a3_4;
    }

    private String session_id;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    private String session_date;
    private String nom_session;
    private String nom_joueur;
    private String level;
    private String A25;
    private String A35;

    public String getSession_date() {
        return session_date;
    }

    public void setSession_date(String session_date) {
        this.session_date = session_date;
    }

    public String getNom_session() {
        return nom_session;
    }

    public void setNom_session(String nom_session) {
        this.nom_session = nom_session;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getA25() {
        return A25;
    }

    public void setA25(String a25) {
        A25 = a25;
    }

    public String getA35() {
        return A35;
    }

    public void setA35(String a35) {
        A35 = a35;
    }

    public String getA24() {
        return A24;
    }

    public void setA24(String a24) {
        A24 = a24;
    }

    public String getA34() {
        return A34;
    }

    public void setA34(String a34) {
        A34 = a34;
    }

    public String getA2_5() {
        return A2_5;
    }

    public void setA2_5(String a2_5) {
        A2_5 = a2_5;
    }

    public String getA3_5() {
        return A3_5;
    }

    public void setA3_5(String a3_5) {
        A3_5 = a3_5;
    }

    public String getA2_4() {
        return A2_4;
    }

    public void setA2_4(String a2_4) {
        A2_4 = a2_4;
    }

    public String getA3_4() {
        return A3_4;
    }

    public void setA3_4(String a3_4) {
        A3_4 = a3_4;
    }

    private String A24;
    private String A34;
    private String A2_5;
    private String A3_5;
    private String A2_4;
    private String A3_4;

}
