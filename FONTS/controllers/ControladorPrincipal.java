package controllers;

import domain.M1;
import domain.M2;
import domain.Problema;
import domain.Usuari;

public class ControladorPrincipal {
    private ControladorEditor ce;
    private ControladorLlistaProblemes cl;
    private Usuari usuari;

    /**
     * Creadora per defecte.
     */
    public ControladorPrincipal() {
        ce = new ControladorEditor();
        cl = new ControladorLlistaProblemes(this);
        usuari = new Usuari(0, "Alex", "TheDarkLord");
    }

    /**
     * Obté una nova instància del controlador d'editor.
     *
     * @return Controlador d'editor.
     */
    public ControladorEditor getControladorEditor() {
        return ce;
    }

    /**
     * Obté una nova instància del controlador de llista de problemes.
     *
     * @return Controlador de llista de problemes.
     */
    public ControladorLlistaProblemes getControladorLlistaProblemes() {
        return cl;
    }

    /**
     * Inicia una partida humà vs humà
     *
     * @param p          Problema a jugar
     * @param nomOponent Nom de l'oponent
     * @return Controlador per a la nova partida.
     */
    ControladorPartidaHvH iniciaHvH(Problema p, String nomOponent) {
        return new ControladorPartidaHvH(p, usuari, nomOponent);
    }

    ControladorPartidaHvM iniciaHvM(Problema p, boolean mType, boolean ataca) {
        return new ControladorPartidaHvM(p, usuari, mType ? new M2() : new M1(), ataca);
    }
}
