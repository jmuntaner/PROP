package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FenTranslator {
    private static final int SIZE = 8;
    private static final Pattern FEN_CHECK = Pattern.compile("([KQBRNPkqbrnp1-8]+\\/){7}[KQBRNPkqbrnp1-8]+ [wb] - - [01] [01]");

    /**
     * Converteix una string en notació FEN a un objecte Tauler.
     *
     * @param fen String que representa l'estat d'una partida en FEN.
     * @return Tauler equivalent al FEN entrat.
     */
    public static Tauler generaTauler(String fen) throws RuntimeException {
        // Validacio Inicial fen
        Matcher matcher = FEN_CHECK.matcher(fen);
        if (!matcher.find()) throw new RuntimeException("Sintaxi FEN invalida.");

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
                if (y != 8) throw new RuntimeException("Falten peces al FEN");
                x++;
                y = 0;
            } else {
                if (act >= '0' && act <= '9') {
                    // Salta caselles buides
                    y += act - '0';
                } else {
                    // Afegeix peça al tauler
                    taula[x][y] = char2Peca(act, x, y);
                    y++;
                }
            }
        }
        if (y != 8) throw new RuntimeException("Falten peces al FEN");
        return new Tauler(taula);
    }

    /**
     * Obté el color del jugador inicial d'una partida en FEN.
     *
     * @param fen String que representa l'estat d'una partida en FEN.
     * @return Color del jugador que comença movent.
     */
    public static Color getColor(String fen) {
        char colorInicial = fen.charAt(fen.length() - 9); // 'w' or 'b'
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
    public static Peca char2Peca(char c, int x, int y) {
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
                throw new RuntimeException(String.format("Tipus de peça desconegut al FEN: '%c'.", c));
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
        StringBuilder sb = new StringBuilder();
        int espais = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char p = t.getCasella(i, j);
                if (p == '-') espais++;
                else {
                    if (espais > 0) {
                        sb.append(espais);
                        espais = 0;
                    }
                    sb.append(p);
                }
            }
            if (espais > 0) {
                sb.append(espais);
                espais = 0;
            }
            if (i != 7) sb.append("/");
        }
        if (inicial == Color.BLANC) sb.append(" w");
        else sb.append(" b");
        sb.append(" - - 0 1");
        return sb.toString();
    }
}
