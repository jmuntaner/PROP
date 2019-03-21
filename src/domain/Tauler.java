package domain;

import java.util.ArrayList;
import javafx.util.Pair;

public class Tauler {
    private ArrayList<Peca> peces;
    private int escacN; // N: negres han fet escac, B = blanques han fet escac
    private int escacB; // 0 = res, 1 = escac, 2 = mat

// PRIVATE METHODS
    // Check if check of player b (against player !b)
    // Pre: board contains a king of color !b
    //
    private boolean isCheck(boolean b) {
        // Find the opposite king
        Peca rei = null;
        for (Peca p: peces) {
            if (p.getRei() && p.isColor() != b) rei = p;
        }
        for (Peca p: peces) {
            if (p != rei && p.isColor() != b) {
                ArrayList<Moviment> al = p.getMovimentsValids(this);
                for (Moviment m : al) {
                    Pair<Integer, Integer> pos = m.getPosFinal();
                    if (pos.equals(rei.getPosicio())) return true;
                }
            }
        }
        return false;
    }

    // Check if checkmate of player b (against !b)
    // Es poden eliminar alguns moviments a priori per millorar eficiencia?
    private boolean isMate(boolean b) {
        for (Peca p: peces) {
            if (p.isColor() != b) {
                ArrayList<Moviment> al = p.getMovimentsValids(this);
                for (Moviment m: al) {
                    p.setPosicio(m.getPosFinal());
                    boolean aux = isCheck(b);
                    p.setPosicio(m.getPosIni());
                    if (!aux) return false;
                }
            }
        }
        return true;
    }

    // Check if checkmate
    private void checkMate() {
        if (isCheck(true)) {
            if (isMate(true)) escacB = 2;
            else escacB = 1;
        }
        else escacB = 0;
        if (isCheck(false)) {
            if (isMate(false)) escacN = 2;
            else escacN = 1;
        }
        else escacN = 0;
    }

// PUBLIC METHODS
    // Constructors

    public Tauler() {
        peces = new ArrayList<>();
        escacB = escacN = 0; //creadora es crida al "iniciar" un problema, i un problema no pot començar en escac/mat
    }

    public Tauler(ArrayList<Peca> pecesInicials) {
        peces = pecesInicials;
        checkMate();
    }

    //Getters i setters

    // Si b, retorna si les blanques han fet escac/mat; si!b, ídem per les negres
    public int getEscac(boolean b) {
        if (b) return escacB;
        else return escacN;
    }

    // Add pieces
    public void addPeca(Peca p) {
        peces.add(p);
        checkMate(); // Si al final es fa servir s'ha de cridar quan estiguin totes les peces posades
    }

    // Execute movement
    public void mou(Moviment mov) {
        for (Peca p: peces) {
            if (p.getPosicio().equals(mov.getPosIni())) p.setPosicio(mov.getPosFinal());
        }
        checkMate();
    }

    // Return all valid movements for a player
    public ArrayList<Moviment> obteMovimentsJugador(boolean jugador) {
        ArrayList<Moviment> al = new ArrayList<>();
        for (Peca p: peces) {
            if (p.isColor() == jugador) {
                al.addAll(p.getMovimentsValids(this));
            }
        }
        return al;
    }
}
