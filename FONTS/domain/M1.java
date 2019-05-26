package domain;

import java.util.ArrayList;

public class M1 extends Maquina {

    @Override
    public String getNom() {
        return "Xicu";
    }

    @Override
    double minimax(Tauler t, int profunditat, double alfa, double beta, boolean esJugadorMaximal, int codi, Color torn) {
        if (profunditat==0 || codi == 2 || codi == 3) {
            return heuristica(esJugadorMaximal, codi);
        }
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        double bestMove;
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
