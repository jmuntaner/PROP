package domain;

import java.util.ArrayList;

public class Tauler {
    private ArrayList<Peca> peces;
    private boolean esEscac;
    private boolean esMat;

    // Constructors

    public Tauler() {
        peces = new ArrayList<>();
    }

    public Tauler(ArrayList<Peca> pecesInicials) {
        peces = pecesInicials;
    }

    //Getters i setters

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

    // Add pieces
    public void addPeca(Peca p) {
        peces.add(p);
    }

    // Execute movement
    // TODO mirar eficiencia, de moment cost lineal
    public void mou(Moviment mov) {
        for (Peca p: peces) {
            if (p.getPosicio().equals(mov.getPosIni())) p.setPosicio(mov.getPosFinal());
        }
    }

    public ArrayList<Moviment> obteMovimentsJugador(boolean jugador) {
        //TODO
        // - Aixo volem que sigui obtenir tots els moviments d'un jugador o d'una peça concreta (o necessitem les dues)?
        // - La primera és menys eficient si ha de calcular tots els moviments
        return null;
    }

    // TODO funcio getOcupacio o similar
}
