package controllers;

import data.GestioProblema;
import data.IOFens;
import domain.Problema;
import domain.Tauler;
import utils.Pair;

import java.io.File;
import java.util.ArrayList;

public class ControladorLlistaProblemes {
    private ControladorPrincipal cp;
    private Problema p;

    private GestioProblema gp;

    /**
     * Creadora per defecte.
     *
     * @param cp Controlador Principal
     */
    ControladorLlistaProblemes(ControladorPrincipal cp) {
        this.cp = cp;
        gp = GestioProblema.getInstance();
    }

    /**
     * Selecciona un problema de la llista.
     *
     * @param nom Identificador del problema a seleccionar.
     */
    public void selectProblema(String nom) {
        p = gp.getProblema(nom);
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
        ArrayList<String> al = gp.getList();
        int n = al.size();
        String[] noms = new String[n];
        for (int i = 0; i < n; ++i)
            noms[i] = al.get(i);
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
     * Obté el número de jugades del problema seleccionat.
     *
     * @return Número de jugades del problema seleccionat.
     */
    public int getJugades() {
        return p.getNumJugades();
    }

    /**
     * Obté la dificultat del problema seleccionat.
     *
     * @return Dificultat del problema seleccionat.
     */
    public int getDificultat() {
        return p.getDificultat();
    }

    /**
     * Elimina el problema indicat de la base de dades de problemes.
     *
     * @param nom Problema a eliminar.
     */
    public void eliminaProblema(String nom) {
        gp.delete(nom);
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

    /**
     * Afegeix els fen d'un arxiu *.fendb a la base de dades.
     *
     * @param f Arxiu *.fendb amb problemes.
     */
    public boolean carregaProblemes(File f) {
        return IOFens.readFenList(f);
    }


    /**
     * Exporta la base de dades a un arxiu *.fendb.
     *
     * @param f Arxiu *.fendb a crear.
     */
    public void exportaProblemes(File f) {
        IOFens.writeFenList(f);
    }

    /**
     * Obté el ranking associat al problema.
     *
     * @return Llista amb el ranking dels 10 millors jugadors.
     */
    public ArrayList<Pair<String, String>> getRanking() {
        return p.getRanking().getLlistaRanking(10);
    }
}
