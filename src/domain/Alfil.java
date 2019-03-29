package domain;

import java.util.ArrayList;

public class Alfil extends Peca {
    Alfil(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public ArrayList<Moviment> getMovimentsValids(Color[][] ocupacio) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        ArrayList<Moviment> movs = new ArrayList<>();

        int[][] inc; //increments relatius
        inc = new int[][]{{1,1},{-1,-1},{1,-1},{-1,1}};
        for (int[] dir: inc) {
            boolean exit = false;
            int step = 1;
            while (!exit) {
                int xp = x0 + step*dir[0];
                int yp = y0 + step*dir[1];

                //se surt del taulell o obstruccio peça del seu color
                if (xp < 0 || xp > 7 || yp < 0 || yp > 7 || ocupacio[xp][yp]==this.getColor()) exit = true;
                else {
                    Moviment mov = new Moviment(this, xp, yp);
                    movs.add(mov);
                    if(ocupacio[xp][yp]!=null) exit = true; //obstrucció per peça enemiga
                }
                step++;
            }
        }
        return movs;
    }

    @Override
    public char toChar() {
        return 'B';
    }

    @Override
    public boolean esMovimentValid(Color[][] ocupacio, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if (Math.abs(x - x0) != Math.abs(y - y0)) return false; //moviment no d'alfil
        int sx = x - x0;
        int sy = y - y0;
        while(x0!=x || y0!=y) {
            x0 += sx/Math.abs(sx);
            y0 += sy/Math.abs(sy);
            if((x0!=x || y0!=y) && ocupacio[x0][y0]!=null) return false;
        }
        return(ocupacio[x0][y0]!=this.getColor());
    }

    @Override
    public boolean esMovimentValid(int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        //moviment diagonal
        return Math.abs(x - x0) == Math.abs(y - y0);
    }
}
