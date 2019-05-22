package controllers;

import data.GestioProblema;
import domain.*;


public class ControladorEditor {
    private Tauler t;
    private Color colorInicial;
    private int numJugades;
    private String idProblema;
    private boolean esNou;

    private GestioProblema gp;

    /**
     * Creadora per defecte.
     */
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

    /**
     * Afegeix una peça al tauler.
     *
     * @param x     Posició X de la peça.
     * @param y     Posició Y de la peça.
     * @param tipus Representació en char de la peça (Com al FEN).
     */
    public void afegeixPeca(int x, int y, char tipus) {
        Peca p = FenTranslator.char2Peca(tipus, x, y);
        t.afegirPeca(p);
    }

    /**
     * Elimina una peça del tauler
     *
     * @param x Posició x de la peça.
     * @param y Posició Y de la peça.
     */
    public void eliminaPeca(int x, int y) {
        t.treurePeca(x, y);
    }

    /**
     * Carrega la distribució de peces a partir d'un FEN.
     *
     * @param fen FEN amb la distribució desitjada.
     * @return Vertader si les peces han estat carregades correctament, fals en cas contrari.
     */
    public boolean carregaFen(String fen) {
        try {
            t = FenTranslator.generaTauler(fen);
            colorInicial = FenTranslator.getColor(fen);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Obté la representació en FEN del problema.
     *
     * @return La representació en FEN del problema.
     */
    public String getFen() {
        return FenTranslator.generaFen(t, colorInicial);
    }

    /**
     * Obté el color inicial del problema.
     *
     * @return Vertader si juguen blanques, fals si negres.
     */
    public boolean getColorInicial() {
        return colorInicial == Color.BLANC;
    }

    /**
     * Estableix el color inicial del problema.
     *
     * @param color Vertader si juguen blanques, fals si negres.
     */
    public void setColorInicial(boolean color) {
        colorInicial = color ? Color.BLANC : Color.NEGRE;
    }

    /**
     * Obté el número de jugades del problema
     *
     * @return Número de jugades del problema.
     */
    public int getNumJugades() {
        return numJugades;
    }

    /**
     * Estableix el número de jugades del problema.
     *
     * @param numJugades Número de jugades del problema.
     */
    public void setNumJugades(int numJugades) {
        this.numJugades = numJugades;
    }

    /**
     * Estableix la id (nom) del problema.
     *
     * @param nom Nou nom del problema.
     */
    public void setIdProblema(String nom) {
        idProblema = nom;
    }

    /**
     * Indica si el problema es nou.
     *
     * @return Vertader si el problema es nou, fals en cas contrari.
     */
    public boolean esNou() {
        return esNou;
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
