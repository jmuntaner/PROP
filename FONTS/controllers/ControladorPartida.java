package controllers;

import domain.*;
import utils.Pair;

import java.util.ArrayList;

public abstract class ControladorPartida {
    private final Problema problema;
    private final EstadistiquesPartida estadistiques;
    private final Partida partida;
    private ArrayList<Moviment> moviments;


    /**
     * Crea una partida Humà vs Humà
     *
     * @param problema El problema en el que es basarà la partida.
     */
    ControladorPartida(Problema problema) {
        this.problema = problema;

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
    public ArrayList<Pair<Integer, Integer>> movimentsPossibles(int x, int y) {
        ArrayList<Pair<Integer, Integer>> possibles = new ArrayList<>();
        moviments = partida.obteMovimentsPosicio(x, y);
        if (moviments != null) {
            for (Moviment mov : moviments) {
                possibles.add(mov.getPosFinal());
            }
        }
        return possibles;
    }


    /**
     * Finalitza la partida actual, actualitzant els rankings corresponents.
     *
     * @return Estadístiques de la partida.
     */
    EstadistiquesPartida finalitzaPartida() {
        Ranking<PuntuacioProblema> ranking = problema.getRanking();
        PuntuacioProblema punts = new PuntuacioProblema(estadistiques, problema.getTema());
        actualitzaRanking(ranking, punts);
        return estadistiques;
    }

    /**
     * Indica el torn actual
     *
     * @return Color que mou en el torn actual;
     */
    Color getColorTorn() {
        return partida.getTorn();
    }

    /**
     * Indica el torn actual.
     *
     * @return Vertader si és el torn de les blanques.
     */
    public boolean getTorn() {
        return partida.getTorn() == Color.BLANC;
    }

    /**
     * Executa el moviment indicat
     *
     * @param index Índex del moviment a la llista generada anteriorment.
     */
    public int mou(int index) {
        Moviment m = moviments.get(index);
        return mou(m);
    }

    /**
     * Executa el moviment indicat
     *
     * @param m Moviment a executar.
     */
    int mou(Moviment m) {
        Color torn = partida.getTorn();
        int res = partida.moure(partida.getTorn(), m);
        estadistiques.finalitzaTorn(torn);
        estadistiques.iniciaTorn(partida.getTorn());
        if (partida.getNumMoviments() >= problema.getNumJugades()) return -1;
        return res;
    }

    /**
     * Obté la representació de la peça de la posició indicada
     *
     * @param x Posició X de la peça
     * @param y Posició Y de la peça
     * @return Caràcter que representa a la peça situada a (x,y)
     */
    public char getPos(int x, int y) {
        return partida.getAtPosicio(x, y);
    }

    abstract void actualitzaRanking(Ranking<PuntuacioProblema> ranking, PuntuacioProblema punts);

    public abstract String getNomTorn();

    public abstract boolean esTornMaquina();
}
