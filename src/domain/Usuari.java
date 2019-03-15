package domain;

import java.util.ArrayList;

public class Usuari {
    private String nom, hashPassword;
    private int id;
    private ArrayList<Problema> problemesSuperats;
    private EstadistiquesJugador estadistiques;

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Problema> getProblemesSuperats() {
        return problemesSuperats;
    }
}
