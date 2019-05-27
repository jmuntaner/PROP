package data;

import domain.Problema;

public class GestioProblema extends  GestioBases {
    private static final String rp = "data/baseProbs/"; //relative path
    private static final String ext = ".prob";

    private static GestioProblema instance = new GestioProblema();

    /**
     * Constructora de GestioProblema
     */
    private GestioProblema() {
        super();
    }

    /**
     * Getter instància única de GestioProblema
     *
     * @return instància de GestióProblema
     */
    public static GestioProblema getInstance() {
        return instance;
    }

    @Override
    public String getPath() {return rp;}

    @Override
    public String getExt() {return ext;}

    /**
     * Desa un problema a la base de problemes
     *
     * @param p Problema a desar
     * @return True si s'ha desat el problema, false si ja existia (nom repetit)
     */
    public boolean saveProblema(Problema p) {
        return writeObject(p.getNom(),p);
    }

    /**
     * Llegeix un problema de la base de problemes
     *
     * @param nom Nom del problema a llegir
     * @return Problema llegit
     */
    public Problema getProblema(String nom) {
        return (Problema) readElement(nom);
    }
}