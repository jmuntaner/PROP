package domain;

public class TranspositionTableEntry {
    private double lower;
    private double upper;
    private Moviment bestMove;

    public TranspositionTableEntry(double lower, double upper, Moviment best) {
        this.lower = lower;
        this.upper = upper;
        this.bestMove = best;
    }

    public double getLower() {return lower;}


    public double getUpper() {
        return upper;
    }

    public Moviment getBestMove() {
        return bestMove;
    }
}
