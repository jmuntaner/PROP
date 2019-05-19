package controllers;

import data.GestioProblema;
import domain.*;

//TODO:
// - Mirar quan s'entra idProblema -> al crear nou problema o al guardar
// - La funcio creaProblema o la funcio guardaProblema ha de rebre la String del nom

public class ControladorEditor {
    private Tauler t;
    private Color colorInicial;
    private int numJugades;
    private String idProblema;
    private boolean esNou;

    private GestioProblema gp;

    ControladorEditor() {
        creaProblema();
        gp = GestioProblema.getInstance();
    }

    /**
     * Inicialitza les dades d'un nou problema.
     */
    public void creaProblema() {
        t = new Tauler();
        esNou = true;
        numJugades = 1;
        colorInicial = Color.BLANC;
    }

    /**
     * Carrega un problema de la base de dades per a edició.
     *
     * @param nom Identificador del problema a editar.
     */
    public void carregaProblema(String nom) {
        Problema p = gp.getProblema(nom);
        t = p.getSituacioInicial();
        esNou = false;
        idProblema = nom;
        numJugades = p.getNumJugades();
        colorInicial = p.getTema();
    }

    /**
     * Obté la representació de la peça d'una casella.
     *
     * @param x Posició X de la peça.
     * @param y Posició Y de la peça.
     * @return Representació de la peça segons el format FEN.
     */
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
     * @return 0: Correcte, 1: Escac/mat/taules, 2: no hi ha solució, 3: nom repetit
     */
    public int guardaProblema() {
        int rt = t.finalEntradaTauler(colorInicial);
        if (rt == 3) return 1;
        Problema p = new Problema(idProblema);
        if (!p.initProblema(numJugades, getFen()))
            return 2;
        if (!esNou) gp.delete(idProblema);
        return gp.saveProblema(p) ? 0 : 3;
    }
}
