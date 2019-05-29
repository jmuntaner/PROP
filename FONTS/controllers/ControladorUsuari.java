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
     * Comprova si existeix un usuari
     *
     * @param nom Identificador de l'usuari a buscar.
     * @return True si existeix l'usuari, false si no.
     */
    public boolean existeixUsuari(String nom) {
        for (String s : gu.getList()) if (s.equals(nom)) return true;
        return false;
    }

    /**
     * Comprova si l'usuari amb el nom donat te el password proporcionat
     *
     * @param nom Identificador de l'usuari a comprovar.
     * @param pass Contrassenya a comprovar
     * @return True si existeix l'usuari i el password es correcte, false altrament
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
