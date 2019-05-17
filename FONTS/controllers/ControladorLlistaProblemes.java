package controllers;

import domain.Problema;
import domain.Tauler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Afegeix els fen d'un arxiu *.fendb a la base de dades.
     *
     * @param f Arxiu *.fendb amb problemes.
     */
    public boolean carregaProblemes(File f) {
        try {
            FileReader fr = new FileReader(f);
            List<String> problemes = new ArrayList<>();
            int rd;
            StringBuilder sb = new StringBuilder();
            while ((rd = fr.read()) != -1) {
                if (rd == '\n') {
                    problemes.add(sb.toString());
                    sb = new StringBuilder();
                } else sb.append((char) rd);
            }
            for (String fen : problemes) {
                if (!afegeixProblema(fen)) return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Afageix un problema d'arxiu a la base de dades de problemes.
     *
     * @param prob String del problema a carregar
     * @return Vertader si el problema ha estat afegit correctament
     */
    private boolean afegeixProblema(String prob) {
        String fen = null;
        int i = prob.indexOf('_');
        if (i > 0 && i < prob.length() - 1) {
            fen = prob.substring(i + 1);
        }
        if (fen == null) return false;
        System.out.printf("ppp: '%s'\n", prob.substring(0, i));
        int numj = Integer.parseInt(prob.substring(0, i));
        Problema pp = new Problema(fen);
        if (!pp.initProblema(numj, fen)) return false;
        cp.afegeixProblema(pp);
        return true;
    }

    /**
     * Exporta la base de dades a un arxiu *.fendb.
     *
     * @param f Arxiu *.fendb a crear.
     */
    public void exportaProblemes(File f) {
        try {
            FileWriter wr = new FileWriter(f);
            int t_size = cp.getNumProblemes();
            for (int i = 0; i < t_size; i++) {
                Problema tmp = cp.getProblema(i);
                wr.write(Integer.toString(tmp.getNumJugades()));
                wr.write("_");
                wr.write(tmp.getFen());
                wr.write('\n');
            }
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
