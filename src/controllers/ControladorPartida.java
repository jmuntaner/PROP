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
    ControladorPartida(Problema problema, Usuari usuari, String oponent) {
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

    /**
     * Mou el millor per a la maquina en qüestió
     *
     * @param profunditat profunditat maxima d'exploracio de l'arbre
     */
    void calcularMoviment(int profunditat) {
        Color torn = partida.getTorn();
        Tauler t = partida.getSituacioActual();
        Moviment mov = null;
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        int bestMove;
        int codi;
        int ret;
        if (torn == Color.BLANC) {
            bestMove = -9999;
            for (Moviment m : movPos) {
                codi = t.mou(m);
                ret = minimax(t, profunditat, false, codi);
                if (ret > bestMove) {
                    mov = m;
                    bestMove = ret;
                }
                t.mouInvers(m);
            }
        } else if (torn == Color.NEGRE) {
            bestMove = 9999;
            for (Moviment m : movPos) {
                codi = t.mou(m);
                ret = minimax(t, profunditat - 1, false, codi);
                if (ret < bestMove) {
                    mov = m;
                    bestMove = ret;
                }
                t.mouInvers(m);
            }
        }
        this.partida.moure(torn, mov);
    }

    /**
     * Calcula el minimax de cada posicio
     *
     * @param profunditat profunditat maxima d'exploracio de l'arbre
     * @param esJugadorMaximal Indica si es el jugador maximal (en aquest cas les blanques)
     * @param codi Codi retornat al fer l'ultim moviment
     * @return Valor de la branca explorada
     */
    private int minimax(Tauler t, int profunditat, boolean esJugadorMaximal, int codi) {
        //mat retornem millor resultat
        if (codi == 2) {
            if (esJugadorMaximal) return 9999;
            else return -9999;
        }
        //escacs mal resultat: sabem que podem fer mat
        if (codi == 3) {
            if (esJugadorMaximal) return -9999;
            else return 9999;
        }
        if (profunditat == 0) {
            return 0; //provisional
        }
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(this.partida.getTorn());
        int bestMove;
        int c;
        if (esJugadorMaximal) {
            bestMove = -9999;
            for (Moviment m : movPos) {
                c = t.mou(m);
                bestMove = Math.max(minimax(t, profunditat - 1, false, c), bestMove);
                t.mouInvers(m);
            }
        } else {
            bestMove = 9999;
            for (Moviment m : movPos) {
                c = t.mou(m);
                bestMove = Math.min(minimax(t, profunditat - 1, true, c), bestMove);
                t.mouInvers(m);
            }
        }
        return bestMove;
    }
}
