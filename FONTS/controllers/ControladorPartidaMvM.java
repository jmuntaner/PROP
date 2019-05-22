package controllers;

import domain.*;

public class ControladorPartidaMvM extends ControladorPartida{
    private Maquina atacant, defensor;
    boolean torn;

    ControladorPartidaMvM(Problema problema, Maquina m1, Maquina m2) {
        super(problema);
        this.atacant = m1;
        this.defensor = m2;
        setNoms(m1.getNom(),m2.getNom());
        torn = true;
    }

    @Override
    void actualitzaRanking(Ranking<PuntuacioProblema> ranking, PuntuacioProblema punts) {}

    @Override
    public boolean esTornMaquina() {return true;}

    @Override
    void actualitzaTorn(){
        torn = !torn;
    }

    @Override
    public int executaMoviment() {
        Maquina act = torn ? atacant : defensor;
        Moviment m = act.calcularMoviment(getProfunditat(), getTauler(), getColorTorn(), getColorPrincipal());
        return mou(m);
    }

    @Override
    public boolean esJugadorHuma(String nom) {return false;}

}
