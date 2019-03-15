package domain;

public class Problema {
    private String nom;
    private boolean tema;
    private int numJugades, dificultat;
    private Tauler situacioInicial;
    private RankingProblema ranking;

    public String getNom() {
        return nom;
    }

    public boolean isTema() {
        return tema;
    }

    public int getNumJugades() {
        return numJugades;
    }


    public Tauler getSituacioInicial() {
        return situacioInicial;
    }

    public int getDificultat() {
        return dificultat;
    }

    private void calculaDificultat() {
    }

    public RankingProblema getRanking() {
        return ranking;
    }
}
