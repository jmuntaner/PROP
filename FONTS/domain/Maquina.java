package domain;

import java.util.ArrayList;
import java.util.Random;

public abstract class Maquina {
    private static final int maxVal = 9999;
    private static final int minVal = -9999;

    public abstract int heuristica(Tauler posicio, Color torn);

    private int valor_peca(char p) {
        int v = 0;
        switch (p) {
            case 'p': v=-10; break;
            case 'P': v=10; break;
            case 'r': v=-50; break;
            case 'R': v=50; break;
            case 'b': v=-30; break;
            case 'B': v=30; break;
            case 'n': v=-30; break;
            case 'N': v=30; break;
            case 'q': v=90; break;
            case 'Q': v=90; break;
            case 'k': v=-9000; break;
            case 'K': v=9000; break;
        }
        return v;
    }

    /**
     * Cerca el millor moviment per a la maquina
     *
     * @param profunditat profunditat maxima d'exploracio de l'arbre
     * @param t Situacio actual del tauler
     * @param torn Color que ha de moure
     * @param tema Color que mou primer
     * @return Moviment que realitza la màquina (null si no n'hi ha cap possible)
     */
    public Moviment calcularMoviment(int profunditat, Tauler t, Color torn, Color tema) {
        //Color torn = partida.getTorn();
        //Tauler t = partida.getSituacioActual();
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        if (movPos.isEmpty()) return null;
        Moviment mov = null;
        int bestMove;
        int codi;
        int ret;
        //if (torn == Color.BLANC) {
        if (torn == tema) {
            bestMove = minVal;
            for (Moviment m : movPos) {
                codi = t.mou(m);
                ret = minimax(t, profunditat-1, false, codi, torn.getNext());
                if (ret > bestMove) {
                    mov = m;
                    bestMove = ret;
                }
                else if (ret == bestMove && new Random().nextBoolean()) mov = m; //Si son iguals, tria aleatoriament
                t.mouInvers(m);
            }
        }
        //else if (torn == Color.NEGRE) {
        else {
            bestMove = maxVal;
            for (Moviment m : movPos) {
                codi = t.mou(m);
                ret = minimax(t, profunditat - 1, true, codi, torn.getNext());
                if (ret < bestMove) {
                    mov = m;
                    bestMove = ret;
                }
                else if (ret == bestMove && new Random().nextBoolean()) mov = m;
                t.mouInvers(m);
            }
        }
        //return this.partida.moure(torn, mov);
        return mov;
    }

    /**
     * Calcula el minimax de cada posicio
     *
     * @param profunditat profunditat maxima d'exploracio de l'arbre
     * @param esJugadorMaximal Indica si el jugador que ha de moure es l'atacant (true si ho es)
     * @param codi Codi retornat al fer l'ultim moviment
     * @param torn Color del jugador que ha de moure
     * @return Valor de la branca explorada
     */
    private int minimax(Tauler t, int profunditat, boolean esJugadorMaximal, int codi, Color torn) {
        if (codi == 2) { //mat retornem millor resultat (mat de l'ultim jugador que ha mogut!!)
            if (!esJugadorMaximal) return maxVal;
            else return minVal;
        }
        else if (codi == 3) { //taules mal resultat: sabem que podem fer mat
            if (!esJugadorMaximal) return minVal;
            else return maxVal;
        }
        else if (profunditat == 0) {
            return 0; //provisional
        }
        //ArrayList<Moviment> movPos = t.obteMovimentsJugador(this.partida.getTorn());
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        int bestMove;
        int c;
        if (esJugadorMaximal) {
            bestMove = minVal;
            for (Moviment m : movPos) {
                c = t.mou(m);
                bestMove = Math.max(minimax(t, profunditat - 1, false, c, torn.getNext()), bestMove);
                t.mouInvers(m);
            }
        } else {
            bestMove = maxVal;
            for (Moviment m : movPos) {
                c = t.mou(m);
                bestMove = Math.min(minimax(t, profunditat - 1, true, c, torn.getNext()), bestMove);
                t.mouInvers(m);
            }
        }
        return bestMove;
    }
}
