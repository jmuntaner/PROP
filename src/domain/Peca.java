package domain;

import javafx.util.Pair;

import java.util.ArrayList;

public abstract class Peca {
    private Color color;
    private int x, y;

    /**
     * Crea una peça amb els atributs escollits.
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Peca(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Retorna el color de la peça.
     *
     * @return El color de la peça.
     */
    public Color getColor() {
        return color;
    }


    /**
     * Retorna la posició actual de la peça.
     * @return Pair amb la posició actual de la peça.
     */
    public Pair<Integer, Integer> getPosicio() {
        return new Pair<>(x, y);
    }

    /**
     * Modifica la posició de la peça
     *
     * @param pos Pair amb la posició de la peça
     */
    public void setPosicio(Pair<Integer, Integer> pos) {
        this.x = pos.getKey();
        this.y = pos.getValue();
    }

    /**
     * Modifica la posició de la peça.
     *
     * @param x Nova fila.
     * @param y Nova columna.
     */
    public void setPosicio(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calcula tots els moviments que pot realitzar la peça.
     * Te en compte la ocupació actual del tauler, passada com a paràmetre.
     * Varia en funció del tipus de peça
     *
     * @param ocupacio Matriu 8x8, indicant amb 0 casella buida, 1 fitxa blanca, 2 negra.
     * @return Llista de tots els moviments vàlids.
     */
    public abstract ArrayList<Moviment> getMovimentsValids(int[][] ocupacio);

    /**
     * Indica si la peça és un rei.
     *
     * @return true si la peça és rei.
     */
    public boolean getRei() {
        return false;
    }

    /**
     * Indica si la peça es pot moure a la posició indicada.
     *
     * @param ocupacio Matriu 8x8, indicant amb 0 casella buida, 1 fitxa blanca, 2 negra.
     * @param x        Fila a on es vol moure la peça.
     * @param y        Columna a on es vol moure la peça.
     * @return Vertader si es pot moure la peça a la posició indicada.
     */
    public abstract boolean esMovimentValid(int[][] ocupacio, int x, int y);
}
