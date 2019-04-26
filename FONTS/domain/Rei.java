package domain;

public class Rei extends Peca {
    /**
     * Creadora de rei
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Rei(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if (c == Color.NEGRE) return 'k';
        else return 'K';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().first();
        int y0 = this.getPosicio().second();
        if (x0 == x && y0 == y) return false; //moviment nul
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        return ((sx == 1 || sy == 1) && sx <= 1 && sy <= 1);
    }
}
