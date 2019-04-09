package domain;

import java.util.ArrayList;

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
    // - Idea: numero de solucions possibles (jugades diferents que acaben en mat)

    //Comprova que totes les jugades de l'oponent porten a un mat

    /**
     * Funció auxiliar per comprovar si el problema té solució
     *  - Si torn = tema prova tots els moviments vàlids
     *  - Sinó, comprova que tots els moviments possibles porten a un mat
     *
     * @param torn Color del jugador al que toca moure
     * @param jugada Número de jugades que ha fet el jugador que ataca
     * @return True el problema té solució
     */
    private boolean comprovaSolAux(Color torn, int jugada) {
        if (jugada>numJugades) return false;
        ArrayList<Moviment> al = situacioInicial.obteMovimentsJugador(torn);
        for (Moviment m: al) {
            int x = situacioInicial.mou(m);
            if (x != 3 && x != 4) {
                if (torn == tema) { //torn atacant
                    if (x == 2 || comprovaSolAux(torn.getNext(),jugada)) { //existeix mat
                        situacioInicial.mou_invers(m);
                        return true;
                    }
                }
                else { //torn defensa
                    if (x == 2 || !comprovaSolAux(torn.getNext(),jugada+1)) { //mat defensor o branca sense mat
                        situacioInicial.mou_invers(m);
                        return false;
                    }
                }
            }
            situacioInicial.mou_invers(m);
        }
        return torn!=tema;
    }

    /**
     * Comprova si el problema té solució
     *
     * @return True si té solució, false si no en té
     */
    private boolean comprovaSolucio() {
        return comprovaSolAux(tema, 0);
    }
    //“Cada node de moviment que no és teu ha de portar a una fulla mat”


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

