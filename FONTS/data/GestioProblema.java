package data;

import domain.Problema;

public class GestioProblema extends  GestioBases {
    private static final String rp = "data/baseProbs/"; //relative path
    private static final String ext = ".prob";
    //potser canviar '/' per File.pathSeparator

    @Override
    public String getPath() {return rp;}

    @Override
    public String getExt() {return ext;}

    /**
     * Desa un problema a la base de problemes
     *
     * @param p Problema a desar
     */
    public void saveProblema(Problema p) {
        // Ara mateix si el problema esta repetit, el sobrescriu
        // Repetit = mateix nom
        writeObject(p.getNom(),p);
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