package domain;

import javafx.util.Pair;

import java.util.ArrayList;

import static domain.Color.BLANC;

public class Tauler {

    // TODO: testing de tota la classe

    private static final int SIZE = 8;

    private Peca[][] peces;

    private Peca _reiBlanc, _reiNegre;

    /**
     * Creadora per defecte
     */
    public Tauler() {
        peces = new Peca[SIZE][SIZE];
    }

    /**
     * Creadora a partir de peces
     *
     * @param pecesInicials situació inicial de les peces al tauler
     */
    //TODO:
    // - El controlador ha de cridar "finalitzaEntradaTauler" un cop s'han entrat totes les peces
    // - Tant si s'entren totes de cop o una per una
    public Tauler(Peca[][] pecesInicials) {
        Peca rb = null;
        Peca rn = null;
        for (Peca[] row : pecesInicials) {
            for (Peca p : row) {
                if (p != null) {
                    char c = p.toChar();
                    if (c == 'K' && rb == null)
                        rb = p; //Es lleig però garantir-ho amb un instanceof ho es mes
                    else if (c == 'K') throw new RuntimeException("Rei blanc duplicat");
                    else if (c == 'k' && rb == null) rb = p;
                    else if (c == 'k') throw new RuntimeException("Rei negre duplicat");
                }
            }
        }
        peces = pecesInicials;
        _reiBlanc = rb;
        _reiNegre = rn;
    }


    /**
     * Indica si la posició actual del tauler es escac pel jugador indicat
     *
     * @param b Color pel que es vol comprovar si hi ha escac
     * @return true si hi a escac
     */
    private boolean esEscac(Color b) {
        Peca objectiu;
        if (b == BLANC) objectiu = _reiBlanc;
        else objectiu = _reiNegre;
        Pair<Integer, Integer> posRei = objectiu.getPosicio();
        int x = posRei.getKey();
        int y = posRei.getValue();

        int[][] inc = movimentsRelatius(); //increments relatius que poden amenaçar el rei
        for (int[] dir : inc) { //comprovem totes direccions
            boolean exit = false;
            int step = 1;
            while (!exit) {
                int xp = x + step * dir[0];
                int yp = y + step * dir[1];
                if (xp < 0 || xp >= SIZE || yp < 0 || yp >= SIZE) exit = true;
                else {
                    Peca p = peces[xp][yp];
                    if (p != null) {
                        if (p.getColor() == b) exit = true;
                        else if (p.esMovimentValid(true, xp, yp)) return true;
                    }
                }
                if (dir[0] * dir[1] == 2 || dir[0] * dir[1] == -2) exit = true; // posicions cavall
                else step++;
            }
        }
        return false;
    }

    /**
     * Indica si el rei estarà en escac després d'1 jugada qualsevol del rival
     * Això permet detectar situacions de mat i de rei ofegat
     *
     * @param b Color pel que es vol comprovar si hi ha mat
     * @return true si hi ha mat
     */
    private boolean esMat(Color b) {
        Pair<Integer, Integer> posRei;
        if (b == BLANC) posRei = _reiNegre.getPosicio();
        else posRei = _reiBlanc.getPosicio();
        ArrayList<Moviment> movs = obteMovimentsJugador(b);
        for (Moviment m : movs) {
            if (esPosicioAmenaca(posRei, m.getPosFinal())) {
                executaMoviment(m);
                boolean esc = esEscac(b);
                mouInvers(m);
                if (!esc) return false;
            }
        }
        return true;
    }


    /**
     * Indica si la posició pos pertany a "l'estrella" de posicions que amenacen centre
     * El cas centre == pos no es considera d'amenaça
     *
     * @param centre Posició de la peça amenaçada
     * @param pos    Posició a comprovar si amenaça
     * @return true si la posició pos amenaça centre
     */
    private boolean esPosicioAmenaca(Pair<Integer, Integer> centre, Pair<Integer, Integer> pos) {
        int incx = centre.getKey() - pos.getKey();
        int incy = centre.getValue() - pos.getValue();
        if (incx == 0 && incy == 0) return false; //centre == pos
        else if (incx == 0 || incy == 0) return true; //moviment horitzontal o vertical
        else if (Math.abs(incx) == Math.abs(incy)) return true; //moviment diagonal
        else if ((Math.abs(incx) == 2 && Math.abs(incy) == 1) || (Math.abs(incx) == 1 && Math.abs(incy) == 2))
            return true; //cavalls
        return false;
    }

    /**
     * Indica si la partida està en taules perquè només queden els dos reis
     *
     * @return true si partida en taules
     */
    private boolean esTaules() {
        for (Peca[] row : peces)
            for (Peca p : row) {
                if (p != null && p != _reiBlanc && p != _reiNegre) return false;
            }
        return true;
    }
    // Els casos de mat impossible (rei + peo/alfil/cavall) arriben al límit de jugades

    /**
     * Indica si hi ha escac o mat pel jugador indicat
     * 0: no hi ha res
     * 1: hi ha escac
     * 2: hi ha mat
     * 3: taules
     * 4: situació invàlida
     *
     * @param c Jugador
     * @param b Si false, comprova només escac/taules; si true, comprova escac/mat/taules
     * @return Retorna un enter segons el codi
     */
    private int esEscacMat(Color c, boolean b) {
        boolean e = esEscac(c);
        boolean t = esTaules();
        if (t) {
            if (!e) return 3;
            else return 4; // no hauria de passar però per si de cas
        }

        if (b) {
            if (e) return 1;
            else return 0;
        }

        boolean m = esMat(c);
        if (m) {
            if (e) return 2;
            else return 3;
        }

        if (e) return 1; //no hi ha mat ni taules
        return 0;
    }

    private void executaMoviment(Moviment mov) {
        Pair<Integer, Integer> pi = mov.getPosIni();
        int xi = pi.getKey();
        int yi = pi.getValue();
        Pair<Integer, Integer> pf = mov.getPosFinal();
        int xf = pf.getKey();
        int yf = pf.getValue();
        Peca p = peces[xi][yi];
        Peca k = peces[xf][yf];

        p.setPosicio(pf);
        peces[xf][yf] = p;
        peces[xi][yi] = null;

        if (k != null) { //si mor alguna peça
            mov.setPecaMorta(k);
        }
    }

    private int[][] movimentsRelatius() {
        return new int[][]{{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    }

    /**
     * Afegeix una peça al tauler
     * La posició és la de la peça
     *
     * @param p Peça a afegir
     */
    //TODO: substituir rei
    public void afegirPeca(Peca p) {
        Pair<Integer, Integer> pos = p.getPosicio();
        char c = p.toChar();
        if (c=='K' && _reiBlanc==null) _reiBlanc = p;
        else if (c=='K' && !pos.equals(_reiBlanc.getPosicio())) throw new RuntimeException("Rei blanc duplicat");
        else if (c!='K' && _reiBlanc!=null && pos.equals(_reiBlanc.getPosicio())) _reiBlanc = null;

        if (c=='k' && _reiNegre==null) _reiNegre = p;
        else if (c=='k' && !pos.equals(_reiNegre.getPosicio())) throw new RuntimeException("Rei negre duplicat");
        else if (c!='k' && _reiNegre!=null && pos.equals(_reiNegre.getPosicio())) _reiNegre = null;

        int x = pos.getKey();
        int y = pos.getValue();
        peces[x][y] = p;
    }

    /**
     * Treu la peça situada a la posició x,y del tauler
     * (És a dir, fa que la posició estigui buida independentment de si hi havia peça o no)
     *
     * @param x Coordenada x de la posició
     * @param y Coordenada y de la posició
     */
    public void treurePeca(int x, int y) {
        if (x<0 || y<0 || x>=SIZE || y>=SIZE) throw new RuntimeException("Posicio fora del tauler");
        if (peces[x][y]!=null) {
            char c = peces[x][y].toChar();
            if (c == 'K') _reiBlanc = null;
            else if (c == 'k') _reiNegre = null;
            peces[x][y] = null;
        }
    }


    /**
     * Executa el moviment d'una peça i indica l'estat final del tauler
     * 0: res, 1: escac, 2:mat, 3: taules, 4: invàlid (pot ser per escac contrari)
     * Si mor alguna peça, la seva posició passa a ser null.
     *
     * @param mov Moviment a executar
     * @return Estat després del moviment (escac/mat/taules)
     */
    public int mou(Moviment mov) {
        executaMoviment(mov);
        Peca p = mov.getPecaMoguda();
        int x = esEscacMat(p.getColor().getNext(), false);
        if (x == 1 || x == 2 || x == 4) return 4; //taules es moviment vàlid
        return esEscacMat(p.getColor(), true);
    }

    /**
     * Reverteix el moviment d'una peça
     *
     * @param mov Moviment que és vol executar a la inversa
     */
    public void mouInvers(Moviment mov) {
        Pair<Integer, Integer> pi = mov.getPosIni();
        int x = pi.getKey();
        int y = pi.getValue();
        Peca p = mov.getPecaMoguda();

        peces[x][y] = p;
        p.setPosicio(pi);

        Pair<Integer, Integer> pf = mov.getPosFinal();
        int x2 = pi.getKey();
        int y2 = pi.getValue();
        Peca k = mov.getPecaMorta();

        peces[x2][y2] = k; //pot ser null;
        if (k != null) k.setPosicio(pf); //potser és redundant pq la peça queda amb l'ultima posicio que tenia
    }

    /**
     * Retorna tots els moviments possibles per un dels jugadors
     *
     * @param jugador Jugador del qual es volen els moviments
     * @return Llista de moviments vàlids
     */
    public ArrayList<Moviment> obteMovimentsJugador(Color jugador) {
        ArrayList<Moviment> al = new ArrayList<>();
        for (Peca[] f : peces)
            for (Peca p : f) {
                if (p != null && p.getColor() == jugador) {
                    Pair<Integer, Integer> pos = p.getPosicio();
                    al.addAll(obteMovimentsPeca(pos.getKey(), pos.getValue()));
                }
            }
        return al;
    }

    /**
     * Retorna tots els moviments possibles per una posició concreta
     *
     * @param x Coordenada x de la posició de la peça
     * @param y Coordenada y de la posició de la peça
     * @return Llista de moviments vàlids si hi ha una peça, null si no n'hi ha
     */
    public ArrayList<Moviment> obteMovimentsPeca(int x, int y) {
        if (peces[x][y] == null) return null;
        Color b = peces[x][y].getColor();

        ArrayList<Moviment> movs = new ArrayList<>();
        int[][] inc = movimentsRelatius();
        for (int[] dir : inc) {
            boolean exit = false;
            int step = 1;
            while (!exit) {
                int xp = x + step * dir[0];
                int yp = y + step * dir[1];
                if (xp < 0 || xp >= SIZE || yp < 0 || yp >= SIZE) exit = true;
                else {
                    Peca p = peces[xp][yp];
                    if (p != null) {
                        if (p.getColor() == b) exit = true;
                        else {
                            boolean mata = peces[xp][yp] != null;
                            if (p.esMovimentValid(mata, xp, yp)) {
                                Moviment m = new Moviment(p, xp, yp);
                                if (mata) m.setPecaMorta(peces[xp][yp]);
                                movs.add(m);
                            }
                        }
                    }
                }
                if (dir[0] * dir[1] == 2 || dir[0] * dir[1] == -2) exit = true;
                else step++;
            }
        }
        return movs;
    }

    /**
     * Retorna el codi char de la peça situada a la posició indicada
     * Si no hi ha cap peça, retorna '-'
     *
     * @param x Coordenada x de la posició a comprovar
     * @param y Coordenada y de la posició a comprovar
     * @return Codi de la peça
     */
    public char getCasella(int x, int y) {
        Peca p = peces[x][y];
        if (p == null) return '-';
        return p.toChar();
    }

    /**
     * Indica si el tauler és vàlid com a situació inicial
     * 0: Correcte
     * 1: Incorrecte per falta del reig blanc
     * 2: Incorrecte per falta del rei negre
     * 3: Incorrecte per escac del jugador que comença / mat / taules
     *
     * @param c Jugador que té el primer torn
     * @return codi indicant la correcció del tauler
     */
    public int finalEntradaTauler(Color c) {
        if (_reiBlanc == null) return 1;
        if (_reiNegre == null) return 2;
        if (esEscacMat(c, true) != 0) return 3;
        int aux = esEscacMat(c.getNext(), true);
        if (aux != 0 && aux != 1) return 3; //el contrari pot esta en escac
        return 0;
    }

    //Comprova que totes les jugades de l'oponent porten a un mat

    /**
     * Funció auxiliar per comprovar si el problema té solució i cercar paràmetres per calcular la dificultat
     * - Si torn = tema, comprova que almenys un moviment porta a un mat.
     * - Sinó, comprova que tots els moviments possibles porten a un mat
     *
     * @param torn       Color del jugador al que toca moure
     * @param tema       Jugador atacant
     * @param jugada     Número de jugades que ha fet el jugador que ataca
     * @param numJugades Número màxim de jugades que pot fer l'atacant
     * @param data       Número de decisions vàlides de l'atacant i número de solucions (paràmetre de sortida)
     */
    public void comprovaSolAux(Color torn, Color tema, int jugada, int numJugades, int[] data) {
        if (jugada > numJugades) return;
        ArrayList<Moviment> al = obteMovimentsJugador(torn);
        if (torn == tema) {
            for (Moviment m : al) {
                int x = mou(m);
                if (x != 3 && x != 4) {
                    data[1]++;
                    if (x == 2) data[0]++; //final amb solució
                    else comprovaSolAux(torn.getNext(), tema, jugada, numJugades, data);
                }
                mouInvers(m);
            }
        } else {
            int num_sols_pre = data[0]; //solucions abans de començar el recorregut
            for (Moviment m : al) {
                int x = mou(m);
                if (x == 2 || x == 3) { //mat o taules (final sense solució)
                    data[0] = num_sols_pre;
                    mouInvers(m);
                    return;
                }
                int num_sols_act = data[0]; //solucions durant el recorregut
                comprovaSolAux(torn.getNext(), tema, jugada + 1, numJugades, data);
                if (num_sols_act == data[0]) { //la branca no té solució
                    data[0] = num_sols_pre;
                    mouInvers(m);
                    return;
                }
                mouInvers(m);
            }
        }
    }
}
