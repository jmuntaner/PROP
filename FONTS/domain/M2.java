package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class M2 extends Maquina {
    private Map<String, TranspositionTableEntry> cache = new HashMap<>();
    private Map<Moviment, Integer> visitedMoves = new HashMap<>();

    private static final int MOVE_MAX_REPETITIONS = 1;
    private static final int LIMIT_PROFUNDITAT = 6;

    private static final double[][] posK = {
            {-3,-4,-4,-5,-5,-4,-4,-3},
            {-3,-4,-4,-5,-5,-4,-4,-3},
            {-3,-4,-4,-5,-5,-4,-4,-3},
            {-3,-4,-4,-5,-5,-4,-4,-3},
            {-2,-3,-3,-4,-4,-3,-3,-2},
            {-1,-2,-2,-2,-2,-2,-2,-1},
            {2,2,0,0,0,0,2,2},
            {2,3,1,0,0,1,3,2}
    };

    private static final double[][] posQ = {
            {-2,-1,-1,-0.5,-0.5,-1,-1,-2},
            {-1,0,0,0,0,0,0,-1},
            {-1,0,0.5,0.5,0.5,0.5,0,-1},
            {-0.5,0,0.5,0.5,0.5,0.5,0,-0.5},
            {0,0,0.5,0.5,0.5,0.5,0,-0.5},
            {-1,0.5,0.5,0,0,0.5,0,-1},
            {-1,0,0.5,0,0,0,0,-1},
            {-2,-1,-1,-0.5,-0.5,-1,-1,-2}
    };

    private static final double[][] posR = {
            {0,0,0,0,0,0,0,0},
            {0.5,1,1,1,1,1,1,0.5},
            {-0.5,0,0,0,0,0,0,-0.5},
            {-0.5,0,0,0,0,0,0,-0.5},
            {-0.5,0,0,0,0,0,0,-0.5},
            {-0.5,0,0,0,0,0,0,-0.5},
            {-0.5,0,0,0,0,0,0,-0.5},
            {0,0,0,0.5,0.5,0,0,0}
    };

    private static final double[][] posB = {
            {-2,-1,-1,-1,-1,-1,-1,-2},
            {-1,0,0,0,0,0,0,-1},
            {-1,0,0.5,1,1,0.5,0,-1},
            {-1,0.5,0.5,1,1,0.5,0.5,-1},
            {-1,0,1,1,1,1,0,-1},
            {-1,1,1,1,1,1,1,-1},
            {-1,0.5,0,0,0,0,0.5,-1},
            {-2,-1,-1,-1,-1,-1,-1,-2}
    };

    private static final double[][] posN = {
            {-5,-4,-3,-3,-3,-3,-4,-5},
            {-4,-2,0,0,0,-0,-2,-4},
            {-3,0,1,1.5,1.5,1,0,-3},
            {-3,0.5,1.5,2,2,1.5,0.5,-3},
            {-3,0,1.5,2,2,1.5,0,-3},
            {-3,0.5,1,1.5,1.5,1,0.5,-3},
            {-4,-2,0,0.5,0.5,0,-2,-4},
            {-5,-4,-3,-3,-3,-3,-5,-5}
    };

    private static final double[][] posP = {
            {0,0,0,0,0,0,0,0},
            {5,5,5,5,5,5,5,5},
            {1,1,2,3,3,2,1,1},
            {0.5,0.5,1,2.5,2.5,1,0.5,0.5},
            {0,0,0,2,2,0,0,0},
            {0.5,-0.5,-1,0,0,-1,-0.5,0.5},
            {0.5,1,1,-2,-2,1,1,0.5},
            {0,0,0,0,0,0,0,0}
    };

    private double valor_peca(char p, int x, int y) {
        double v = 0;
        switch (p) {
            case 'p':
                v = -10 - posP[x][y];
                break;
            case 'P':
                v = 10 + posP[x][y];
                break;
            case 'r':
                v = -50 - posR[x][y];
                break;
            case 'R':
                v = 50 + posR[x][y];
                break;
            case 'b':
                v = -30 - posB[x][y];
                break;
            case 'B':
                v = 30 + posB[x][y];
                break;
            case 'n':
                v = -30 - posN[x][y];
                break;
            case 'N':
                v = 30 + posN[x][y];
                break;
            case 'q':
                v = -90 - posQ[x][y];
                break;
            case 'Q':
                v = 90 + posQ[x][y];
                break;
            case 'k':
                v = -9000 - posK[x][y];
                break;
            case 'K':
                v = 9000 + posK[x][y];
                break;
        }
        return v;
    }

    private boolean limitProfunditat(int profunditat) {
        return profunditat==0;
        //return profunditat==0 || profunditat <= getProfunditatInicial()-LIMIT_PROFUNDITAT;
    }

    private double heuristica(Tauler posicio, boolean esJugadorMaximal, int codi, Color torn) {
        if (codi == 2) { //mat del jugador anterior
            if (!esJugadorMaximal) return maxVal;
            else return minVal;
        } else if (codi == 3) { //taules, atacant perd, defensor guanya.
            return minVal;
        }
        double v = 0;
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                v += valor_peca(posicio.getCasella(i,j),i,j);
            }
        }
        if(torn == Color.BLANC && !esJugadorMaximal || torn == Color.NEGRE && esJugadorMaximal) v*=-1;
        //valor escac
        if (codi == 1) { //escac del jugador anterior
            if (!esJugadorMaximal) v+=100;
            else v-=100;
        }
        return v;
    }

    @Override
    public String getNom() {
        return "Barja";
    }

    //https://github.com/Vadman97/ChessGame/blob/master/src/vad/AIPlayer.java
    @Override
    double minimax(Tauler t, int profunditat, double alfa, double beta, boolean esJugadorMaximal, int codi, Color torn) {
        String fen = FenTranslator.generaFen(t,torn);
        if (cache.containsKey(fen)) {
            //System.out.println("Key found in cache");
            TranspositionTableEntry entry = cache.get(fen);
            if (entry.getLower() >= beta) return entry.getLower();
            if (entry.getUpper() <= alfa) return entry.getUpper();
            alfa = Math.max(alfa, entry.getLower());
            beta = Math.min(beta, entry.getUpper());
        }
        if (limitProfunditat(profunditat) || codi == 2 || codi==3) {
            return heuristica(t, esJugadorMaximal, codi, torn);
        }
        double bestMove = 0;
        Moviment best = null;
        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);
        if (esJugadorMaximal) {
            bestMove = minVal;
            double a = alfa;
            for (Moviment m : movPos) {
                if (bestMove >= beta) break;
                if(visitedMoves.containsKey(m)) {
                    int r = visitedMoves.get(m);
                    if (r >= MOVE_MAX_REPETITIONS) {
                        System.out.println("Move visited too many times");
                        continue;
                    }
                    else visitedMoves.put(m, r+1);
                }
                else visitedMoves.put(m, 1);
                int c = t.mou(m);
                double val = minimax(t, profunditat - 1, a, beta, false, c, torn.getNext());
                t.mouInvers(m);
                if (visitedMoves.get(m)>1) visitedMoves.put(m,visitedMoves.get(m)-1);
                else visitedMoves.remove(m);
                if (val > bestMove) {
                    bestMove = val;
                    best = m;
                }
                a = Math.max(a, bestMove);
            }
        } else {
            bestMove = maxVal;
            double b = beta;
            for (Moviment m : movPos) {
                if (bestMove <= alfa) break;
                if (visitedMoves.containsKey(m)) {
                    if (visitedMoves.get(m) >= MOVE_MAX_REPETITIONS) {
                        System.out.println("Move visited too many times");
                        continue;
                    }
                    else visitedMoves.put(m,1);
                }
                else visitedMoves.put(m,1);
                int c = t.mou(m);
                double val = minimax(t, profunditat - 1, alfa, b, true, c, torn.getNext());
                t.mouInvers(m);
                if (visitedMoves.get(m)>1) visitedMoves.put(m, visitedMoves.get(m)-1);
                visitedMoves.remove(m);
                if (val < bestMove) {
                    bestMove = val;
                    best = m;
                }
                b = Math.min(b, bestMove);
            }
        }

        if (bestMove <= alfa) cache.put(fen, new TranspositionTableEntry(minVal, bestMove,best));
        else if (bestMove > alfa && bestMove < beta) cache.put(fen, new TranspositionTableEntry(bestMove, bestMove, best));
        else if (bestMove >= beta) cache.put(fen, new TranspositionTableEntry(bestMove, maxVal, best));
        return bestMove;
    }
}

/*
Idees:
https://stackoverflow.com/questions/9964496/alpha-beta-move-ordering
    - Alpha-beta pruning (igual que M1)
    - Cut-off profunditat: no calcula tot el minimax
        -> Calcula maxim dos moviments per jugador (arbitrari)
        -> Aixi esperem tenir una avaluació decent pero no exacta
    - Heurística de peces: cada peça al tauler te un valor segons el seu tipus
    - Heurística de posicions: cada tipus de peça te un valor diferent segons la posicio al tauler
        -> No es massa util per una partida tan rapida (esta posat per partides llargues)
    - Prioritat per a les jugades que acaben en mat
    - Move ordering: avalua els millors moviments primer
        -> No implementat perque el minimax es poc profund i no compensa
        -> Cal fer els moviments dos cops
    - Transposition tables: comprovacio de repeticions
    - Moviments: limita el nombre de vegades que es comprova un moviment
 */
