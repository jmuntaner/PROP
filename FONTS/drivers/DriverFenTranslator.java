package drivers;

import domain.Color;
import domain.FenTranslator;
import domain.Peca;
import domain.Tauler;

public class DriverFenTranslator extends GenericDriver {
    private Tauler t;
    private Color c;

    DriverFenTranslator(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Carregar FEN",
                "Eliminar peça",
                "Afegir peça",
                "Generar FEN",
                "Imprimir tauler"};
        c = Color.BLANC;
        t = new Tauler();

    }

    public static void main(String[] args) {
        DriverFenTranslator driver = new DriverFenTranslator(args);
        driver.runLoop();
    }


    void testCarregaFen() {
        optPrint("Introduir FEN: ");
        String fen = scan.nextLine();
        t = FenTranslator.generaTauler(fen);
        c = FenTranslator.getColor(fen);
        optPrint("Color: ");
        System.out.println(c == Color.BLANC ? "blanc" : "negre");
        optPrintln("Tauler:");
        printTauler(t);
    }

    void eliminaPeca() {
        optPrint("coordenades (x y): ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();
        t.treurePeca(x, y);
        printTauler(t);
    }

    void afegeixPeca() {
        optPrint("Tipus: ");
        char c = scan.nextLine().charAt(0);
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();
        Peca p = FenTranslator.char2Peca(c, x, y);
        t.afegirPeca(p);
        printTauler(t);
    }

    void testGeneraFen() {
        System.out.println(FenTranslator.generaFen(t, c));
    }

    @Override
    void runTest(int option) {
        switch (option) {
            case 1:
                testCarregaFen();
                break;
            case 2:
                eliminaPeca();
                break;
            case 3:
                afegeixPeca();
                break;
            case 4:
                testGeneraFen();
                break;
            case 5:
                printTauler(t);
                break;
            default:
                System.out.println("Test no implementat");
        }
    }
}
