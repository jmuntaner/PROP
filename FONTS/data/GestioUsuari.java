package data;

import java.util.ArrayList;

import domain.Usuari;

public class GestioUsuari extends GestioBases {
    private static final String rp = "data/baseUsuaris/";
    private static final String ext = ".user";

    @Override
    public String getPath() {return rp;}

    @Override
    public String getExt() {return ext;}

    /**
     * Desa un usuari a la base d'usuaris
     *
     * @param u Usuari a desar
     * @return True si s'ha desat l'usuari, false si ja existia (repetit)
     */
    public boolean saveUsuari(Usuari u) {
        ArrayList<String> al = getList();
        String nom = u.getNom();
        for (String s: al) if (nom.equals(s)) return false;
        writeObject(u.getNom(), u);
        return true;
    }

    /**
     * Llegeix un usuari de la base de problemes
     *
     * @param nom Nom de l'usuari a llegir
     * @return Usuari llegit
     */
    public Usuari getUsuari(String nom) {
        return (Usuari) readElement(nom);
    }
}