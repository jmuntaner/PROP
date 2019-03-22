package domain;

import java.util.ArrayList;

public class Partida {
    private int numMoviments;
    private ArrayList<Moviment> historial;
    private Color torn;
    private Problema prob;
    private Tauler situacioActual;

    /**
     * Crea la partida associada a un problema.
     *
     * @param p Problema sobre el que s'inicia la partida
     */
    Partida(Problema p) {
        prob = p;
        torn = p.getTema();
        situacioActual = prob.getSituacioInicial();
        historial = new ArrayList<Moviment>();
        numMoviments = 0;
    }

    /**
     * Indica el numero de moviments realitzats.
     * @return El numero de moviments realitzats.
     */
    public int getNumMoviments() {
        return numMoviments;
    }

    /**
     * Retorna l'historial de moviments
     * @return Llista dels moviments realitzats.
     */
    public ArrayList<Moviment> getHistorial() {
        return historial;
    }

    /**
     * Indica el torn actual.
     *
     * @return El color del jugador amb el torn vigent.
     */
    public Color getTorn() {
        return torn;
    }

    /**
     * Reinicia l'estat de la partida
     */
    public void reset() {
        historial.clear();
        numMoviments = 0;
        situacioActual = prob.getSituacioInicial();
    }

    /**
     * Executa un moviment.
     *
     * @param jugador Color del jugador que executa el moviment.
     * @param mov     Moviment a realitzar.
     */
    public void moure(Color jugador, Moviment mov) {
        if (jugador != torn) throw new RuntimeException("No es el torn del jugador especificat");
        situacioActual.mou(mov);
        historial.add(mov);
        numMoviments++;
    }

    /**
     * Obté la peça que es troba a la posició indicada.
     *
     * @param x Fila de la peça buscada.
     * @param y Columna de la peça buscada.
     * @return Caràcter que representa a la peça
     */
    public char getAtPosicio(int x, int y) {
        return situacioActual.getCasella(x, y);
    }

    /**
     * Retorna les estadístiques de la partida.
     * @return Estadístiques de la partida.
     */
    public EstadistiquesPartida getEstadistiques() {
        return null;
    }
}
