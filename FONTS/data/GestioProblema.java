package data;

import com.sun.xml.internal.ws.addressing.ProblemAction;
import domain.FenTranslator;
import domain.Problema;

import java.io.*;
import java.util.ArrayList;

public class GestioProblema {
    /*
    Justificació:
        - Cal llegir tot el fitxer per comprovar que no hi hagi repeticions
        - Cal llegir i reescriure tot el fitxer per esborrar un problema
        - Cal llegir tot el fitxer fins a una certa posicio per llegir un problema concret
    Per aquest motiu, i com que no tenim grans quantitats de dades, es mes senzill guardar un arraylist,
        en comptes de guardar els problemes per separat.

    ATENCIO
    El fitxer no pot estar buit, i ha de contenir un arrayList.
    Si es trenca, cal esborrar-lo i deixar que el programa el torni a crear.

    USAGE
    Si el fitxer no existeix, es pot cridar getSize i saveProblema nomes.
     */

    /**
     * Llegeix la base de problemes
     *
     * @return Llista amb tots els problemes de la base (o null si el fitxer no existeix)
     */
    private ArrayList<Problema> readBase() {
        try {
            FileInputStream f = new FileInputStream("db_problema.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            ArrayList<Problema> al = (ArrayList<Problema>) o.readObject();
            o.close();
            f.close();
            return al;
        }
        catch (FileNotFoundException e) {
            return null;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Problema - read: " + e.getMessage());
        }
    }

    /**
     * Sobrescriu la base de problemes amb una llista de probleme
     *
     * @param al Llista de problemes a guardar
     */
    private void writeBase(ArrayList<Problema> al) {
        try {
            FileOutputStream f = new FileOutputStream("db_problema.txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(al);
            o.close();
            f.close();
        }
        catch(IOException e) {
            throw new RuntimeException("Problema - write: " + e.getMessage());
        }
    }

    /**
     * Comprova si un problema està a la base de problemes.
     * Si no hi és, l'afegeix. Si hi és, pot actualitzar-lo o cancel·lar l'operació.
     *
     * @param p Problema a guardar
     * @param b Indica el comportament si el problema ja hi es (true = update, false = cancel)
     */
    //TODO: potser interessa que retorni un boolea segons si s'ha guardat o no
    public void saveProblema(Problema p, boolean b) {
        ArrayList<Problema> al = readBase();
        if (al==null) al = new ArrayList<Problema>();
        else {
            String fen = FenTranslator.generaFen(p.getSituacioInicial(),p.getTema());
            int nj = p.getNumJugades();
            for (Problema u: al)
                if (nj==u.getNumJugades() && fen.equals(FenTranslator.generaFen(u.getSituacioInicial(),u.getTema())))
                    return;
        }
        al.add(p);
        writeBase(al);
    }

    /**
     * Llegeix un problema de la base de problemes
     *
     * @param index Posicio del problema en la base de problemes
     * @return Problema desitjat
     */
    public Problema getProblema(int index) {
        ArrayList<Problema> al = readBase();
        return al.get(index);
    }

    public int getSize() {
        ArrayList<Problema> al = readBase();
        return al==null ? 0 : al.size();
    }

    /**
     * Esborra un problema de la base de problemes
     *
     * @param index Posicio del problema en la base de problemes
     */
    public void deleteProblema(int index){
        ArrayList<Problema> al = readBase();
        al.remove(index);
        writeBase(al);
    }
}
