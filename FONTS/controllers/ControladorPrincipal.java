package controllers;

import domain.M1;
import domain.M2;
import domain.Problema;
import domain.Usuari;

import java.util.ArrayList;

public class ControladorPrincipal {
    private ArrayList<Problema> BDproblemes;
    private ControladorEditor ce;
    private ControladorLlistaProblemes cl;
    private Usuari usuari;

    /**
     * Creadora per defecte.
     */
    public ControladorPrincipal() {
        BDproblemes = new ArrayList<>();
        // TODO: Borrar
        Problema p = new Problema("Test problem 1");
        p.initProblema(2, "6k1/pp4p1/2p5/2bp4/8/P5Pb/1P3rrP/2BRRN1K b - - 0 1");
        BDproblemes.add(p);
        p = new Problema("Test problem 2");
        p.initProblema(2, "5rkr/pp2Rp2/1b1p1Pb1/3P2Q1/2n3P1/2p5/P4P2/4R1K1 w - - 1 0");
        BDproblemes.add(p);

        ce = new ControladorEditor(this);
        cl = new ControladorLlistaProblemes(this);
        usuari = new Usuari(0, "Sauron", "TheDarkLord");
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
     * Afegeix un problema a la base de dades de problemes.
     *
     * @param p Problema a afegir.
     */
    void afegeixProblema(Problema p) {
        BDproblemes.add(p);
    }

    /**
     * Obté el número de problemes.
     *
     * @return Número de problemes presents a la base de dades.
     */
    public int getNumProblemes() {
        return BDproblemes.size();
    }


    /**
     * Obté un problema de la base de dades.
     *
     * @param index Identificador del problema a obtenir.
     * @return Problema solicitat.
     */
    Problema getProblema(int index) {
        return BDproblemes.get(index);
    }

    /**
     * Elimina el problema indicat de la base de dades de problemes.
     *
     * @param index Problema a eliminar.
     */
    void eliminaProblema(int index) {
        BDproblemes.remove(index);
    }

    /**
     * Actualitza la les dades d'un problema de la base de dades.
     *
     * @param index Identificador del problema a actualitzar.
     * @param nou   Noves dades del problema.
     */
    void editaProblema(int index, Problema nou) {
        BDproblemes.set(index, nou);
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
