package domain;

import javafx.util.Pair;

import java.util.ArrayList;

public class Ranking<T extends Puntuacio<T>> {
    private final ArrayList<Pair<String, T>> ranking;

    Ranking() {
        ranking = new ArrayList<>();
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

        for (int i = 0; i < n; ++i) {
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
