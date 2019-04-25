package domain;

import utils.Pair;

public class Moviment {
    private Pair<Integer, Integer> posIni, posFinal;
    private Peca p, k; //p = peça que es mou, k = peça morta

    /**
     * Creadora amb valors inicials
     *
     * @param fx x posició final
     * @param fy y posició final
     * @param p  peça moguda
     */
    public Moviment(Peca p, Integer fx, Integer fy) {
        if (fx < 0 || fy < 0 || fx > 7 || fy > 7) throw new RuntimeException("Posicio fora del tauler");
        Pair<Integer, Integer> aux = p.getPosicio(); //no modifica el moviment si hi ha excepcio
        if (aux.getKey() == fx && aux.getValue() == fy) throw new RuntimeException("Moviment nul");
        posIni = aux;
        posFinal = new Pair<>(fx, fy);
        this.p = p;
    }

    /**
     * Getter posició inicial
     *
     * @return posició inicial
     */
    public Pair<Integer, Integer> getPosIni() {
        return posIni;
    }

    /**
     * Getter posició final
     *
     * @return posició final
     */
    public Pair<Integer, Integer> getPosFinal() {
        return posFinal;
    }

    /**
     * Setter posició final
     *
     * @param x x posició final
     * @param y y posició final
     */
    public void setPosFinal(Integer x, Integer y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) throw new RuntimeException("Posicio fora del tauler");
        if (posIni.getKey() == x && posIni.getValue() == y) throw new RuntimeException("Moviment nul");
        posFinal = new Pair<>(x, y);
        k = null;
    }

    /**
     * Getter peça moguda
     *
     * @return peça moguda
     */
    public Peca getPecaMoguda() {
        return p;
    }

    /**
     * Setter peca moguda
     *
     * @param p peça moguda
     */
    public void setPecaMoguda(Peca p) {
        Pair<Integer, Integer> aux = p.getPosicio();
        if (aux.equals(posFinal)) throw new RuntimeException("Moviment nul");
        this.p = p;
        posIni = aux;
    }

    /**
     * Getter peça morta
     *
     * @return peça morta
     */
    public Peca getPecaMorta() {
        return k;
    }

    /**
     * Setter peca morta
     * La posició de la peça ha de ser igual a la posició final del moviment
     *
     * @param k peça morta
     */
    public void setPecaMorta(Peca k) {
        if (k != null && !posFinal.equals(k.getPosicio())) throw new RuntimeException("Posicio incorrecta");
        this.k = k;
    }
}
