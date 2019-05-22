package domain;
//TODO:
// - test M2
// - capar profunditat
// - ordenacio moviments
public class M2 extends Maquina {
    private int valor_peca(char p) {
        int v = 0;
        switch (p) {
            case 'p':
                v = -10;
                break;
            case 'P':
                v = 10;
                break;
            case 'r':
                v = -50;
                break;
            case 'R':
                v = 50;
                break;
            case 'b':
                v = -30;
                break;
            case 'B':
                v = 30;
                break;
            case 'n':
                v = -30;
                break;
            case 'N':
                v = 30;
                break;
            case 'q':
                v = -90;
                break;
            case 'Q':
                v = 90;
                break;
            case 'k':
                v = -9000;
                break;
            case 'K':
                v = 9000;
                break;
        }
        return v;
    }

    @Override
    public boolean limitProfunditat(int profunditat) {
        return profunditat==0 || profunditat <= getProfunditatInicial()-2;
        //permet nomes un moviment propi i un de l'oponent
    }

    @Override
    public int heuristica(Tauler posicio, boolean esJugadorMaximal, int codi, Color torn) {
        if (codi == 2) { //mat del jugador anterior
            if (!esJugadorMaximal) return maxVal;
            else return minVal;
        } else if (codi == 3) { //taules, atacant perd, defensor guanya.
            return minVal;
        }
        int v = 0;
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                v += valor_peca(posicio.getCasella(i,j));
            }
        }
        if(torn == Color.BLANC && !esJugadorMaximal || torn == Color.NEGRE && esJugadorMaximal) v*=-1;
        //valor escac
        if (codi == 1) { //escac del jugador anterior
            if (!esJugadorMaximal) v+=3;
            else v-=3;
        }
        return v;
    }

    @Override
    public String getNom() {
        return "Barja";
    }
}

/*
Idea:
https://stackoverflow.com/questions/9964496/alpha-beta-move-ordering
    - Alpha-beta pruning (igual que M1)
    - Cut-off profunditat: no calcula tot el minimax, nomes un moviment de cada jugador màxim (arbitrari)
    - Heurística de peces: cada peça al tauler te un valor segons el seu tipus
    - Move ordering: avalua els millors moviments primer
 */
