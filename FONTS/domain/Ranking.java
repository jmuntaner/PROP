package domain;

import javafx.util.Pair;

import java.util.ArrayList;

public class Ranking<T extends Puntuacio<T>> {
    private final ArrayList<Pair<String, T>> ranking;

    Ranking() {
        ranking = new ArrayList<>();
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    public void afegeixPuntuacio(Usuari usuari, T punts) {
        String nom = usuari.getNom();
        //Busquem si un usuari ja te una puntuació millor
        Pair<String, T> trobat = null;
        for (Pair<String, T> row : ranking) {
            if (row.getKey().equals(usuari.getNom())) {
                trobat = row;
                break;
            }
        }
        if (trobat != null) {
            if (trobat.getValue().esMillor(punts)) return;
            ranking.remove(trobat);
        }
        // Insertem la nova puntuació on toca
        for (int i = 0; i < ranking.size(); ++i) {
            if (punts.esMillor(ranking.get(i).getValue())) {
                ranking.add(i, new Pair<>(nom, punts));
                return;
            }
        }
        //Afegir usuari al final (si arriba aquí o bé és l'últim o bé el ranking està buit)
        ranking.add(new Pair<>(nom, punts));

    }

    public int posicioUsuari(Usuari usuari) {
        int pos = 1;
        for (Pair<String, T> row : ranking) {
            if (row.getKey().equals(usuari.getNom())) {
                return pos;
            }
            pos++;
        }
        return 0;
    }

    public ArrayList<Pair<String, String>> getLlistaRanking(int n) {
        ArrayList<Pair<String, String>> result = new ArrayList<>();
        for (int i = 0; i < min(n, ranking.size()); ++i) {
            Pair<String, T> row = ranking.get(i);
            result.add(new Pair<>(row.getKey(), row.getValue().representa()));
        }
        return result;
    }

    public String puntuacioUsuari(Usuari usuari) {
        for (Pair<String, T> row : ranking) {
            if (row.getKey().equals(usuari.getNom())) {
                return row.getValue().representa();
            }
        }
        return null;
    }
}
