package controllers;

import domain.*;

public class ControladorEditor {
    private Tauler t;
    private Color colorInicial;
    private int numJugades;

    private ControladorPrincipal cp;

    ControladorEditor(ControladorPrincipal controladorPrincipal) {
        t = new Tauler();
        colorInicial = Color.BLANC;
        numJugades = 1;

        cp = controladorPrincipal;
    }

    public char getCasella(int x, int y) {
        return t.getCasella(x, y);
    }

    public void afegeixPeca(int x, int y, char tipus) {
        Peca p = FenTranslator.char2Peca(tipus, x, y);
        t.afegirPeca(p);
    }

    public void eliminaPeca(int x, int y) {
        t.treurePeca(x, y);
    }

    public boolean carregaFen(String fen) {
        try {
            t = FenTranslator.generaTauler(fen);
            colorInicial = FenTranslator.getColor(fen);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public String getFen() {
        return FenTranslator.generaFen(t, colorInicial);
    }

    public boolean getColorInicial() {
        return colorInicial == Color.BLANC;
    }

    public void setColorInicial(boolean color) {
        colorInicial = color ? Color.BLANC : Color.NEGRE;
    }

    public int getNumJugades() {
        return numJugades;
    }

    public void setNumJugades(int numJugades) {
        this.numJugades = numJugades;
    }

    /**
     * Guarda el problema a la base de dades.
     *
     * @return 0: Correcte, 1: Escac/mat/taules, 2: no hi ha solució
     */
    public int guardaProblema() {
        int rt = t.finalEntradaTauler(colorInicial);
        if (rt == 3) return 1;
        Problema p = new Problema(
                "Mat en " + numJugades + " per les " +
                        (colorInicial == Color.BLANC ? "blanques" : "negres"));
        if (!p.initProblema(numJugades, getFen()))
            return 2;
        cp.afegeixProblema(p);
        return 0;
    }
}
