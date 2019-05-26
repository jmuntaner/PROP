package controllers;

import data.GestioProblema;
import domain.*;

import java.util.ArrayList;

public class ControladorAnalisi {
    private Maquina a, d;
    private ArrayList<Problema> probs;
    private ArrayList<Boolean> wins;
    private ArrayList<EstadistiquesPartida> ep;
    private GestioProblema gp;
    private int partidaAct, winsA, winsD;


    /**
     * Constructora ControladorAnalisi
     *
     * @param noms Noms dels problemes a analitzar
     */
    public ControladorAnalisi(String[] noms) {
        a = new M1();
        d = new M1();
        wins = new ArrayList<>();
        ep = new ArrayList<>();
        probs = new ArrayList<>();
        gp = GestioProblema.getInstance();
        winsA = 0;
        winsD = 0;
        for (String s : noms) probs.add(gp.getProblema(s));
        partidaAct = 0;
    }

    /**
     * Getter noms dels problemes
     *
     * @return Noms dels problemes a avaluar
     */
    public String[] getNoms() {
        String[] noms = new String[probs.size()];
        for (int i = 0; i < probs.size(); ++i) noms[i] = probs.get(i).getNom();
        return noms;
    }

    /**
     * Getter nombre de problemes
     *
     * @return Nombre de problemes a avaluar
     */
    public int getNumProbs() {
        return probs.size();
    }

    /**
     * Getter número de problema actual
     *
     * @return Posició del problema actual en la llista de problemes a avaluar
     */
    public int getNumAct() {
        return partidaAct + 1;
    }


    /**
     * Getter temps de jocs
     *
     * @param atacant Indica el jugador del qual es volen saber els temps (true = atacant, false = defensor)
     * @return Temps de jocs del jugador idnicat en cada partida jugada
     */
    public long[] getTimes(boolean atacant) {
        int n = ep.size();
        long[] times = new long[n];
        for (int i = 0; i < n; ++i) {
            Color tema = probs.get(i).getTema();
            times[i] = ep.get(i).getTemps(atacant ? tema : tema.getNext());
        }
        return times;
    }

    /**
     * Getter guanyadors
     *
     * @return Nom del jugador guanyador de cada partida jugada
     */
    public String[] getWinners() {
        String[] res = new String[wins.size()];
        for (int i = 0; i < wins.size(); ++i) {
            String elvira = wins.get(i) ? ("A (" + a.getNom() + ")") : ("D (" + d.getNom() + ")");
            res[i] = elvira;
        }
        return res;
    }

    /**
     * Getter nombre de partides guanyades
     *
     * @param maquina Indica el jugador del qual es volen saber les partides guanyades (true = atacant, false = defensor)
     * @return Nombre de partides guanyades pel jugador indicat
     */
    public int getWinCount(boolean maquina) {
        if (maquina) return winsD;
        else return winsA;
    }

    /**
     * Getter nom problema següent
     *
     * @return Nom del següent problema a avaluar
     */
    public String getNextNom() {
        return probs.get(partidaAct).getNom();
    }

    /**
     * Getter nombre de jugades problema següent
     *
     * @return Nombre de jugades del següent problema a avaluar
     */
    public int getNextJugades() {
        return probs.get(partidaAct).getNumJugades();
    }

    /**
     * Juga la següent partida, si encara queden problemes per jugar
     *
     * @return False si no queden problemes a avaluar, true en cas contrari.
     */
    public boolean jugaPartida() {
        if (partidaAct >= probs.size()) return false;
        jugaPartidaAux(probs.get(partidaAct));
        partidaAct++;
        return true;
    }

    /**
     * Funcio auxiliar per jugar la següent partida
     *
     * @param p Problema amb què es juga la partida
     */
    private void jugaPartidaAux(Problema p) {
        ControladorPartidaMvM cp = new ControladorPartidaMvM(p, a, d);
        int res = 0;
        while (res == 0 || res == 1)
            res = cp.executaMoviment();
        String w = cp.getNomGuanyador();
        boolean winner = w.equals(a.getNom());
        if (winner) winsA++;
        else winsD++;
        wins.add(winner);
        ep.add(cp.getEstadistiques());
    }

    /**
     * Getter nom màquina
     *
     * @param maquina Màquina de la qual es vol saber el nom (true = atacant, false = defensora)
     * @return Nom de la màquina indicada
     */
    public String getNomM(boolean maquina) {
        if (maquina) return d.getNom();
        else return a.getNom();
    }

    /**
     * Reset dels resultats de l'avaluació
     */
    public void resetAll() {
        wins = new ArrayList<>();
        ep = new ArrayList<>();
        winsA = 0;
        winsD = 0;
        partidaAct = 0;
    }

    /**
     * Associa un tipus de maquina a cada jugador
     *
     * @param jugador Jugador que es vol assignar (0 = atacant, altre = defensor)
     * @param maquina Màquina que es vol associar al jugador (0 = M1, altre = M2)
     */
    public void setMaquina(int jugador, int maquina) {
        if (jugador == 0)
            a = maquina == 0 ? new M1() : new M2();
        else
            d = maquina == 0 ? new M1() : new M2();
    }
}
