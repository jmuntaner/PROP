package controllers;

import domain.Color;
import domain.FenTranslator;
import domain.Peca;
import domain.Tauler;

public class ControladorEditor {
    private Tauler t;
    private Color colorInicial;

    ControladorEditor() {
        t = new Tauler();
        colorInicial = Color.BLANC;
    }

    public char getCasella(int x, int y) {
        return t.getCasella(x, y);
    }

    public void afegeixPeca(int x, int y, char tipus) {
        Peca p = FenTranslator.char2Peca(tipus, x, y);
        t.afegirPeca(p);
    }

    public void carregaFen(String fen) {
        t = FenTranslator.generaTauler(fen);
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
}
