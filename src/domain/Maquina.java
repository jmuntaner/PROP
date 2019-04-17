package domain;

public abstract class Maquina {
    public abstract int heuristica(Tauler posicio, Color torn);

    private int valor_peca(char p) {
        int v = 0;
        switch (p) {
            case 'p': v=-10; break;
            case 'P': v=10; break;
            case 'r': v=-50; break;
            case 'R': v=50; break;
            case 'b': v=-30; break;
            case 'B': v=30; break;
            case 'n': v=-30; break;
            case 'N': v=30; break;
            case 'q': v=90; break;
            case 'Q': v=90; break;
            case 'k': v=-9000; break;
            case 'K': v=9000; break;
        }
        return v;
    }
}
