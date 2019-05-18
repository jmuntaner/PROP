package data;

import domain.Problema;

import java.io.*;
import java.util.ArrayList;

public class GestioProblema {
    private static final String rp = "data/baseProbs/"; //relative path
    private static final String ext = ".prob";
    //potser canviar '/' per File.pathSeparator

    /**
     * Llista els problemes que hi ha a la base de problemes
     *
     * @return Llista amb els noms dels problemes
     */
    public ArrayList<String> getList() {
        File f = new File(rp); //Modificar per treure la / del final?
        File[] lf = f.listFiles();
        ArrayList<String> al = new ArrayList<>();
        for (File x: lf) al.add(x.getName());
        return al;
    }

    /**
     * Esborra un problema de la base de problemes
     *
     * @param nom Nom del problema a esborrar
     */
    public void deleteProblema(String nom) {
        File f = new File(rp + nom + ext);
        if (!f.delete()) throw new RuntimeException("Problema - delete: couldn't delete file");
    }

    /**
     * Desa un problema a la base de problemes
     *
     * @param p Problema a desar
     */
    public void saveProblema(Problema p) {
        // Ara mateix si el problema esta repetit, el sobrescriu
        // Repetit = mateix nom
        try {
            FileOutputStream f = new FileOutputStream(rp + p.getNom() + ext);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(p);
            o.close();
            f.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Problema - save: " + e.getMessage());
        }
    }

    /**
     * Llegeix un problema de la base de problemes
     *
     * @param nom Nom del problema a llegir
     * @return Problema llegit
     */
    public Problema getProblema(String nom) {
        try {
            FileInputStream f = new FileInputStream(rp + nom + ext);
            ObjectInputStream o = new ObjectInputStream(f);
            Problema p = (Problema) o.readObject();
            o.close();
            f.close();
            return p;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Problema - get: " + e.getMessage());
        }
    }
}