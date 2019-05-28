package controllers;

import domain.M1;
import domain.M2;
import domain.Problema;
import domain.Usuari;

public class ControladorPrincipal {
    private ControladorEditor ce;
    private ControladorLlistaProblemes cl;
    private ControladorUsuari cu;
    private Usuari usuari;

    /**
     * Creadora per defecte.
     */
    public ControladorPrincipal() {
        ce = new ControladorEditor();
        cl = new ControladorLlistaProblemes(this);
        cu = new ControladorUsuari(this);
        usuari = ControladorUsuari.getGuest();
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
     * Obté una nova instància del controlador d'usuari.
     *
     * @return Controlador d'usuari.
     */
    public ControladorUsuari getControladorUsuari() {
        return cu;
    }

    /**
     * Setter de l'usuari
     */
    void setUsuari(Usuari u) {
        usuari = u;
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

    /**
     * Inicia una partida humà vs màquina
     *
     * @param p Problema a jugar
     * @param mType Tipus de maquina (true = M2, false = M1)
     * @param ataca Indica si la maquina ataca (true) o defensa (false)
     * @return Controlador per a la nova partida
     */
    ControladorPartidaHvM iniciaHvM(Problema p, boolean mType, boolean ataca) {
        return new ControladorPartidaHvM(p, usuari, mType ? new M2() : new M1(), ataca);
    }
}
