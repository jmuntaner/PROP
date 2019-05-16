package controllers;

import domain.Problema;
import domain.PuntuacioProblema;
import domain.Ranking;
import domain.Usuari;

public class ControladorPartidaHvH extends ControladorPartida {
    private final Usuari usuari;


    /**
     * Crea una partida Humà vs Humà
     *
     * @param problema El problema en el que es basarà la partida.
     * @param usuari   L'usuari que juga el problema (ataca).
     * @param oponent  Nom de l'oponent.
     */
    public ControladorPartidaHvH(Problema problema, Usuari usuari, String oponent) {
        super(problema);
        this.usuari = usuari;
        setNoms(usuari.getNom(), oponent);
    }


    @Override
    void actualitzaRanking(Ranking<PuntuacioProblema> ranking, PuntuacioProblema punts) {
        ranking.afegeixPuntuacio(usuari, punts);
    }


    @Override
    public boolean esTornMaquina() {
        return false;
    }

    @Override
    void actualitzaTorn() {
        // No cal fer res
    }

    @Override
    public int executaMoviment() {
        throw new RuntimeException("Un jugador humà no pot generar moviments.");
    }
}
