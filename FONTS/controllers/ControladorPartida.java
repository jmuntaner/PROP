package controllers;

import domain.*;
import utils.Pair;

import java.util.ArrayList;

public abstract class ControladorPartida {
    private final Problema problema;
    private final EstadistiquesPartida estadistiques;
    private final Partida partida;
    private ArrayList<Moviment> moviments;
    private boolean taules, limit;
    private Color colorPrincipal;
    private String nomA, nomB;

    /**
     * Crea una partida Humà vs Humà
     *
     * @param problema El problema en el que es basarà la partida.
     */
    ControladorPartida(Problema problema) {
        this.problema = problema;

        colorPrincipal = problema.getTema();
        partida = new Partida(problema);
        estadistiques = new EstadistiquesPartida();
        estadistiques.iniciaTorn(partida.getTorn());
        taules = false;
        limit = false;
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
     */
    void finalitzaPartida() {
        Ranking<PuntuacioProblema> ranking = problema.getRanking();
        PuntuacioProblema punts = new PuntuacioProblema(estadistiques, problema.getTema());
        actualitzaRanking(ranking, punts);
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
        actualitzaTorn();
        if (res <= 1 && partida.getNumMoviments() >= problema.getNumJugades()) {
            limit = true;
            return -1;
        }
        if (res == 3) taules = true;
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

    /**
     * Indica si la partida ha acabat per taules.
     *
     * @return Vertader si la partida ha acabat en taules.
     */
    public boolean esTaules() {
        return taules;
    }

    /**
     * Indica si la partida ha acabat per límit de temps.
     *
     * @return Vertader si la partida ha acabat per límit de temps.
     */
    public boolean esLimit() {
        return limit;
    }


    /**
     * Obté el nom del jugador al que toca moure.
     *
     * @return Nom del jugador del torn actual.
     */
    public String getNomTorn() {
        if (partida.getTorn() == colorPrincipal) return nomA;
        else return nomB;
    }

    /**
     * Obté el nom del jugador guanyador.
     *
     * @return Nom del jugador guanyador, "-" en cas de taules.
     */
    public String getNomGuanyador() {
        if (limit) return getNomTorn();
        if (taules) return "-";
        if (partida.getTorn() == colorPrincipal) return nomB;
        else return nomA;
    }

    /**
     * Modifica els noms dels jugadors.
     *
     * @param nomA Nom del jugador atacant.
     * @param nomB Nom del jugador defensor.
     */
    void setNoms(String nomA, String nomB) {
        this.nomA = nomA;
        this.nomB = nomB;
    }

    /**
     * Obté el tema del problema.
     *
     * @return Tema del poblema.
     */
    Color getColorPrincipal() {
        return colorPrincipal;
    }

    /**
     * Obté el tauler actual.
     *
     * @return Situació actual de la partida.
     */
    Tauler getTauler() {
        return partida.getSituacioActual();
    }

    /**
     * Actualitza la informació del ranking
     *
     * @param ranking Ranking a actualitzar.
     * @param punts   Puntuació a inserir.
     */
    abstract void actualitzaRanking(Ranking<PuntuacioProblema> ranking, PuntuacioProblema punts);

    /**
     * Indica si és el torn d'una màquina.
     * @return Vertader si és el torn d'una màquina.
     */
    public abstract boolean esTornMaquina();

    /**
     * Actualitza l'estat intern del controlador al finalitzar un torn.
     */
    abstract void actualitzaTorn();

    /**
     * Executa un moviment de màquina.
     */
    public abstract int executaMoviment();
}
