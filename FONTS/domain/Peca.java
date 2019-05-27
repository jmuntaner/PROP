package domain;

import utils.Pair;

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
        if (x < 0 || x > 7 || y < 0 || y > 7) throw new RuntimeException("Posicio fora del tauler");
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
     *
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
        this.x = pos.first();
        this.y = pos.second();
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
     * Retorna el codi char correponent al tipus de peça i color
     *
     * @return char codi
     */
    public abstract char toChar();

    /**
     * Indica si la peça es pot moure a la posició indicada.
     * Ho comprova segons el tipus de peça.
     *
     * @param mata Indica si la peça mata o no
     * @param x    Fila a on es vol moure la peça.
     * @param y    Columna a on es vol moure la peça.
     * @return Vertader si es pot moure la peça a la posició indicada.
     */
    public abstract boolean esMovimentValid(boolean mata, int x, int y);
}
