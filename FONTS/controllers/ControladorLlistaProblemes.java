package controllers;

import domain.Problema;
import domain.Tauler;

public class ControladorLlistaProblemes {
    private ControladorPrincipal cp;
    private Problema p;

    /**
     * Creadora per defecte.
     *
     * @param cp Controlador Principal
     */
    ControladorLlistaProblemes(ControladorPrincipal cp) {
        this.cp = cp;
    }

    /**
     * Selecciona un problema de la llista.
     *
     * @param index Identificador del problema a seleccionar.
     */
    public void selectProblema(int index) {
        p = cp.getProblema(index);
    }

    /**
     * Obté la representació matricial del tauler del problema seleccionat.
     *
     * @return Matriu 8x8 de caràcters representant el tauler.
     */
    public char[][] getTauler() {
        Tauler t = p.getSituacioInicial();
        char[][] res = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                res[i][j] = t.getCasella(i, j);
            }
        }
        return res;
    }

    /**
     * Obté els noms dels problemes de la base de dades.
     *
     * @return Array amb els noms de tots els problemes presents a la base de dades.
     */
    public String[] getNomsProblemes() {
        int size = cp.getNumProblemes();
        String[] noms = new String[size];
        for (int i = 0; i < size; i++) {
            noms[i] = cp.getProblema(i).getNom();
        }
        return noms;
    }


    /**
     * Obté el nom del problema seleccionat.
     *
     * @return Nom del problema seleccionat.
     */
    public String getNom() {
        return p.getNom();
    }

    /**
     * Elimina el problema indicat de la base de dades de problemes.
     *
     * @param index Problema a eliminar.
     */
    public void eliminaProblema(int index) {
        cp.eliminaProblema(index);
        p = null; // Acaba amb les referències al problema per a que passi el GC a netejar, que ja li toca.
    }

    /**
     * Inicia una nova partida humà vs humà.
     *
     * @return Controlador de la nova partida.
     */
    public ControladorPartida iniciaPartidaHvH(String nomOp) {
        return cp.iniciaHvH(p, nomOp);
    }

    /**
     * Inicia una nova partida humà vs màquina.
     *
     * @return Controlador de la nova partida.
     */
    public ControladorPartida iniciaPartidaHvM(boolean mType, boolean ataca) {
        return cp.iniciaHvM(p, mType, ataca);
    }
}
