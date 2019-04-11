package domain;

import java.util.ArrayList;

public class Torre extends Peca {
    Torre(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if(c == Color.NEGRE) return 'r';
        else return 'R';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if(x0==x && y0==y) return false; //moviment nul
        //moviment torre
        return (x0==x) || (y0==y);
    }

}
