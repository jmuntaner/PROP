package controllers;

import domain.Problema;
import domain.Tauler;
import data.*;

import java.io.File;
import java.util.ArrayList;

public class ControladorProblema {
    private GestioProblema gp;
    private Problema p;

    public ControladorProblema() {
        gp = new GestioProblema();
        // Provisional
        Problema x = new Problema("Test problem 2");
        x.initProblema(2, "5rkr/pp2Rp2/1b1p1Pb1/3P2Q1/2n3P1/2p5/P4P2/4R1K1 w - - 1 0");
        gp.saveProblema(x);
    }

    public boolean afegeixProblema(Problema x) {
        return gp.saveProblema(x);
    }

    public Problema getProblema(String nom) {
        return gp.getProblema(nom);
    }

    public void eliminaProblema(String nom) {
        gp.delete(nom);
        p = null;
    }

    public void editaProblema(Problema nou) {
        gp.delete(nou.getNom());
        if (!gp.saveProblema(nou)) throw new RuntimeException("ControladorProblema - error edita");
    }

    public void selectProblema(String nom) {
        p = gp.getProblema(nom);
    }

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

    public String[] getNomsProblemes() {
        ArrayList<String> al = gp.getList();
        int n = al.size();
        String[] noms = new String[n];
        for (int i=0; i<n; ++i)
            noms[i] = al.get(i);
        return noms;
    }

    //public Problema getProblemaCarregat() {return p;}

    public String getNom() {return p.getNom();}

    public int getJugades() {return p.getNumJugades();}

    public int getDificultat() {return p.getDificultat();}

    public boolean carregaProblemes(File f) {
        return IOFens.readFenList(f);
    }

    public void exportaProblemes(File f) {
        IOFens.writeFenList(f);
    }


}
