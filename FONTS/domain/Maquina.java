package domain;

import java.util.ArrayList;
import java.util.Random;

public abstract class Maquina {
    private static final int maxVal = 9999;
    private static final int minVal = -9999;

    public abstract int heuristica(Tauler posicio, boolean esJugadorMaximal, int codi, Color torn);

    public static int getMaxVal() {
        return maxVal;
    }

    public static int getMinVal() {
        return minVal;
    }

    /**
     * Cerca el millor moviment per a la maquina
     *
     * @param profunditat profunditat maxima d'exploracio de l'arbre
     * @param t           Situacio actual del tauler
     * @param torn        Color que ha de moure
     * @param tema        Color que mou primer
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
        bestMove = torn == tema ? minVal : maxVal;
        for (Moviment m : movPos) { //primera "iteracio" del minimax
            codi = t.mou(m);
            ret = minimax(t, profunditat - 1, minVal, maxVal, false, codi, torn.getNext());
            if (ret == bestMove && new Random().nextBoolean()) mov = m; //Si son iguals, tria aleatoriament
            else if ((torn == tema && ret > bestMove) || (torn != tema && ret < bestMove)) {
                mov = m;
                bestMove = ret;
            }
            t.mouInvers(m);
        }
        /*
        (* Initial call *)
        alphabeta(origin, depth, −∞, +∞, TRUE)
         */
        return mov;
    }

    //https://medium.freecodecamp.org/simple-chess-ai-step-by-step-1d55a9266977
    //https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning

    /**
     * Calcula el minimax de cada posicio
     *
     * @param profunditat      profunditat maxima d'exploracio de l'arbre
     * @param alfa             Valor minim
     * @param beta             Valor maxim
     * @param esJugadorMaximal Indica si el jugador que ha de moure es l'atacant (true si ho es)
     * @param codi             Codi retornat al fer l'ultim moviment
     * @param torn             Color del jugador que ha de moure
     * @return Valor de la branca explorada
     */
    private int minimax(Tauler t, int profunditat, int alfa, int beta, boolean esJugadorMaximal, int codi, Color torn) {
        //possibles millores per M2:
        // - Heuristica segons posicio de tauler, ordenacio de moviments,... (potser cal afegir parametres a l'heuristica)
        // - Canviar funcio de node final, potser per limitar profunditat
        if (profunditat == 0 || codi == 2 || codi == 3) { //profunditat 0 o node final
            return heuristica(t, esJugadorMaximal, codi, torn);
        }
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        int bestMove;
        if (esJugadorMaximal) {
            bestMove = minVal;
            for (Moviment m : movPos) {
                int c = t.mou(m);
                bestMove = Math.max(bestMove, minimax(t, profunditat - 1, alfa, beta, false, c, torn.getNext()));
                t.mouInvers(m);
                alfa = Math.max(alfa, bestMove);
                if (alfa >= beta) break; //beta cut-off
            }
        } else {
            bestMove = maxVal;
            for (Moviment m : movPos) {
                int c = t.mou(m);
                bestMove = Math.min(bestMove, minimax(t, profunditat - 1, alfa, beta, true, c, torn.getNext()));
                t.mouInvers(m);
                beta = Math.min(beta, bestMove);
                if (alfa >= beta) break; //alfa cut-off
            }
        }
        return bestMove;
    }
}
