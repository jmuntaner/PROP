package controllers;

import domain.*;

public class ControladorPartidaHvH extends ControladorPartida {
    private final Usuari usuari;
    private final String nomA, nomB;
    private final Color colorPrincipal;

    /**
     * Crea una partida Humà vs Humà
     *
     * @param problema El problema en el que es basarà la partida.
     * @param usuari   L'usuari que juga el problema (ataca).
     * @param oponent  Nom de l'oponent.
     */
    public ControladorPartidaHvH(Problema problema, Usuari usuari, String oponent) {
        super(problema);
        usuari = new Usuari(0, "Jugador 1", "Sauron");
        this.usuari = usuari;
        nomA = usuari.getNom();
        nomB = oponent;
        colorPrincipal = problema.getTema();
    }


    @Override
    void actualitzaRanking(Ranking<PuntuacioProblema> ranking, PuntuacioProblema punts) {
        ranking.afegeixPuntuacio(usuari, punts);
    }

    @Override
    public String getNomTorn() {
        if (getColorTorn() == colorPrincipal) return nomA;
        else return nomB;
    }

    @Override
    public boolean esTornMaquina() {
        return false;
    }
}
