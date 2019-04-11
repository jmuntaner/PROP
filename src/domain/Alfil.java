package domain;

import java.util.ArrayList;

public class Alfil extends Peca {
    Alfil(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if(c == Color.NEGRE) return 'b';
        else return 'B';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        //moviment diagonal
        return Math.abs(x - x0) == Math.abs(y - y0);
    }
}
