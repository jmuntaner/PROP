package domain;

import java.util.ArrayList;
import java.util.Random;

public abstract class Maquina {
    static final double maxVal = 9999;
    static final double minVal = -9999;

    /**
     * Getter del nom de la màquina
     *
     * @return Nom de la màquina
     */
    public abstract String getNom();

    public double heuristica(boolean esJugadorMaximal, int codi) {
        if (codi == 2) { //mat del jugador anterior
            if (!esJugadorMaximal) return maxVal;
            else return minVal;
        } else if (codi == 3) { //taules, atacant perd, defensor guanya.
            return minVal;
        }
        return 0;
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
        double bestMove;
        int codi;
        double ret;
        //if (torn == Color.BLANC) {
        bestMove = torn == tema ? minVal : maxVal;
        for (Moviment m : movPos) { //primera "iteracio" del minimax
            codi = t.mou(m);
            // torn != tema, defensor, minimitzem
            // torn == tema, atacant,  maximitzem
            ret = minimax(t, profunditat - 1, minVal, maxVal, torn != tema, codi, torn.getNext());
            if (ret == bestMove && (new Random().nextBoolean() || mov == null)) mov = m; //Si son iguals, tria aleatoriament
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
    abstract double minimax(Tauler t, int profunditat, double alfa, double beta, boolean esJugadorMaximal, int codi, Color torn);
}
