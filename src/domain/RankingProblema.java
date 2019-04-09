package domain;

import javafx.util.Pair;

import java.util.ArrayList;

class RankingProblema implements Ranking {
    private ArrayList<Pair<String, Pair<Integer, Long>>> ranking;

    //TODO: Consultar Elvira: Implementar amb estructura més eficient
    // Implementar subclasse fila?
    RankingProblema() {
        ranking = new ArrayList<>();
    }

    private static String puntsAString(Pair<Integer, Long> punts) {
        long puntsSegons = punts.getValue() / 1000;
        return punts.getKey() + "j " + puntsSegons + "s";
    }


    private static boolean esMillor(Pair<Integer, Long> a, Pair<Integer, Long> b) {
        if (a.getKey() < b.getKey()) return true;
        if (b.getKey() < a.getKey()) return false;
        return b.getValue() >= a.getValue();
    }

    /**
     * Afegeix la partida d'un usuari al ranking.
     *
     * @param usuari        Usuari que ha jugat la partida.
     * @param estadistiques Estadístiques de la partida.
     * @param color         Color amb el que ha jugat l'usuari.
     */
    public void afegeixPartida(Usuari usuari, EstadistiquesPartida estadistiques, Color color) {
        long temps = estadistiques.getTemps(color);
        int jugades = estadistiques.getJugades(color);
        Pair<Integer, Long> punts = new Pair<>(jugades, temps);
        String nom = usuari.getNom();
        //Busquem si un usuari ja te una puntuació millor
        Pair<String, Pair<Integer, Long>> trobat = null;
        for (Pair<String, Pair<Integer, Long>> row : ranking) {
            if (row.getKey().equals(usuari.getNom())) {
                trobat = row;
                break;
            }
        }
        if (trobat != null) {
            if (esMillor(trobat.getValue(), punts)) return;
            ranking.remove(trobat);
        }
        // Insertem la nova puntuació on toca
        for (int i = 0; i < ranking.size(); ++i) {
            if (esMillor(punts, ranking.get(i).getValue())) {
                ranking.add(i, new Pair<>(nom, punts));
                return;
            }
        }
    }

    @Override
    public String puntuacioUsuari(Usuari usuari) {
        for (Pair<String, Pair<Integer, Long>> row : ranking) {
            if (row.getKey().equals(usuari.getNom())) {
                return puntsAString(row.getValue());
            }
        }
        return null;
    }

    @Override
    public int posicioUsuari(Usuari usuari) {
        int pos = 1;
        for (Pair<String, Pair<Integer, Long>> row : ranking) {
            if (row.getKey().equals(usuari.getNom())) {
                return pos;
            }
            pos++;
        }
        return 0;
    }

    @Override
    public ArrayList<Pair<String, String>> getLlistaRanking(int n) {
        ArrayList<Pair<String, String>> result = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            Pair<String, Pair<Integer, Long>> row = ranking.get(i);
            result.add(new Pair<>(row.getKey(), puntsAString(row.getValue())));
        }
        return result;
    }

}
