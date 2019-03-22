package domain;

import java.util.ArrayList;

public class Partida {
    private int numMoviments;
    private ArrayList<Moviment> historial;
    private boolean torn;
    private Problema prob;
    private Tauler situacioActual;

    Partida(Problema p) {
        prob = p;
        torn = p.getTema();
        situacioActual = prob.getSituacioInicial();
        historial = new ArrayList<Moviment>();
        numMoviments = 0;
    }

    public int getNumMoviments() {
        return numMoviments;
    }

    public ArrayList<Moviment> getHistorial() {
        return historial;
    }


    public boolean getTorn() {
        return torn;
    }

    public void reset() {
        historial.clear();
        numMoviments = 0;
        situacioActual = prob.getSituacioInicial();
    }

    public void moure(boolean jugador, Moviment mov) {
        if (jugador != torn) throw new RuntimeException("No es el torn del jugador especificat");
        situacioActual.mou(mov);
    }

    public String getAtPosicio(int x, int y) {
        return situacioActual.getCasella(x, y);
    }

    public EstadistiquesPartida getEstadistiques() {
        return null;
    }
}
