package domain;

import java.util.ArrayList;

public class Rei extends Peca {
    Rei(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if(c == Color.NEGRE) return 'k';
        else return 'K';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        return ((sx == 1 || sy == 1) && sx <= 1 && sy <= 1);
    }
}
