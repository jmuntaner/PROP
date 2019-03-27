package domain;

import java.util.ArrayList;

public class Torre extends Peca {
    Torre(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public ArrayList<Moviment> getMovimentsValids(Color[][] ocupacio) {
        return null;
    }

    @Override
    public char toChar() {
        return 0;
    }

    @Override
    public boolean esMovimentValid(Color[][] ocupacio, int x, int y) {
        return false;
    }

    @Override
    public boolean esMovimentValid(int x, int y) {
        return false;
    }

}
