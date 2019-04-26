package domain;

public class Cavall extends Peca {
    /**
     * Creadora de cavall
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Cavall(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if (c == Color.NEGRE) return 'n';
        else return 'N';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().first();
        int y0 = this.getPosicio().second();
        if (x0 == x && y0 == y) return false; //moviment nul
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        return ((sx == 1 && sy == 2) || (sx == 2 && sy == 1));
    }

}
