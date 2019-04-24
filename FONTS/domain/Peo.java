package domain;

public class Peo extends Peca {
    /**
     * Creadora de peó
     *
     * @param x     Fila a la que es troba la peça.
     * @param y     Columna a la que es troba la peça.
     * @param color Color de la peça
     */
    public Peo(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if (c == Color.NEGRE) return 'p';
        else return 'P';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if (x0 == x && y0 == y) return false; //moviment nul
        int sy = Math.abs(y - y0);
        int inc;
        if (this.getColor() == Color.BLANC) inc = -1;
        else inc = 1; //negre
        boolean avancaDos = (x - x0 == 2 * inc && sy == 0 && (x0 == 6 && this.getColor() == Color.BLANC || x0 == 1 && this.getColor() == Color.NEGRE)); //y0 == 3.5-2.5*inc seria una condicio mes curta pero sembla arbitraria
        boolean avancaUn = (x - x0 == inc && sy == 0);
        boolean menja = (x - x0 == inc && sy == 1);
        if (mata) return menja;
        else return avancaUn || avancaDos;
    }
}
