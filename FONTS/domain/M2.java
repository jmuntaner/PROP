package domain;

import java.util.*;

public class M2 extends Maquina {
    private Map<String, TranspositionTableEntry> cache = new HashMap<>();
    private Map<String, Integer> visitedMoves = new HashMap<>();

    private static final int MOVE_MAX_REPETITIONS = 1;

    /*
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

    private void order(ArrayList<Moviment> movs, Tauler t, boolean jmax, Color torn) {
        ArrayList<Pair<Double,Moviment>> values = new ArrayList<>();
        for (Moviment m: movs) {
            int codi = t.mou(m);
            values.add(new Pair<>(heuristica(jmax, codi), m));
            t.mouInvers(m);
        }
        //ordenacio en ordre ascendent
        Collections.sort(values, new Comparator<Pair<Double, Moviment>>() {
            @Override
            public int compare(Pair<Double, Moviment> o1, Pair<Double, Moviment> o2) {
                int x = o1.first().compareTo(o2.first());
                return x;
            }
        });
        movs.clear();
        if (!jmax) Collections.reverse(values);
        for (Pair<Double,Moviment> p: values) movs.add(p.second());
    }
    */

    @Override
    public String getNom() {
        return "Barja";
    }

    private void decVisited(String sm) {
        if (visitedMoves.get(sm)>1) visitedMoves.put(sm, visitedMoves.get(sm)-1);
        else visitedMoves.remove(sm);
    }

    private boolean incVisited(String sm) {
        if (visitedMoves.containsKey(sm)) {
            int r = visitedMoves.get(sm);
            if (r >= MOVE_MAX_REPETITIONS) return true;
            else visitedMoves.put(sm, r+1);
        }
        else visitedMoves.put(sm,1);
        return false;
    }

    //https://github.com/Vadman97/ChessGame/blob/master/src/vad/AIPlayer.java
    private double minimaxAux(Tauler t, int profunditat, double alfa, double beta, boolean jmax, int codi, Color torn) {
        String fen = FenTranslator.generaFen(t,torn);
        if (cache.containsKey(fen)) {
            //System.out.println("Key found in cache");
            TranspositionTableEntry entry = cache.get(fen);
            if (entry.getLower() >= beta) return entry.getLower();
            if (entry.getUpper() <= alfa) return entry.getUpper();
            alfa = Math.max(alfa, entry.getLower());
            beta = Math.min(beta, entry.getUpper());
        }
        if (profunditat==0 || codi == 2 || codi==3)
            return heuristica(jmax, codi);

        ArrayList<Moviment> movPos = t.obteMovimentsJugador(torn);

        double bestMove = jmax ? minVal : maxVal;
        double a = alfa;
        double b = beta;
        Moviment best = null;

        for (Moviment m: movPos) {
            if (jmax ? bestMove >= b : bestMove <= a) break;
            String sm = m.movCode();
            if (incVisited(sm)) continue;
            int c = t.mou(m);
            double val = minimaxAux(t, profunditat-1, a, b, !jmax, c, torn.getNext());
            t.mouInvers(m);
            decVisited(sm);
            if (jmax) {
                if (val > bestMove) {
                    bestMove = val;
                    best = m;
                }
                a = Math.max(a, bestMove);
            }
            else {
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

    @Override
    double minimax(Tauler t, int profunditat, double alfa, double beta, boolean esJugadorMaximal, int codi, Color torn) {
        cache.clear();
        visitedMoves.clear();
        return minimaxAux(t, profunditat, alfa, beta, esJugadorMaximal, codi, torn);
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
    - Transposition tables: comprovacio de repeticions
    - Moviments: limita el nombre de vegades que es comprova un moviment
        Nomes serveix per problemes llargs (però no fa que trigui molt mes aixi que aqui es queda)
 */

/*
Resultats provisionals M2 (problema mat en 3, profunditat total):
    - Heuristica M1 sense cache: 2700 ms - 168297 nodes
    - Heuristica M1 amb cache: 2400 ms
    - Heuristica M2 amb cache: 3445082 nodes (aprox 40-50 segons)
    - Heuristica M2 sense cache: >~ 4M nodes
    -> La llista de moviments repetits no modifica el numero de nodes al ser problemes petits
    -> Com que no fa varia gaire el temps, la deixem per si pot ser util en algun cas
    - Heuristica M2 amb cache i move ordering: 33 s - 555k nodes

    - M1: 190k nodes - 2,7 s
    - M2: 167k nodes - 2.7 s

Problema de 4, profunditat total:
    - Heuristica M1 sense millores: ~50s - 4,869M nodes
    - Heuristica M2: 731 s - 49M nodes
    - Heuristica M1 amb cache: 33s - 2.3M nodes
    - Heuristica M1 amb cache i ordenacio: 130s

    -> Decisio final: M2
        - Heuristica: M1
        - Millores de rendiment: cache taulers, límit repetició de moviments, ordenació de moviments
        - Profunditat: total (sense limitar)
 */