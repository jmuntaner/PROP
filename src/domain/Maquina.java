package domain;

public abstract class Maquina {
    public abstract int heuristica(char[] llistaPeces);

    private int valor_peca(char p) {
        int v = 0;
        switch (p) {
            case 'p': v=1; break;
            case 'P': v=1; break;
            case 'r': v=5; break;
            case 'R': v=5; break;
            case 'b': v=3; break;
            case 'B': v=3; break;
            case 'n': v=3; break;
            case 'N': v=3; break;
            case 'q': v=9; break;
            case 'Q': v=9; break;
        }
        return v;
    }
}
