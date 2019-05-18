package data;

import domain.Problema;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOFens {
    /*
    Format del fitxer que llegeix:
        - Un problema per línia
        - Cada línia: numJugades_FEN
     */

    /**
     * Exporta els problemes de la base de problemes a un arxiu
     *
     * @param f Arxiu on exportar els problemes
     */
    public void writeFenList(File f) {
        try {
            GestioProblema gp = new GestioProblema();
            ArrayList<String> al = gp.getList();
            FileWriter wr = new FileWriter(f);
            for (String s: al) {
                Problema p = gp.getProblema(s);
                wr.write(Integer.toString(p.getNumJugades()));
                wr.write("_");
                wr.write(p.getFen());
                wr.write("\n");
            }
            wr.close();
        }
        catch (IOException e) {
            throw new RuntimeException("IOFen - write: " + e.getMessage());
        }
    }


    /**
     * Afegeix un problema d'arxiu a la base de problemes
     *
     * @param prob String del problema a carregar
     * @return True si el problema ha estat afegit correctament, false si no
     */
    private boolean afegeixProblema(String prob) {
        String fen = null;
        int i = prob.indexOf('_');
        if (i>0 && i<prob.length()-1) fen = prob.substring(i+1);
        else return false; //if (fen==null) return false;
        int numj = Integer.parseInt(prob.substring(0,i));
        Problema pp = new Problema(prob);
        if (!pp.initProblema(numj,fen)) return false;

        GestioProblema gp = new GestioProblema();
        gp.saveProblema(pp);
        return true;
    }

    /**
     * Llegeix una llista de problemes d'un fitxer .fendb i els guarda a la base de problemes
     * Si algun problema no s'ha pogut guardar, no intenta desar els següents
     *
     * @param f Fitxer d'on llegir els problemes
     * @return False si algun problema no s'ha pogut desar, true si s'han desat tots.
     */
    public boolean readFenList(File f) {
        try {
            FileReader fr = new FileReader(f);
            ArrayList<String> probs = new ArrayList<>();
            int rd;
            StringBuilder sb = new StringBuilder();
            while ((rd=fr.read()) != -1) {
                if (rd=='\n') {
                    probs.add(sb.toString());
                    sb = new StringBuilder();
                }
                else sb.append((char) rd);
            }
            for (String fen: probs)
                if (!afegeixProblema(fen)) return false;
            return true;
        }
        catch(IOException e) {
            throw new RuntimeException("IOFens - read: " + e.getMessage());
        }
    }
}
