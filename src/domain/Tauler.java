package domain;

import javafx.util.Pair;

import java.util.ArrayList;

import static domain.Color.BLANC;
import static domain.Color.NEGRE;

public class Tauler {
    private Peca[][] peces;
    // TODO: repassar si cal tenir això apuntat
    private int escacN; // N: negres han fet escac, B = blanques han fet escac
    private int escacB; // 0 = res, 1 = escac, 2 = mat

    private Rei _reiBlanc, _reiNegre; //Proposo modificar els noms per Melcior i Baltasar


// PRIVATE METHODS
    // Check if check of player b (against player !b)
    // Pre: board contains a king of color !b

    public Tauler() {
        Peca[][] temp = {{null}}; //TODO: definir-ho be
        peces = temp;
        escacB = escacN = 0; //creadora es crida al "iniciar" un problema, i un problema no pot començar en escac/mat
    }

    public Tauler(Peca[][] pecesInicials) {
        peces = pecesInicials;
        checkMate();
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
    private boolean esMat(Color b) { //TODO: Repassar
        int[][] ocup = generaOcupacio();
        for (Peca[] f : peces)
            for (Peca p : f) {
                if (p.getColor() != b) {
                    ArrayList<Moviment> al = p.getMovimentsValids(ocup);
                    for (Moviment m : al) {
                        p.setPosicio(m.getPosFinal());
                        boolean aux = esEscac(b);
                        p.setPosicio(m.getPosIni());
                        if (!aux) return false;
                    }
                }
            }
        return true;
    }

// PUBLIC METHODS
    // Constructors

    /**
     * Genera la matriu de enters que representa la ocupació del Tauler
     * <p>
     * 0: buit, 1: blanques, 2: negres
     *
     * @return Matriu d'ocupació del Tauler
     */
    private int[][] generaOcupacio() {
        return null; //TODO: Implementar la traduccio
    }

    // Check if checkmate
    private void checkMate() {
        if (esEscac(BLANC)) {
            if (esMat(BLANC)) escacB = 2;
            else escacB = 1;
        } else escacB = 0;
        if (esEscac(NEGRE)) {
            if (esMat(NEGRE)) escacN = 2;
            else escacN = 1;
        } else escacN = 0;
    }

    //Getters i setters

    // Si b, retorna si les blanques han fet escac/mat; si!b, ídem per les negres
    public int getEscac(boolean b) {
        if (b) return escacB;
        else return escacN;
    }

    // Execute movement
    public void mou(Moviment mov) {
        Pair<Integer, Integer> pi = mov.getPosIni();
        int x = pi.getKey(); //TODO: En serio, això dels Pairs és una merda
        int y = pi.getValue();
        peces[x][y].setPosicio(mov.getPosFinal());
        // TODO: potser cal mirar algo mes, no ho se
        checkMate();
    }

    // Return all valid movements for a player
    public ArrayList<Moviment> obteMovimentsJugador(Color jugador) {
        ArrayList<Moviment> al = new ArrayList<>();
        int[][] ocup = generaOcupacio();
        for (Peca[] f : peces)
            for (Peca p : f) {
                if (p != null && p.getColor() == jugador) {
                    al.addAll(p.getMovimentsValids(ocup));
                }
            }
        return al;
    }

    public String getCasella(int x, int y) {
        //TODO
        return "r1";
    }
}
