package domain;

import java.util.ArrayList;

public class Cavall extends Peca {
    Cavall(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if(c == Color.NEGRE) return 'n';
        else return 'N';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if(x0==x && y0==y) return false; //moviment nul
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        return ((sx == 1 && sy == 2) || (sx == 2 && sy == 1));
    }

}
