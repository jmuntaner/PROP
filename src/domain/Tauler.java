package domain;

import java.util.ArrayList;

public class Tauler {
    private ArrayList<Peca> peces;
    private boolean esEscac;
    private boolean esMat;

    Tauler(ArrayList<Peca> pecesInicials) {
        peces = pecesInicials;
    }

    public boolean getEsEscac() {
        return esEscac;
    }

    public void setEsEscac(boolean esEscac) {
        this.esEscac = esEscac;
    }

    public boolean getEsMat() {
        return esMat;
    }

    public void setEsMat(boolean esMat) {
        this.esMat = esMat;
    }

    public int mou(Moviment mov) {
        return 0;
    }

    public ArrayList<Moviment> obteMovimentsJugador(boolean jugador) {
        return null;
    }
}
