package domain;

import java.util.ArrayList;

public class Peo extends Peca {
    Peo(int x, int y, Color color) {
        super(x,y,color);
    }

    @Override
    public char toChar(){
        Color c = this.getColor();
        if(c == Color.NEGRE) return 'p';
        else return 'P';}

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        int sx = Math.abs(x - x0);
        int inc;
        if(this.getColor()==Color.BLANC) inc = 1;
        else inc = -1; //negre
        boolean avancaDos = (y-y0 == 2*inc && sx == 0 && (y0 == 1 && this.getColor()==Color.BLANC || y0 == 6 && this.getColor()==Color.NEGRE)); //y0 == 3.5-2.5*inc seria una condicio mes curta pero sembla arbitraria
        boolean avancaUn = (y - y0 == inc && sx == 0);
        boolean menja = (y - y0 == inc && sx == 1);
        if(mata) return menja;
        else return avancaUn || avancaDos;
    }
}
