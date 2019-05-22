package controllers;

import domain.*;

public class ControladorPartidaHvM extends ControladorPartida {
    private Usuari usuari;
    private boolean tornMaquina;
    private Maquina maquina;

    /**
     * Crea una partida Humà vs Màquina.
     *
     * @param problema El problema en que es basarà la partida.
     * @param usuari   Usuari que juga la partida.
     * @param maquina  Màquina contra la que jugar.
     * @param ataca    Indica si la màquina ataca o defensa.
     */
    ControladorPartidaHvM(Problema problema, Usuari usuari, Maquina maquina, boolean ataca) {
        super(problema);
        this.usuari = usuari;
        if (ataca) {
            setNoms(maquina.getNom(), usuari.getNom());
        } else {
            setNoms(usuari.getNom(), maquina.getNom());
        }
        this.maquina = maquina;
        tornMaquina = ataca;
    }

    @Override
    void actualitzaRanking(Ranking<PuntuacioProblema> ranking, PuntuacioProblema punts) {
        ranking.afegeixPuntuacio(usuari, punts);
    }


    @Override
    public boolean esTornMaquina() {
        return tornMaquina;
    }

    @Override
    void actualitzaTorn() {
        tornMaquina = !tornMaquina;
    }

    @Override
    public int executaMoviment() {
        if (!tornMaquina) throw new RuntimeException("Un jugador humà no pot generar moviments.");

        Moviment m = maquina.calcularMoviment(getProfunditat(), getTauler(), getColorTorn(), getColorPrincipal());
        return mou(m);
    }

    @Override
    public boolean esJugadorHuma(String nom){return nom.equals(usuari.getNom());}
}
