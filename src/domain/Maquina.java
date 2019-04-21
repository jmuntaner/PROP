package domain;

import java.util.ArrayList;

public abstract class Maquina {
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
     * Mou el millor per a la maquina en qüestió
     *
     * @param profunditat profunditat maxima d'exploracio de l'arbre
     */
    public Moviment calcularMoviment(int profunditat, Tauler t, Color torn, Color tema) {
        //Color torn = partida.getTorn();
        //Tauler t = partida.getSituacioActual();
        Moviment mov = null;
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        int bestMove;
        int codi;
        int ret;
        //if (torn == Color.BLANC) {
        if (torn == tema) {
            bestMove = -9999;
            for (Moviment m : movPos) {
                codi = t.mou(m);
                ret = minimax(t, profunditat, false, codi, torn);
                if (ret > bestMove) {
                    mov = m;
                    bestMove = ret;
                }
                t.mouInvers(m);
            }
        }
        //else if (torn == Color.NEGRE) {
        else {
            bestMove = 9999;
            for (Moviment m : movPos) {
                codi = t.mou(m);
                ret = minimax(t, profunditat - 1, false, codi, torn);
                if (ret < bestMove) {
                    mov = m;
                    bestMove = ret;
                }
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
     * @param esJugadorMaximal Indica si es el jugador maximal (en aquest cas les blanques)
     * @param codi Codi retornat al fer l'ultim moviment
     * @return Valor de la branca explorada
     */
    private int minimax(Tauler t, int profunditat, boolean esJugadorMaximal, int codi, Color torn) {
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
        //ArrayList<Moviment> movPos = t.obteMovimentsJugador(this.partida.getTorn());
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        int bestMove;
        int c;
        if (esJugadorMaximal) {
            bestMove = -9999;
            for (Moviment m : movPos) {
                c = t.mou(m);
                bestMove = Math.max(minimax(t, profunditat - 1, false, c, torn.getNext()), bestMove);
                t.mouInvers(m);
            }
        } else {
            bestMove = 9999;
            for (Moviment m : movPos) {
                c = t.mou(m);
                bestMove = Math.min(minimax(t, profunditat - 1, true, c, torn.getNext()), bestMove);
                t.mouInvers(m);
            }
        }
        return bestMove;
    }
}
