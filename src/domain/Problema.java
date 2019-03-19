package domain;

public class Problema {
    private String nom;
    private boolean tema;
    private int numJugades, dificultat;
    private Tauler situacioInicial;
    private RankingProblema ranking;

    // Constructor
    public Problema(String nom, boolean tema, Integer numJugades, Tauler situacioInicial) {
        this.nom = nom;
        this.tema = tema;
        this.numJugades = numJugades;
        this.situacioInicial = situacioInicial;
    }

    // Getters & setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getTema() {
        return tema;
    }

    public void setTema(boolean tema) {
        this.tema = tema;
    }

    public int getNumJugades() {
        return numJugades;
    }

    public void setNumJugades(int nj) {
        numJugades = nj;
    }

    public int getDificultat() {
        return dificultat;
    }

    public Tauler getSituacioInicial() {
        return situacioInicial;
    }

    public void setSituacioInicial(Tauler si){
        situacioInicial = si;
    }

    //TODO:
    // - Comprovar que nom no estigui repetit (controlador, cal accedir a persistència, abans o després)
    // - Potser una funció per canviar més d'una cosa
    // - Comprovar problema no repetit (tauler + tema [o invers] + jugades) (ídem que nom)
    // - Cal recalcular dificultat en canviar tema, jugades i/o tauler
    // - D'aquí fer una sola funció, o posar un bool com a paràmetre que indiqui si es modificaran més coses

    public RankingProblema getRanking() {
        return ranking;
    }
    //TODO: gestió ranking

    private void calculaDificultat() {}
    //TODO: implementar càlcul dificultat

    //TODO: funcio per comprovar si existeix solucio, privada que es cridi des de la creadora
    private boolean comprovaSolucio() {return false;}
}
