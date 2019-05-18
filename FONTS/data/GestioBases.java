package data;

import java.io.*;
import java.util.ArrayList;

public abstract class GestioBases {
    /**
     * Llegeix un element de la base de problemes
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

    void writeObject(String nom, Object x) {
        try {
            FileOutputStream f = new FileOutputStream(getPath() + nom + getExt());
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(x);
            o.close();
            f.close();
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
     * Llista els problemes que hi ha a la base de problemes
     *
     * @return Llista amb els noms dels problemes
     */
    public ArrayList<String> getList() {
        File f = new File(getPath()); //Modificar per treure la / del final?
        File[] lf = f.listFiles();
        ArrayList<String> al = new ArrayList<>();
        for (File x: lf) al.add(x.getName());
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
