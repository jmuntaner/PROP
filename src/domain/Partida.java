package domain;

import java.util.ArrayList;

public class Partida {
    private int numMoviments;
    private ArrayList<Moviment> historial;
    private boolean torn;
    private Problema prob;
    private Tauler situacioActual;

    Partida(Problema p) {
        this.prob = p;
    }

    public int getNumMoviments() {
        return numMoviments;
    }

    public ArrayList<Moviment> getHistorial() {
        return historial;
    }


    public boolean isTorn() {
        return torn;
    }

    public void reset() {

    }

    public void moure(boolean jugador, Moviment mov) {

    }

    public EstadistiquesPartida getEstadistiques() {
        return null;
    }
}
