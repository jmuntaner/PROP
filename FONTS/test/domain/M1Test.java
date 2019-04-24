package test.domain;

import domain.*;
import javafx.util.Pair;
import org.junit.Test;

import static domain.Color.BLANC;
import static org.junit.Assert.assertEquals;

public class M1Test {
    private void verificaMoviment(Moviment m, int xi, int yi, int xf, int yf) {
        Pair<Integer, Integer> ini = m.getPosIni();
        Pair<Integer, Integer> fin = m.getPosFinal();
        assertEquals(xi, ini.getKey(), 0);
        assertEquals(yi, ini.getValue(), 0);
        assertEquals(xf, fin.getKey(), 0);
        assertEquals(yf, fin.getValue(), 0);
    }

    private void jugaPartida(int n, String fen) {
        Tauler t = FenTranslator.generaTauler(fen);
        Color ataca = FenTranslator.getColor(fen);
        M1 m = new M1();

        int lastres = -1;
        Color torn = ataca;
        for (int i = (n * 2) - 1; i >= 1; i--) {
            Moviment mov = m.calcularMoviment(i, t, torn, ataca);
            torn = torn.getNext();
            lastres = t.mou(mov);
            if (lastres == 2) break;
        }

        assertEquals(2, lastres, 0);
    }

    @Test
    public void calcularMoviment() {
        // Problemes amb resultats fixes i verificats:
        // Problema de l'enunciat, profunditat = 2
        Tauler t = FenTranslator.generaTauler("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1");
        M1 m = new M1();
        Moviment mov = m.calcularMoviment(3, t, BLANC, BLANC);
        verificaMoviment(mov, 6, 2, 4, 3);
        assertEquals(0, t.mou(mov), 0);
        mov = m.calcularMoviment(1, t, BLANC, BLANC);
        verificaMoviment(mov, 0, 1, 2, 2);
        assertEquals(2, t.mou(mov), 0);
        System.out.println("Problema 1 Ok");

        // Problema 2: Mat en 2
        t = FenTranslator.generaTauler("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5 w - - 0 1");
        mov = m.calcularMoviment(3, t, BLANC, BLANC);
        verificaMoviment(mov, 6, 1, 4, 2);
        assertEquals(0, t.mou(mov), 0);
        mov = m.calcularMoviment(1, t, BLANC, BLANC);
        verificaMoviment(mov, 7, 2, 5, 4);
        assertEquals(2, t.mou(mov), 0);
        System.out.println("Problema 2 Ok");


        // Problemes extra (només es valida que es faci mat com a màxim en N jugades)
        // Problemes de mat en 2 (http://wtharvey.com/m8n2.txt)
        jugaPartida(2, "1rb4r/pkPp3p/1b1P3n/1Q6/N3Pp2/8/P1P3PP/7K w - - 0 1");
        System.out.println("Problema 3 Ok");
        jugaPartida(2, "5rkr/pp2Rp2/1b1p1Pb1/3P2Q1/2n3P1/2p5/P4P2/4R1K1 w - - 0 1");
        System.out.println("Problema 4 Ok");
        jugaPartida(2, "1r1kr3/Nbppn1pp/1b6/8/6Q1/3B1P2/Pq3P1P/3RR1K1 w - - 0 1");
        System.out.println("Problema 5 Ok");
        jugaPartida(2, "3q2r1/4n2k/p1p1rBpp/PpPpPp2/1P3P1Q/2P3R1/7P/1R5K w - - 0 1");
        System.out.println("Problema 6 Ok");
        jugaPartida(2, "r2qk2r/pb4pp/1n2Pb2/2B2Q2/p1p5/2P5/2B2PPP/RN2R1K1 w - - 0 1");
        System.out.println("Problema 7 Ok");
        jugaPartida(2, "6k1/pp4p1/2p5/2bp4/8/P5Pb/1P3rrP/2BRRN1K b - - 0 1");
        System.out.println("Problema 8 Ok");

        // Problemes de mat en 3 (http://wtharvey.com/m8n3.txt)
        jugaPartida(3, "r2n1rk1/1ppb2pp/1p1p4/3Ppq1n/2B3P1/2P4P/PP1N1P1K/R2Q1RN1 b - - 0 1");
        System.out.println("Problema 9 Ok");
        jugaPartida(3, "rr3k2/pppq1pN1/1b1p1BnQ/1b2p1N1/4P3/2PP3P/PP3PP1/R4RK1 w - - 0 1");
        System.out.println("Problema 10 Ok");


    }

}