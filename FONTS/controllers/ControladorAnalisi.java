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


    public ControladorAnalisi(String[] noms, boolean mtype1, boolean mtype2) {
        a = mtype1 ? new M1() : new M2();
        d = mtype2 ? new M1() : new M2();
        wins = new ArrayList<>();
        ep = new ArrayList<>();
        probs = new ArrayList<>();
        gp = GestioProblema.getInstance();
        winsA = 0;
        winsD = 0;
        for (String s : noms) probs.add(gp.getProblema(s));
        partidaAct = 0;
    }

    public String[] getNoms() {
        String[] noms = new String[probs.size()];
        for (int i = 0; i < probs.size(); ++i) noms[i] = probs.get(i).getNom();
        return noms;
    }

    public int getNumProbs() {
        return probs.size();
    }

    public int getNumAct() {
        return partidaAct + 1;
    }

    //Es poden fer getters de dificultat, numJugades...

    public long[] getTimes(boolean atacant) {
        int n = ep.size();
        long[] times = new long[n];
        for (int i = 0; i < n; ++i) {
            Color tema = probs.get(i).getTema();
            times[i] = ep.get(i).getTemps(atacant ? tema : tema.getNext());
        }
        return times;
    }

    public String[] getWinners() {
        String[] res = new String[wins.size()];
        for (int i = 0; i < wins.size(); ++i)
            res[i] = wins.get(i) ? ("A (" + a.getNom() + ")") : ("D (" + d.getNom() + ")");
        return res;
    }

    public int getWinCount(boolean maquina) {
        if (maquina) return winsD;
        else return winsA;
    }

    public String getNextNom() {
        return probs.get(partidaAct).getNom();
    }

    public int getNextJugades() {
        return probs.get(partidaAct).getNumJugades();
    }

    public boolean jugaPartida() {
        if (partidaAct >= probs.size()) return false;
        jugaPartida(probs.get(partidaAct));
        partidaAct++;
        return true;
    }

    private void jugaPartida(Problema p) {
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

    public String getNomM(boolean maquina) {
        if (maquina) return d.getNom();
        else return a.getNom();
    }

    public void resetAll() {
        wins = new ArrayList<>();
        ep = new ArrayList<>();
        winsA = 0;
        winsD = 0;
        partidaAct = 0;
    }
}
