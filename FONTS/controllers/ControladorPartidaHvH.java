package controllers;

import data.GestioUsuari;
import domain.Problema;
import domain.Usuari;

public class ControladorPartidaHvH extends ControladorPartida {
    private final Usuari usuari;


    /**
     * Crea una partida Humà vs Humà
     *
     * @param problema El problema en el que es basarà la partida.
     * @param usuari   L'usuari que juga el problema (ataca).
     * @param oponent  Nom de l'oponent.
     */
    public ControladorPartidaHvH(Problema problema, Usuari usuari, String oponent) {
        super(problema);
        this.usuari = usuari;
        if(!usuari.getNom().equals("guest")) {
            getUsuari().getStatistics().iniciaPartida();
            GestioUsuari gu = GestioUsuari.getInstance();
            gu.delete(getUsuari().getNom());
            gu.saveUsuari(getUsuari());
        }
        setNoms(usuari.getNom(), oponent);
    }

    @Override
    public boolean esTornMaquina() {
        return false;
    }

    @Override
    void actualitzaTorn() {
        // No cal fer res
    }

    @Override
    public int executaMoviment() {
        throw new RuntimeException("Un jugador humà no pot generar moviments.");
    }

    @Override
    public boolean esJugadorHuma(String nom) {
        return true;
    }

    @Override
    Usuari getUsuari() {
        return usuari;
    }
}
