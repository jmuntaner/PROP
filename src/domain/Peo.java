package domain;

import java.util.ArrayList;

public class Peo extends Peca {
    public Peo(int x, int y, Color color){
        super(x,y,color);
    }
    //sorry :') he obert la classe sense voler i he hagut de posar aixè pq no donés error

    @Override
    public ArrayList<Moviment> getMovimentsValids(Color[][] ocupacio) {
        return null;
    }

    @Override
    public boolean esMovimentValid(Color[][] ocupacio, int x, int y){return false;}

    @Override
    public char toChar(){return 'p';}
}
