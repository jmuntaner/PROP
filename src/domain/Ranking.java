package domain;

import javafx.util.Pair;

import java.util.ArrayList;

interface Ranking {
    //TODO: Consultar Elvira: Llençar excepcions o consultora existeixUsuari()?

    /**
     * Obté la puntuació màxima de l'usuari en el ranking.
     *
     * @param usuari Usuari a consultar.
     * @return Puntuació de l'usuari.
     */
    String puntuacioUsuari(Usuari usuari);

    /**
     * Obté la posició de l'usuari en el ranking.
     *
     * @param usuari Usuari a consultar.
     * @return Posició de l'usuari en el ranking.
     */
    int posicioUsuari(Usuari usuari);

    /**
     * Obté la llista d'usuaris i puntuacions del ranking.
     *
     * @param n Número de entrades del ranking a obtenir.
     * @return Llista amb els n millors usuaris i les seves puntuacions, ordenada de millor a pitjor.
     */
    ArrayList<Pair<String, String>> getLlistaRanking(int n);
}
