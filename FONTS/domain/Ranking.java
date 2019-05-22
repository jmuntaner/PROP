package domain;

import utils.Pair;

import java.io.Serializable;
import java.util.ArrayList;

public class Ranking<T extends Puntuacio<T>> implements Serializable {
    private final ArrayList<Pair<String, T>> ranking;


    /**
     * Creadora de ranking
     */
    public Ranking() {
        ranking = new ArrayList<>();
    }


    /**
     * Funció de comparació d'enters
     *
     * @param a Primer enter a comparar
     * @param b Segon enter a comparar
     * @return True si el primer és més petit que el segon, false en cas contrari
     */
    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Permet afegir una nova puntuació al ranking.
     * Afegeix la puntuació si l'usuari no en té cap, o si és millor que la que té.
     *
     * @param usuari Usuari que ha fet la puntuació
     * @param punts  Puntuació que ha tret
     */
    public void afegeixPuntuacio(Usuari usuari, T punts) {
        String nom = usuari.getNom();
        //Busquem si un usuari ja te una puntuació millor
        Pair<String, T> trobat = null;
        for (Pair<String, T> row : ranking) {
            if (row.first().equals(usuari.getNom())) {
                trobat = row;
                break;
            }
        }
        if (trobat != null) {
            if (trobat.second().esMillor(punts)) return;
            ranking.remove(trobat);
        }
        // Insertem la nova puntuació on toca
        for (int i = 0; i < ranking.size(); ++i) {
            if (punts.esMillor(ranking.get(i).second())) {
                ranking.add(i, new Pair<>(nom, punts));
                return;
            }
        }
        //Afegir usuari al final (si arriba aquí o bé és l'últim o bé el ranking està buit)
        ranking.add(new Pair<>(nom, punts));

    }

    /**
     * Permet saber en quina posició es troba l'usuari d'entre tots els usuaris que hi ha al ranking.
     *
     * @param usuari Usuari a cercar
     * @return Posició que ocupa l'usuari
     */
    public int posicioUsuari(Usuari usuari) {
        int pos = 1;
        for (Pair<String, T> row : ranking) {
            if (row.first().equals(usuari.getNom())) {
                return pos;
            }
            pos++;
        }
        return 0;
    }


    /**
     * Permet saber els usuaris amb més puntuació, i les respectives puntuacions
     *
     * @param n Nombre d'usuaris que es volen conèixer
     * @return Nom i puntuació corresponents als n usuaris amb més puntuació
     */
    public ArrayList<Pair<String, String>> getLlistaRanking(int n) {
        ArrayList<Pair<String, String>> result = new ArrayList<>();
        for (int i = 0; i < min(n, ranking.size()); ++i) {
            Pair<String, T> row = ranking.get(i);
            result.add(new Pair<>(row.first(), row.second().representa()));
        }
        return result;
    }


    /**
     * Permet saber la millor puntuació d'un usuari concret
     *
     * @param usuari Usuari del qual es vol saber la puntuació
     * @return Puntuació que té registrada al ranking
     */
    public String puntuacioUsuari(Usuari usuari) {
        for (Pair<String, T> row : ranking) {
            if (row.first().equals(usuari.getNom())) {
                return row.second().representa();
            }
        }
        return null;
    }
}
