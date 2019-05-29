package data;

import java.io.*;
import java.util.ArrayList;

public abstract class GestioBases {

    /**
     * Constructora de GestioBases
     * Si no existeix el directori de la base, el crea.
     *
     * @param path Path a la base
     */
    GestioBases(String path) {
        new File(path).mkdirs();
    }

    /**
     * Llegeix un element de la base corresponent
     * El nom ha de coincidir amb el nom del fitxer
     *
     * @param nom Nom de l'element a llegir
     * @return Objecte llegit
     */
    Object readElement(String nom) {
        try {
            FileInputStream f = new FileInputStream(getPath() + nom + getExt());
            ObjectInputStream o = new ObjectInputStream(f);
            Object x = o.readObject();
            o.close();
            f.close();
            return x;
        }
        catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException("Base - read: " + e.getMessage());
        }
    }

    /**
     * Escriu un element a la base corresponent
     * El nom coincideix amb el nom del fitxer
     *
     * @param nom Nom de l'element e escriure
     * @param x Element a escriure
     * @return True si s'ha desat correctament, false si ja existia un fitxer amb aquest nom
     */
    boolean writeObject(String nom, Object x) {
        try {
            ArrayList<String> al = getList();
            for (String s: al) if (nom.equals(s)) return false;
            FileOutputStream f = new FileOutputStream(getPath() + nom + getExt());
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(x);
            o.close();
            f.close();
            return true;
        }
        catch(IOException e) {
            throw new RuntimeException("Base - write: " + e.getMessage());
        }
    }

    /**
     * Getter path relatiu
     *
     * @return Path relatiu de la base
     */
    public abstract String getPath();

    /**
     * Getter extensio
     *
     * @return Extensio associada als fitxers de la base
     */
    public abstract String getExt();

    /**
     * Llista els elements que hi ha a la base
     *
     * @return Llista amb els noms dels elements
     */
    public ArrayList<String> getList() {
        File f = new File(getPath());
        File[] lf = f.listFiles();
        ArrayList<String> al = new ArrayList<>();
        for (File x: lf) {
            String s = x.getName();
            int n = s.length()-getExt().length();
            al.add(s.substring(0,n));
        }
        return al;
    }

    /**
     * Esborra un element de la base
     *
     * @param nom Nom de l'element a esborrar
     */
    public void delete(String nom) {
        File f = new File(getPath() + nom + getExt());
        if (!f.delete()) throw new RuntimeException("Base - delete: couldn't delete file");
    }

}

