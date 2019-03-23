package domain;

import javafx.util.Pair;

import java.util.ArrayList;

import static domain.Color.*;

public class Tauler {

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
    // - Tal i com implementem la classe, cal que el controlador cridi a esEscacMat després d'entrar totes les peces
    // - Si és mat d'un dels dos, o si és escac a favor del jugador que té el primer torn, problema invàlid
    // - Excepció: si no es troben els dos reis (un dels dos és nul, problema invàlid)
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
        int x = posRei.getKey(); //TODO: Els pairs de JAva son raros, millor utilitzar arrays
        int y = posRei.getValue();

        return false;
    }

    // Check if checkmate of player b (against !b)
    // Es poden eliminar alguns moviments a priori per millorar eficiència?

    /**
     * Indica si la posició actual del tauler és mat pel jugador indicat
     *
     * @param b Color pel que es vol comprovar si hi ha escac
     * @return true si hi ha mat
     */
    private boolean esMat(Color b) { //TODO: Repassar
        Color[][] ocup = generaOcupacio();
        for (Peca[] f : peces)
            for (Peca p : f) {
                if (p.getColor() != b) {
                    ArrayList<Moviment> al = p.getMovimentsValids(ocup); //aixo retornara una sola posicio
                    for (Moviment m : al) {     //caldrà generar bé el moviment
                        p.setPosicio(m.getPosFinal()); // mou
                        boolean aux = esEscac(b);
                        p.setPosicio(m.getPosIni()); // mou invers
                        if (!aux) return false;
                    }
                }
            }
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
     *  Afegeix una peça al tauler
     *  La posició és la de la peça
     *
     * @param p Peça a afegir
     */
    public void afegirPeca (Peca p) {}

    /**
     * Treu la peça situada a la posició x,y del tauler
     *
     * @param x Coordenada x de la posició
     * @param y Coordenada y de la posició
     */
    public void treurePeca (int x, int y){}


    /**
     * Executa el moviment d'una peça
     *
     * @param mov Moviment a executar
     */
    public void mou(Moviment mov) {
        Pair<Integer, Integer> pi = mov.getPosIni();
        int x = pi.getKey(); //TODO: En serio, això dels Pairs és una merda
        int y = pi.getValue();
        peces[x][y].setPosicio(mov.getPosFinal());
        // TODO: actualitzar peça moguda (?) + morta
        //checkMate();
    }

    /**
     * Reverteix el moviment d'una peça
     *
     * @param mov Moviment que és vol executar a la inversa
     */
    public void mou_invers(Moviment mov) {
        //TODO: implementar
    }

    /**
     * Retorna tots els moviments possibles per un dels jugdors
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
     * Retorna tots els moviments possibles per una peça concreta
     * Retorna un moviment i no una posició per poder cridar mou() directament, i pq contè si mata alguna peça
     *
     * @param c Color de la peça
     * @param x Coordenada x de la posició de la peça
     * @param y Coordenada y de la posició de la peça
     * @return Llista de moviments vàlids
     */
    public ArrayList<Moviment> obteMovimentsPeca(Color c, int x, int y) {
        return null;
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
        //if null return '-';
        return peces[x][y].toChar();
    }

    /**
     * Indica si hi ha escac o mat pel jugador indicat
     *
     * @param c Jugador
     * @param b Si false, comprova només escac; si true, comprova escac i mat
     * @return Retorna 0 si no hi ha res, 1 si hi ha escac i (si b=true) 2 si hi ha escac i mat
     */
    public int esEscacMat(Color c, boolean b) {
        /*if (esEscac(BLANC)) {
            if (esMat(BLANC)) escacB = 2;
            else escacB = 1;
        } else escacB = 0;
        if (esEscac(NEGRE)) {
            if (esMat(NEGRE)) escacN = 2;
            else escacN = 1;
        } else escacN = 0; */
        return 0;
    }

}
