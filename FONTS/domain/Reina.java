package domain;

public class Reina extends Peca {

    /**
     * Creadora de reina
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Reina(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if (c == Color.NEGRE) return 'q';
        else return 'Q';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().first();
        int y0 = this.getPosicio().second();
        if (x0 == x && y0 == y) return false; //moviment nul
        //moviment diagonal o de torre
        return (Math.abs(x - x0) == Math.abs(y - y0)) || ((x0 == x) || (y0 == y));
    }
}
