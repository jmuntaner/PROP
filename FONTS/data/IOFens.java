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
    public static void writeFenList(File f) {
        try {
            GestioProblema gp = GestioProblema.getInstance();
            ArrayList<String> al = gp.getList();
            FileWriter wr = new FileWriter(f);
            for (String s: al) {
                Problema p = gp.getProblema(s);
                wr.write(p.getNom());
                wr.write("_");
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
    private static boolean afegeixProblema(String prob) {
        String fen = null;
        int i = prob.indexOf('_');
        int j = prob.lastIndexOf('_');
        if (i>0 && j<prob.length()-1 && i < j) fen = prob.substring(j+1);
        else return false; //if (fen==null) return false;
        int numj = Integer.parseInt(prob.substring(i+1,j));
        String nom = prob.substring(0,i);
        Problema pp = new Problema(nom);
        if (!pp.initProblema(numj,fen)) {
            System.out.println("false");
            return false;
        }
        GestioProblema gp = GestioProblema.getInstance();
        gp.saveProblema(pp);
        return true;
    }

    /**
     * Llegeix una llista de problemes d'un fitxer .fendb i els guarda a la base de problemes
     * Si algun problema no s'ha pogut guardar, no intenta desar els següents
     * Format de cada entrada en el .fendb: nom_numJugades_fen\n
     * És important que hi hagi el \n, també en l'ultima línia
     *
     * @param f Fitxer d'on llegir els problemes
     * @return False si algun problema no s'ha pogut desar, true si s'han desat tots.
     */
    public static boolean readFenList(File f) {
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
                else if (rd!='\r') sb.append((char) rd); //Sistema anti-windows (no em peguis Alex)
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
