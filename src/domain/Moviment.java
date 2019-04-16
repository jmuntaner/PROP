package domain;

import javafx.util.Pair;

public class Moviment {
    private Pair<Integer, Integer> posIni, posFinal;
    private Peca p, k; //p = peça que es mou, k = peça morta

    /**
     * Creadora per defecte
     */
    public Moviment(){}

    /**
     * Creadora amb valors inicials
     * @param fx x posició final
     * @param fy y posició final
     * @param p peça moguda
     */
    public Moviment(Peca p, Integer fx, Integer fy) {
        if (fx<0 || fy<0 || fx>7 || fy>7) throw new RuntimeException("Posició final fora del tauler");
        posIni = p.getPosicio(); // per assegurar coherència de posicions
        if (posIni.getKey()==fx && posIni.getValue()==fy) throw new RuntimeException("Moviment nul");
        posFinal = new Pair<>(fx,fy);
        this.p = p;
    }

    /**
     * Getter posició inicial
     * @return posició inicial
     */
    public Pair<Integer, Integer> getPosIni() {
        return posIni;
    }

    /**
     * Getter posició final
     * @return posició final
     */
    public Pair<Integer, Integer> getPosFinal() {
        return posFinal;
    }

    /**
     * Setter posició final
     * @param x x posició final
     * @param y y posició final
     */
    public void setPosFinal(Integer x, Integer y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) throw new RuntimeException("Posició fora del tauler");
        if (posIni!=null && posIni.getKey()==x && posIni.getValue()==y) throw new RuntimeException("Moviment nul");
        posFinal = new Pair<>(x,y);
        k = null;
    }

    /**
     * Getter peça moguda
     * @return peça moguda
     */
    public Peca getPecaMoguda() {
        return p;
    }

    /**
     * Setter peca moguda
     * @param p peça moguda
     */
    public void setPecaMoguda(Peca p) {
        this.p = p;
        posIni = p.getPosicio();
    }

    /**
     * Getter peça morta
     * @return peça morta
     */
    public Peca getPecaMorta() { return k; }

    /**
     * Setter peca morta
     * Aquesta posició ha de ser igual a la posició final del moviment
     * @param k peça morta
     */
    public void setPecaMorta(Peca k) {
        if (!posFinal.equals(k.getPosicio())) throw new RuntimeException("Posició de peça morta incorrecta");
        this.k = k;
    }
}
