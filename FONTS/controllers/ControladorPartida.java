package controllers;

import domain.*;
import javafx.util.Pair;

import java.util.ArrayList;

public class ControladorPartida {
    private final Problema problema;
    private final Usuari usuari;
    private final String nomOponent;
    private final EstadistiquesPartida estadistiques;
    private Partida partida;

    /**
     * Crea una partida Humà vs Humà
     *
     * @param problema El problema en el que es basarà la partida.
     * @param usuari   L'usuari que juga el problema (ataca).
     * @param oponent  Nom de l'oponent.
     */
    public ControladorPartida(Problema problema, Usuari usuari, String oponent) {
        this.problema = problema;
        this.usuari = usuari;
        nomOponent = oponent;
        partida = new Partida(problema);
        estadistiques = new EstadistiquesPartida();
        estadistiques.iniciaTorn(partida.getTorn());
    }

    /**
     * Retorna la llista de posicions a les que es pot moure una peça.
     *
     * @param x Posició x de la peça.
     * @param y Posició y de la peça.
     * @return Llista amb les posicions a les quals es pot moure la peça de x,y.
     */
    ArrayList<Pair<Integer, Integer>> movimentsPossibles(int x, int y) {
        ArrayList<Pair<Integer, Integer>> possibles = new ArrayList<>();
        ArrayList<Moviment> moviments = partida.obteMovimentsPosicio(x, y);
        if (moviments != null) {
            for (Moviment mov : moviments) {
                possibles.add(mov.getPosFinal());
            }
        }
        return possibles;
    }

    /**
     * Mou una peça des de (xo, yo) fins (xf, yf) si es pot.
     *
     * @param xo Posició x de la peça.
     * @param yo Posició y de la peça.
     * @param xf Posició x a on moure.
     * @param yf Posició y a on moure.
     */
    void mou(int xo, int yo, int xf, int yf) {
        estadistiques.finalitzaTorn(partida.getTorn());
        //TODO: Crear moviment i moure la Peça
    }

    /**
     * Finalitza la partida actual, actualitzant els rankings corresponents.
     *
     * @return Estadístiques de la partida.
     */
    EstadistiquesPartida finalitzaPartida() {
        Ranking<PuntuacioProblema> ranking = problema.getRanking();
        PuntuacioProblema punts = new PuntuacioProblema(estadistiques, problema.getTema());
        ranking.afegeixPuntuacio(usuari, punts);
        return estadistiques;
    }
}
