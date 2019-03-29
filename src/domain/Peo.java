package domain;

import java.util.ArrayList;

public class Peo extends Peca {
    Peo(int x, int y, Color color) {
        super(x,y,color);
    }

    @Override
    public ArrayList<Moviment> getMovimentsValids(Color[][] ocupacio) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        ArrayList<Moviment> movs = new ArrayList<>();
        int inc;
        if(this.getColor()==Color.BLANC) inc = 1;
        else inc = -1; //negre
        //avançar1
        int yp = y0 + inc;
        int xp = x0;
        if (yp >= 0 && yp <= 7 && ocupacio[xp][yp] == null) {
            Moviment mov = new Moviment(this, xp, yp);
            movs.add(mov);
        }
        //menjar
        xp = x0 + 1;
        if (xp >= 0 && xp <= 7 && yp >= 0 && yp <= 7 && ocupacio[xp][yp] != null && ocupacio[xp][yp]!=this.getColor()) {
            Moviment mov = new Moviment(this, xp, yp);
            movs.add(mov);
        }
        xp = x0 - 1;
        if (xp >= 0 && xp <= 7 && yp >= 0 && yp <= 7 && ocupacio[xp][yp] != null && ocupacio[xp][yp]!=this.getColor()) {
            Moviment mov = new Moviment(this, xp, yp);
            movs.add(mov);
        }
        //avançar2
        xp = x0;
        yp += inc;
        boolean avancaDos = (y0 == 1 && this.getColor()==Color.BLANC || y0 == 6 && this.getColor()==Color.NEGRE);
        if(avancaDos && ocupacio[xp][yp]==null && ocupacio[xp][yp-inc]==null) {
            Moviment mov = new Moviment(this, xp, yp);
            movs.add(mov);
        }
        return movs;
    }

    @Override
    public boolean esMovimentValid(Color[][] ocupacio, int x, int y){
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        int sx = Math.abs(x - x0);
        int inc;
        if(this.getColor()==Color.BLANC) inc = 1;
        else inc = -1; //negre
        boolean avancaDos = (y-y0 == 2*inc && (y0 == 1 && this.getColor()==Color.BLANC || y0 == 6 && this.getColor()==Color.NEGRE));
        boolean avancaUn = (y - y0 == inc && sx == 0) && ocupacio[x][y]==null;
        boolean menja = (y - y0 == inc && sx == 1) && ocupacio[x][y]!=this.getColor() && ocupacio[x][y]!=null;
        return (avancaUn || menja || (avancaDos && ocupacio[x][y]==null && ocupacio[x][y-inc]==null));
    }

    @Override
    public boolean potMatar(int x, int y) { // TODO: modificar
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        int sx = Math.abs(x - x0);
        int inc;
        if(this.getColor()==Color.BLANC) inc = 1;
        else inc = -1; //negre
        boolean avancaDos = (y-y0 == 2*inc && (y0 == 1 && this.getColor()==Color.BLANC || y0 == 6 && this.getColor()==Color.NEGRE));
        return ((y - y0 == inc && sx<=1) || avancaDos);
    }

    @Override
    public char toChar(){return 'P';}
}
