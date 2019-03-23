package domain;

import javafx.util.Pair;

import java.util.ArrayList;

import static domain.Color.*;

public class Tauler {

    // TODO: testing de tota la classe

    private static final int SIZE = 8;

    private Peca[][] peces;

    private Rei _reiBlanc, _reiNegre; //Proposo modificar els noms per Melcior i Baltasar

    /**
     * Creadora per defecte
     */
    public Tauler() {
        peces = new Peca[SIZE][SIZE];
    }

    /**
     * Creadora a partir de peces
     * @param pecesInicials situació inicial de les peces al tauler
     */
    //TODO:
    // - El controlador ha de cridar "finalitzaEntradaTauler" un cop s'han entrat totes les peces
    // - Tant si s'entren totes de cop o una per una
    public Tauler(Peca[][] pecesInicials) {
        peces = pecesInicials;
        for (Peca[] row: peces) {
            for (Peca p: row) {
                char c = p.toChar();
                if (c == 'K') _reiBlanc = (Rei) p; //Es lleig però garantir-ho amb un instanceof ho es mes
                else if (c == 'k') _reiNegre = (Rei) p;
            }
        }
    }

    /**
     * Indica si la posició actual del tauler es escac pel jugador indicat
     *
     * @param b Color pel que es vol comprovar si hi ha escac
     * @return true si hi a escac
     */
    private boolean esEscac(Color b) {
        Rei objectiu;
        if (b == BLANC) objectiu = _reiBlanc;
        else objectiu = _reiNegre;
        Pair<Integer, Integer> posRei = objectiu.getPosicio();
        int x = posRei.getKey();
        int y = posRei.getValue();

        int[][] inc; //increments relatius que poden amenaçar el rei
        inc = new int[][]{{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
        for (int[] dir: inc) { //comprovem totes direccions
            boolean exit = false;
            int step = 1;
            while (!exit) {
                int xp = x + step*dir[0];
                int yp = y + step*dir[1];
                if (xp<0 || xp>=SIZE || yp<0 || yp>=SIZE) exit = true;
                else {
                    Peca p = peces[xp][yp];
                    if (p != null) {
                        if (p.getColor() == b) exit = true;
                        else if (p.esMovimentValid(xp, yp)) return true;
                    }
                }
                if (dir[0]*dir[1]==2 || dir[0]*dir[1] == -2) exit = true; // posicions cavall
                else step++;
            }
        }
        return false;
    }

    /**
     * Indica si la posició actual del tauler és mat pel jugador indicat
     *
     * @param b Color pel que es vol comprovar si hi ha mat
     * @return true si hi ha mat
     */
    private boolean esMat(Color b, Color[][] taula_oc) { //TODO: implementar
        for (Peca[] f : peces)
            for (Peca p : f) {
                if (p.getColor() != b) {
                    ArrayList<Moviment> al = p.getMovimentsValids(taula_oc);
                    for (Moviment m : al) {
                        executaMoviment(m);
                        boolean esc = esEscac(b);
                        mou_invers(m);
                        if (!esc) return false;
                    }
                }
            }
        return true;
    }

    /**
     * Indica si la partida està en taules perquè només queden els dos reis
     *
     * @return true si partida en taules
     */
    private boolean esTaules() {
        for (Peca[] row: peces) for (Peca p: row) if (p!=null && p!=_reiBlanc && p!=_reiNegre) return false;
        return true;
    }

    /**
     * Genera la matriu de colors que representa l'cupació del Tauler
     *
     * @return Matriu d'ocupació del Tauler
     */
    private Color[][] generaOcupacio() {
        Color[][] oc = new Color[SIZE][SIZE];
        for (int i = 0; i<SIZE; i++) {
            for (int j = 0; j<SIZE; j++) {
                if (peces[i][j]!=null) oc[i][j] = peces[i][j].getColor();
            }
        }
        return oc;
    }

    /**
     * Indica si hi ha escac o mat pel jugador indicat
     *  0: no hi ha res
     *  1: hi ha escac
     *  2: hi ha mat
     *  3: taules
     *  4: situació invàlida
     * @param c Jugador
     * @param b Si false, comprova només escac/taules; si true, comprova escac/mat/taules
     * @return Retorna codi int d'escac 0 si no hi ha res, 1 si hi ha escac i (si b=true) 2 si hi ha escac i mat
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

        Color[][] taula_oc = generaOcupacio();
        boolean m = esMat(c, taula_oc);
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
        Pair<Integer,Integer> pf = mov.getPosFinal();
        int xf = pf.getKey();
        int yf = pf.getValue();
        Peca p = peces[xi][yi];
        Peca k = peces[xf][yf];

        p.setPosicio(pf);
        peces[xf][yf] = p;
        peces[xi][yi] = null;

        if (k != null) { //si mor alguna peça
            mov.setPecaMorta(k);
            k.setPosicio(null);
        }
    }

    /**
     *  Afegeix una peça al tauler
     *  La posició és la de la peça
     *
     * @param p Peça a afegir
     */
    public void afegirPeca (Peca p) {
        Pair<Integer,Integer> pos = p.getPosicio();
        int x = pos.getKey();
        int y = pos.getValue();
        peces[x][y] = p; //Possible exception si x/y són null o estan fora del rang
    }

    /**
     * Treu la peça situada a la posició x,y del tauler
     *
     * @param x Coordenada x de la posició
     * @param y Coordenada y de la posició
     */
    public void treurePeca (int x, int y){
        peces[x][y] = null; // Possible exception si x/y son null o estan fora del rang
    }


    /**
     * Executa el moviment d'una peça i indica l'estat final del tauler
     * 0: res, 1: escac, 2:mat, 3: taules, 4: invàlid (pot ser per escac contrari)
     * Si mor alguna peça, la seva posició passa a ser null.
     *
     * @param mov Moviment a executar
     * @param b Indica si es tracta d'un moviment definitiu (true) o només possible (false)
     * @return Estat després del moviment (escac/mat/taules)
     */
    public int mou(Moviment mov, boolean b) {
        executaMoviment(mov);
        Peca p = mov.getPecaMoguda();
        if (esEscacMat(p.getColor().getNext(), false) != 0) return 4;
        return esEscacMat(p.getColor(), b);
    }

    /**
     * Reverteix el moviment d'una peça
     *
     * @param mov Moviment que és vol executar a la inversa
     */
    public void mou_invers(Moviment mov) {
        Pair<Integer,Integer> pi = mov.getPosIni();
        int x = pi.getKey();
        int y = pi.getValue();
        Peca p = mov.getPecaMoguda();

        peces[x][y] = p;
        p.setPosicio(pi);

        Pair<Integer,Integer> pf = mov.getPosFinal();
        int x2 = pi.getKey();
        int y2 = pi.getValue();
        Peca k = mov.getPecaMorta();

        peces[x2][y2] = k; //pot ser null;
        if (k != null) k.setPosicio(pf);
    }

    /**
     * Retorna tots els moviments possibles per un dels jugadors
     *
     * @param jugador Jugador del qual es volen els moviments
     * @return Llista de moviments vàlids
     */
    public ArrayList<Moviment> obteMovimentsJugador(Color jugador) {
        ArrayList<Moviment> al = new ArrayList<>();
        Color[][] ocup = generaOcupacio();
        for (Peca[] f : peces)
            for (Peca p : f) {
                if (p != null && p.getColor() == jugador) {
                    al.addAll(p.getMovimentsValids(ocup));
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
        Peca p = peces[x][y];
        if (p == null) return null;
        Color[][] ocup = generaOcupacio();
        return p.getMovimentsValids(ocup);
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
     *  0: Correcte
     *  1: Incorrecte per falta del reig blanc
     *  2: Incorrecte per falta del rei negre
     *  3: Incorrecte per escac i mat
     *  4: Incorrecte per escac del jugador que comença movent
     *
     * @param c Jugador que té el primer torn
     * @return codi indicant la correcció del tauler
     */
    public int finalEntradaTauler(Color c) {
        return esEscacMat(c,true);
    }


}
