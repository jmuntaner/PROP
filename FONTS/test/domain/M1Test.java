package test.domain;

import domain.*;
import javafx.util.Pair;
import org.junit.Test;

import static domain.Color.BLANC;

public class M1Test {
    public void verificaMoviment(Moviment m, int xi, int yi, int xf, int yf) {
        Pair<Integer, Integer> ini = m.getPosIni();
        Pair<Integer, Integer> fin = m.getPosFinal();
        assert ini.getKey() == xi;
        assert ini.getValue() == yi;
        assert fin.getKey() == xf;
        assert fin.getValue() == yf;
    }

    @Test
    public void calcularMoviment() {
        // Problema de l'enunciat, profunditat = 2
        Tauler t = FenTranslator.generaTauler("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1");
        MaquinaStub m = new MaquinaStub();
        Moviment mov = m.calcularMoviment(3, t, BLANC, BLANC);
        assert m.countHeuristica == 4177;
        m.countHeuristica = 0;

        verificaMoviment(mov, 6, 2, 4, 3);

        t.mou(mov);
        mov = m.calcularMoviment(2, t, BLANC, BLANC);
        verificaMoviment(mov, 0, 1, 2, 2);
        assert m.countHeuristica == 1130;
    }

    private class MaquinaStub extends M1 {
        int countHeuristica = 0;

        @Override
        public int heuristica(Tauler posicio, boolean esJugadorMaximal, int codi, Color torn) {
            countHeuristica++;
            return super.heuristica(posicio, esJugadorMaximal, codi, torn);
        }
    }
}