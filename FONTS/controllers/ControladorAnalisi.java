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

    ;

    public ControladorAnalisi(String[] noms, boolean mtype1, boolean mtype2) {
        a = mtype1 ? new M1() : new M2();
        d = mtype2 ? new M1() : new M2();
        wins = new ArrayList<>();
        ep = new ArrayList<>();
        gp = GestioProblema.getInstance();
        for (String s: noms) probs.add(gp.getProblema(s));
    }

    public String[] getNoms(){
        String[] noms = new String[probs.size()];
        for (int i=0; i<probs.size();++i) noms[i] = probs.get(i).getNom();
        return noms;
    }

    //Es poden fer getters de dificultat, numJugades...

    public long[] getTimes(boolean atacant) {
        int n = ep.size();
        long[] times = new long[n];
        for (int i=0; i<n; ++i) {
            Color tema = probs.get(i).getTema();
            times[i] = ep.get(i).getTemps(atacant ? tema : tema.getNext());
        }
        return times;
    }

    public String[] getWinners() {
        String[] res = new String[wins.size()];
        for (int i=0; i<wins.size();++i) res[i] = wins.get(i) ? a.getNom() : d.getNom();
        return res;
    }

    public void jugaPartides() {
        for (Problema p: probs) {
            ControladorPartidaMvM cp = new ControladorPartidaMvM(p, a, d);
            int res = 0;
            while (res==0 || res==1)
                res = cp.executaMoviment();
            String w = cp.getNomGuanyador();
            wins.add(w.equals(a.getNom()));
            ep.add(cp.getEstadistiques());
        }
    }
}
