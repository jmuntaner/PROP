package domain;

public class Problema {
    private String nom;
    private Color tema;
    private int numJugades, dificultat; // dificultat = número decisions vàlides atacant / número solucions possibles
    private Tauler situacioInicial;
    private Ranking<PuntuacioProblema> ranking;
    //inspiració dificultat -> http://www.cogsys.org/app/webroot/papers/ACS2015/article7.pdf

//TODO: implementació i testing

    /**
     * Comprova si el problema té solució i, si en té, en guarda la dificultat
     *
     * @return True si té solució, false si no en té
     */
    private boolean comprovaSolucio() {
        int[] data = new int[]{0,0};
        situacioInicial.comprovaSolAux(tema, tema, 0, numJugades, data);
        if (data[0] == 0) return false;
        dificultat = data[1] / data[0];
        return true;
    }


    /**
     * Constructora
     *
     * @param nom String identificadora del problema
     */
    public Problema(String nom) {
        this.nom = nom;
    }


    /**
     * Getter nom
     *
     * @return Nom identificador del problema
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     *
     * @param nom Nom identificador del problema
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter tema
     *
     * @return Tema
     */
    public Color getTema() {
        return tema;
    }

    /**
     * Getter número jugades
     *
     * @return Número màxim de jugades
     */
    public int getNumJugades() {
        return numJugades;
    }

    /**
     * Getter dificultat
     *
     * @return Dificultat
     */
    public int getDificultat() { // Ara mateix l'escala no està clara, però x > 0;
        return dificultat;
    }

    /**
     * Getter situació inicial
     *
     * @return Tauler de situació inicial
     */
    public Tauler getSituacioInicial() {
        return situacioInicial;
    }

    /**
     * Getter ranking
     *
     * @return Ranking específic del problema
     */
    public Ranking<PuntuacioProblema> getRanking() {
        return ranking;
    }


    /**
     * Inicialització (o modificació) situació inicial problema
     * Comprova si el problema té solució
     *
     * @param t Tema
     * @param nj Número màxim de jugades
     * @param si Situació inicial del tauler
     * @return True si la inicialització s'ha completat amb èxit, false si no (problema sense solució)
     */
    public boolean initProblema(Color t, int nj, Tauler si) {
        tema = t;
        numJugades = nj;
        situacioInicial = si;
        ranking = new Ranking<>();
        return comprovaSolucio();
    }
}

//TODO: usage classe
// - Es crida initProblema a l'intentar guardar/jugar problema introduït o modificat
// - Si initProblema retorna false, ha de donar opció a modificar problema
// - El controlador gestiona color, tema i tauler
// - En cas de guardar, CtrlProblema ha de comprovar si el problema repeticions, si s'introdueix/modifica:
// -    -> Nom
// -    -> Tema + numJugades + situacióInicial

