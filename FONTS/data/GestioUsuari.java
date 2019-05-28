package data;

import domain.Usuari;

public class GestioUsuari extends GestioBases {
    private static final String rp = "bases/baseUsuaris/";
    private static final String ext = ".user";

    private static GestioUsuari instance = new GestioUsuari();

    /**
     * Constructora de GestioUsuari
     */
    private GestioUsuari() {
        super(rp);
    }

    /**
     * Getter instància única de GestioUsuari
     *
     * @return Instància de GestioUsuari
     */
    public static GestioUsuari getInstance() {
        return instance;
    }

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
        return writeObject(u.getNom(), u);
    }

    /**
     * Llegeix un usuari de la base d'usuaris
     *
     * @param nom Nom de l'usuari a llegir
     * @return Usuari llegit
     */
    public Usuari getUsuari(String nom) {
        return (Usuari) readElement(nom);
    }
}