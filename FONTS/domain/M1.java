package domain;

public class M1 extends Maquina {
    @Override
    public int heuristica(Tauler posicio, boolean esJugadorMaximal, int codi, Color torn) {
        if (codi == 2) { //mat del jugador anterior
            if (!esJugadorMaximal) return getMaxVal();
            else return getMinVal();
        } else if (codi == 3) { //taules
            if (esJugadorMaximal) return getMaxVal();
            else return getMinVal();
        }
        return 0;
    }
}
