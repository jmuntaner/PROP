package domain;
import java.io.Serializable;

public class EstadistiquesJugador {
    private int intents; // nombre de problemes intentats (començats)
    private int acabats; // nombre de problemes acabats
    private int guanyats; // nombre de problemes guanyats

    public EstadistiquesJugador() {
        intents = 0;
        acabats = 0;
        guanyats = 0;
        //TODO: afegir-ne mes
    }

    /**
     * Getter intents
     * @return intents
     */
    public int getIntents() {
        return intents;
    }

    /**
     * Getter acabats
     * @return acabats
     */
    public int getAcabats() {
        return acabats;
    }

    /**
     * Getter guanyats
     * @return guanyats
     */
    public int getGuanyats() {
        return guanyats;
    }

    /**
     * Actualització de les estadistiques a l'inici de partida
     */
    public void iniciaPartida() {
        intents++;
    }

    /**
     * Actualització de les estadistiques a la finalització de partida
     * @param guanyat representa si ha guanyat o no
     */
    public void acabaPartida(boolean guanyat) {
        acabats++;
        if(guanyat) guanyats++;
    }

    /**
     * Reinicia les estadistiques del jugador a 0
     */
    public void reset() {
        intents = 0;
        acabats = 0;
        guanyats = 0;
    }
}

