package domain;

public class Torre extends Peca {

    /**
     * Creadora de torre
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Torre(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if (c == Color.NEGRE) return 'r';
        else return 'R';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if (x0 == x && y0 == y) return false; //moviment nul
        //moviment torre
        return (x0 == x) || (y0 == y);
    }

}
