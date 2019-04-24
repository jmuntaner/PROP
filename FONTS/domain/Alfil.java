package domain;

public class Alfil extends Peca {

    /**
     * Creadora d'alfil
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Alfil(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if (c == Color.NEGRE) return 'b';
        else return 'B';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if (x0 == x && y0 == y) return false; //moviment nul
        //moviment diagonal
        return Math.abs(x - x0) == Math.abs(y - y0);
    }
}
