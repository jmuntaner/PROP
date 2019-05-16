package domain;

public class M1 extends Maquina {
    @Override
    public int heuristica(Tauler posicio, boolean esJugadorMaximal, int codi, Color torn) {
        if (codi == 2) { //mat del jugador anterior
            if (!esJugadorMaximal) return maxVal;
            else return minVal;
        } else if (codi == 3) { //taules, atacant perd, defensor guanya.
            return minVal;
        }
        return 0;
    }

    @Override
    public String getNom() {
        return "Xicu";
    }
}
