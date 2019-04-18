package domain;

public class PuntuacioProblema implements Puntuacio<PuntuacioProblema> {
    private final int jugades;
    private final long temps;

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
        return jugades + "j " + temps / 1000 + "s";
    }
}
