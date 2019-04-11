package domain;

import java.util.ArrayList;

public class Rei extends Peca {
    Rei(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public ArrayList<Moviment> getMovimentsValids(Color[][] ocupacio) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        ArrayList<Moviment> movs = new ArrayList<>();

        int[][] inc; //increments relatius
        inc = new int[][]{{1,1},{-1,-1},{1,-1},{-1,1},{1,0},{-1,0},{0,-1},{0,1}};
        for (int[] dir: inc) {
            int xp = x0 + dir[0];
            int yp = y0 + dir[1];
            //no se surt del taulell o no troba peça del seu color
            if (xp >= 0 && xp <= 7 && yp >= 0 && yp <= 7 && ocupacio[xp][yp] != this.getColor()) {
                Moviment mov = new Moviment(this, xp, yp);
                movs.add(mov);
            }
        }
        return movs;
    }

    @Override
    public char toChar() {
        return 'K';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        return potMatar(x, y);
    }

    /*
    @Override
    public boolean esMovimentValid(Color[][] ocupacio, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        //moviment valid + no peça mateix color
        return ((sx == 1 || sy == 1) && sx <= 1 && sy <= 1 && ocupacio[x][y]!=this.getColor());
    }
    */
    @Override
    public boolean potMatar(int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        int sx = Math.abs(x - x0);
        int sy = Math.abs(y - y0);
        return ((sx == 1 || sy == 1) && sx <= 1 && sy <= 1);
    }
}
