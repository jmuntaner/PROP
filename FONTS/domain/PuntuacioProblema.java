package domain;

public class PuntuacioProblema implements Puntuacio<PuntuacioProblema> {
    private final int jugades;
    private final long temps;


    /**
     * Creadora de puntuació associada a un problema
     *
     * @param estadistiques Estadístiques de la partida
     * @param color         Tema del problema
     */
    public PuntuacioProblema(EstadistiquesPartida estadistiques, Color color) {
        temps = estadistiques.getTemps(color);
        jugades = estadistiques.getJugades(color);
    }

    @Override
    public boolean esMillor(PuntuacioProblema altre) {
        if (jugades < altre.jugades) return true;
        if (altre.jugades < jugades) return false;
        return altre.temps > temps;
    }

    @Override
    public String representa() {
        return jugades + "j " + String.format("%.2f", ((double) temps) / 1000) + "s";
    }
}
