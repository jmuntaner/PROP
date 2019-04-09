package domain;

class EstadistiquesPartida {
    private Color tornActual;
    private boolean tornEnCurs;

    private int jugadesBlanc, jugadesNegre;
    private long startTime, tempsBlanc, tempsNegre;

    /**
     * Creadora per defecte.
     */
    EstadistiquesPartida() {
        jugadesNegre = 0;
        jugadesBlanc = 0;
        tempsBlanc = 0;
        tempsNegre = 0;
        tornEnCurs = false;
    }

    /**
     * Creadora amb dades existents.
     *
     * @param jugadesB Jugades realitzades pel jugador blanc.
     * @param jugadesN Jugades realitzades pel jugador negre.
     * @param tempsB   Temps jugat pel jugador blanc.
     * @param tempsN   Temps jugat pel jugador negre.
     */
    EstadistiquesPartida(int jugadesB, int jugadesN, long tempsB, long tempsN) {
        jugadesNegre = jugadesN;
        jugadesBlanc = jugadesB;
        tempsNegre = tempsN;
        tempsBlanc = tempsB;
        tornEnCurs = false;
    }

    /**
     * Inicia el torn del jugador especificat, actualitzant les estadístiques.
     *
     * @param jugador Jugador que juga el torn.
     */
    public void iniciaTorn(Color jugador) {
        tornActual = jugador;
        tornEnCurs = true;
        if (jugador == Color.BLANC) jugadesBlanc++;
        else jugadesNegre++;
        startTime = System.currentTimeMillis();
    }

    /**
     * Finalitza el torn del jugador especificat, actualitzant les estadístiques.
     *
     * @param jugador Jugador que juga el torn.
     */
    public void finalitzaTorn(Color jugador) {
        if (!tornEnCurs) throw new RuntimeException("No es pot finalitzar un torn no iniciat");
        if (tornActual != jugador) throw new RuntimeException("El torn finalitzat no coincideix amb l'actual");
        tornEnCurs = false;

        long endTime = System.currentTimeMillis() - startTime;
        if (jugador == Color.BLANC) tempsBlanc += endTime;
        else tempsNegre += endTime;
    }

    /**
     * Obté el nombre de jugades realitzades pel jugador especificat.
     *
     * @param jugador Jugador a consultar.
     * @return Nombre de jugades realitzades pel jugador.
     */
    public int getJugades(Color jugador) {
        if (jugador == Color.BLANC) return jugadesBlanc;
        else return jugadesNegre;
    }

    /**
     * Obté el nombre total de jugades.
     *
     * @return Nombre total de jugades realitzades a la partida.
     */
    public int getJugadesTotals() {
        return jugadesBlanc + jugadesNegre;
    }

    /**
     * Obté el temps jugat per un jugador.
     *
     * @param jugador Jugador a consultar.
     * @return Temps jugat pel jugador.
     */
    public long getTemps(Color jugador) {
        if (jugador == Color.BLANC) return tempsBlanc;
        else return tempsNegre;
    }

    /**
     * Obté el temps mig per jugada per un jugador.
     *
     * @param jugador Jugador a consultar.
     * @return Temps mig de jugada pel jugador.
     */
    public long getTempsMitja(Color jugador) {
        if (jugador == Color.BLANC) return tempsBlanc / jugadesBlanc;
        else return tempsNegre / jugadesNegre;
    }

    /**
     * Obté el temps mig per jugada de la partida.
     *
     * @return Temps mig de jugada de la partida.
     */
    public long getTempsMitja() {
        return (tempsBlanc + tempsNegre) / (jugadesBlanc + jugadesNegre);
    }

    /**
     * Obté la durada de la partida.
     *
     * @return Temps total jugat de la partida.
     */
    public long getTempsTotal() {
        return tempsBlanc + tempsNegre;
    }
}
