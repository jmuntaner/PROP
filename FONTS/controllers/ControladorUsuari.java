package controllers;

import data.GestioUsuari;
import domain.*;

public class ControladorUsuari {
    private Usuari u;
    private static Usuari guest = new Usuari( "guest", "guest");
    private GestioUsuari gu;
    private ControladorPrincipal cp;

    public ControladorUsuari(ControladorPrincipal cp) {
        this.cp = cp;
        gu = GestioUsuari.getInstance();
        u  = guest;
    }

    public static Usuari getGuest() {return guest;}

    public EstadistiquesJugador getStatistics() {
        return u.getStatistics();
    }

    public String getNom() {
        return u.getNom();
    }

    /**
     * Retorna true si existeix l'usuari amb nom nom i fals altrament.
     *
     * @param nom Identificador de l'usuari a buscar.
     */
    public boolean existeixUsuari(String nom) {
        for (String s : gu.getList()) if (s.equals(nom)) return true;
        return false;
    }

    /**
     * Retorna true si existeix l'usuari amb nom nom i fals altrament.
     *
     * @param nom Identificador de l'usuari a comprovar.
     * @param pass Contrassenya a comprovar
     */
    public boolean authenticate(String nom, String pass) {
        return gu.getUsuari(nom).checkPass(pass);
    }

    /**
     * Selecciona un usuari com a l'actual
     *
     * @param nom Identificador de l'usuari a seleccionar.
     */
    public void selectUsuari(String nom) {
        u = gu.getUsuari(nom);
        cp.setUsuari(u);
    }

    /**
     * Registra un nou usuari
     *
     * @param nom Nom del nou usuari
     * @param pass Contrassenya del nou usuari
     */
    public void register (String nom, String pass) {
        u = new Usuari(nom, pass);
        gu.saveUsuari(u);
        cp.setUsuari(u);
    }

    /**
     * Logout: es passa a l'usuari guest com actiu
     *
     */
    public void logout() {
        if(!u.getNom().equals("guest")) {
            gu.delete(u.getNom());
            gu.saveUsuari(u);
        }
        u = guest;
        cp.setUsuari(guest);
    }






}
