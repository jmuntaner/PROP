package domain;

import java.util.ArrayList;

public class Peo extends Peca {
    Peo(int x, int y, Color color) {
        super(x,y,color);
    }

    @Override
    public ArrayList<Moviment> getMovimentsValids(Color[][] ocupacio) {
        return null;
    }

    @Override
    public boolean esMovimentValid(Color[][] ocupacio, int x, int y){return false;}

    @Override
    public boolean esMovimentValid(int x, int y) {
        return false;
    }

    @Override
    public char toChar(){return 'p';}
}
