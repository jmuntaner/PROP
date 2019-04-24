package domain;

public class Rei extends Peca {
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
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if (x0 == x && y0 == y) return false; //moviment nul
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        return ((sx == 1 || sy == 1) && sx <= 1 && sy <= 1);
    }
}
