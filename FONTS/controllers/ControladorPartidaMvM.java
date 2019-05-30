package controllers;

import domain.Maquina;
import domain.Moviment;
import domain.Problema;
import domain.Usuari;

public class ControladorPartidaMvM extends ControladorPartida{
    private Maquina atacant, defensor;
    private boolean torn;

    /**
     * Crea una partida Màquina vs Màquina
     *
     * @param problema El problema en que es basarà la partida
     * @param m1 Màquina que ataca
     * @param m2 Màquina que defensa
     */
    ControladorPartidaMvM(Problema problema, Maquina m1, Maquina m2) {
        super(problema);
        this.atacant = m1;
        this.defensor = m2;
        setNoms(m1.getNom(),m2.getNom());
        torn = true;
    }

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

    @Override
    Usuari getUsuari() {
        return null;
    }
}
