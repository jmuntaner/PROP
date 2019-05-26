package domain;

public class TranspositionTableEntry {
    private double lower;
    private double upper;
    private Moviment bestMove;

    /**
     * Creadora de l'entrada per a la taula de transposició
     * (Cache de nodes visitats en el minimax)
     *
     * @param lower Valor mínim associat al node
     * @param upper Valor màxim associat al node
     * @param best Millor moviment següent trobat per al node
     */
    public TranspositionTableEntry(double lower, double upper, Moviment best) {
        this.lower = lower;
        this.upper = upper;
        this.bestMove = best;
    }

    /**
     * Getter valor mínim
     *
     * @return Valor mínim associat al node
     */
    public double getLower() {return lower;}


    /**
     * Getter valor màxim
     *
     * @return Valor màxim associat al node
     */
    public double getUpper() {
        return upper;
    }

    /**
     * Getter moviment
     *
     * @return Moviment de l'entrada de la taula
     */
    public Moviment getBestMove() {
        return bestMove;
    }
}
