package domain;

import javafx.util.Pair;

public class Moviment {
    private Pair<Integer, Integer> posIni, posFinal;

    public Moviment(){}

    public Moviment(Integer ix, Integer iy, Integer fx, Integer fy) {
        posIni = new Pair<>(ix,iy);
        posFinal = new Pair<>(fx,fy);
    }

    public Pair<Integer, Integer> getPosIni() {
        return posIni;
    }

    public void setPosIni(Integer x, Integer y) {
        posIni = new Pair<>(x,y);
    }

    public Pair<Integer, Integer> getPosFinal() {
        return posFinal;
    }

    public void setPosFinal(Integer x, Integer y) {
        posFinal = new Pair<>(x,y);
    }


}
