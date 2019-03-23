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
     * @param ix x posició inicial
     * @param iy y posició inicial
     * @param fx x posició final
     * @param fy y posició final
     * @param p peça moguda
     */
    public Moviment(Integer ix, Integer iy, Integer fx, Integer fy, Peca p) {
        posIni = new Pair<>(ix,iy);
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
     * Setter posició inicial
     * @param x x posició inicial
     * @param y y posició inicial
     */
    public void setPosIni(Integer x, Integer y) {
        posIni = new Pair<>(x,y);
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
        posFinal = new Pair<>(x,y);
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
    }

    /**
     * Getter peça morta
     * @return peça morta
     */
    public Peca getPecaMorta() {
        return k;
    }

    /**
     * Setter peca morta
     * @param k peça morta
     */
    public void setPecaMorta(Peca k) {
        this.k = k;
    }
}
