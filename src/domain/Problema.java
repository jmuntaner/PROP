package domain;

public class Problema {
    private String nom;
    private Color tema;
    private int numJugades, dificultat;
    private Tauler situacioInicial;
    private RankingProblema ranking;

//TODO: implementació i testing

    /**
     * Calcula la dificultat del problema i actualitza el valor de l'atribut
     */
    private void calculaDificultat() {}
    //TODO: implementar càlcul dificultat
    // - Buscar criteri per fer el càlcul

    /**
     * Comprova si el problema té solució
     *
     * @return True si té solució, false si no en té
     */
    //TODO: funcio per comprovar si existeix solucio
    // - Buscar algoritme o fer força bruta ez
    private boolean comprovaSolucio() {return false;}


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
    public int getDificultat() {
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
     * @return (?)
     */
    public RankingProblema getRanking() {
        return ranking;
    }
    //TODO: Ranking (setup inicial, gestió i lectura)


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
        boolean b = comprovaSolucio();
        if (!b) return false;
        calculaDificultat();
        return true;
    }
}

//TODO: usage classe
// - Es crida initProblema a l'intentar guardar/jugar problema introduït o modificat
// - Si initProblema retorna false, ha de donar opció a modificar problema
// - El controlador gestiona color, tema i tauler
// - En cas de guardar, CtrlProblema ha de comprovar si el problema repeticions, si s'introdueix/modifica:
// -    -> Nom
// -    -> Tema + numJugades + situacióInicial

