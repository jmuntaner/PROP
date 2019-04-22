package domain;

public class Reina extends Peca {
    public Reina(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        Color c = this.getColor();
        if(c == Color.NEGRE) return 'q';
        else return 'Q';
    }

    @Override
    public boolean esMovimentValid(boolean mata, int x, int y) {
        int x0 = this.getPosicio().getKey();
        int y0 = this.getPosicio().getValue();
        if(x0==x && y0==y) return false; //moviment nul
        //moviment diagonal o de torre
        return (Math.abs(x - x0) == Math.abs(y - y0)) || ((x0==x) || (y0==y));
    }
}
