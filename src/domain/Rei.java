package domain;

import java.util.ArrayList;

public class Rei extends Peca {
    @Override
    public ArrayList<Moviment> getMovimentsValids(int[][] ocupacio) {
        return null;
    }

    @Override
    public boolean getRei() {
        return true;
    }
}
