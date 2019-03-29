package domain;

public class FenTranslator {
    private static final int SIZE = 8;

    /**
     * Converteix una string en notació FEN a un objecte Tauler.
     *
     * @param fen String que representa l'estat d'una partida en FEN.
     * @return Tauler equivalent al FEN entrat.
     */
    public static Tauler generaTauler(String fen) {
        Peca[][] taula = new Peca[SIZE][SIZE];
        String peces = fen.substring(0, fen.length() - 10); // borra " w - - 0 1"
        char act;
        int y = 0;
        int x = 0;
        for (int i = 0; i < peces.length(); ++i) {
            // https://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
            act = peces.charAt(i);
            if (act == '/') {
                // Salt de fila
                x++;
                y = 0;
            } else {
                if (act >= '0' && act <= '9') {
                    // Salta caselles buides
                    y += act - '0';
                } else {
                    // Afegeix peça al tauler
                    taula[x][y] = char2Peca(act, x, y);
                }
            }
        }
        return new Tauler(taula);
    }

    /**
     * Obté el color del jugador inicial d'una partida en FEN.
     *
     * @param fen String que representa l'estat d'una partida en FEN.
     * @return Color del jugador que comença movent.
     */
    public static Color getColor(String fen) {
        char colorInicial = fen.charAt(fen.length() - 8); // 'w' or 'b'
        if (colorInicial == 'w') return Color.BLANC;
        else if (colorInicial == 'b') return Color.NEGRE;
        else throw new RuntimeException("Color invalid a FEN.");
    }

    /**
     * Converteix un caràcter FEN a una Peça.
     *
     * @param c Caràcter FEN que representa una peça.
     * @param x Fila de la peça
     * @param y Columna de la peça
     * @return Peça representada pel caràcter.
     */
    private static Peca char2Peca(char c, int x, int y) {
        Color color;
        if (c < 'a') {
            color = Color.BLANC;
        } else {
            color = Color.NEGRE;
            c -= 32; // toUpper()
        }
        switch (c) {
            case 'K':
                return new Rei(x, y, color);
            case 'Q':
                return new Reina(x, y, color);
            case 'R':
                return new Torre(x, y, color);
            case 'B':
                return new Alfil(x, y, color);
            case 'N':
                return new Cavall(x, y, color);
            case 'P':
                return new Peo(x, y, color);
            default:
                throw new RuntimeException("Tipus de peça desconegut al FEN.");
        }
    }

    /**
     * Genera una string FEN a partir d'un tauler
     *
     * @param t       Tauler a convertir.
     * @param inicial Color del jugador que comença movent.
     * @return String FEN representant la partida demanada.
     */
    public static String generaFen(Tauler t, Color inicial) {
        // TODO: Cal esperar a que peça tingui implementat getChar()
        return null;
    }
}
