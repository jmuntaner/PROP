package drivers;

import domain.Color;
import domain.Moviment;
import domain.Partida;
import domain.Problema;
import javafx.util.Pair;

import java.util.ArrayList;

public class DriverPartida extends GenericDriver {
    Partida mPartida;

    DriverPartida(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crea partida",
                "Obté torn",
                "Obté peça a pos",
                "Obté historial",
                "Obté número de moviments",
                "Mou peça",
                "Mostra tauler",
                "Reinicia partida"
        };
    }

    public static void main(String[] args) {
        DriverPartida driver = new DriverPartida(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && mPartida == null) {
            System.out.println("Error: partida no inicialitzada.");
            return;
        }
        switch (option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testGetTorn();
                break;
            case 3:
                testGetPeca();
                break;
            case 4:
                testGetHistorial();
                break;
            case 5:
                testGetNumMoviments();
                break;
            case 6:
                testMoure();
                break;
            case 7:
                printTauler(mPartida.getSituacioActual());
                break;
            case 8:
                testReset();
                break;
            default:
                System.out.println("Test no implementat");
        }
    }

    void testConstructor() {
        optPrint("FEN: ");
        String fen = scan.nextLine();
        optPrint("Numero de jugades: ");
        int nj = scan.nextInt();
        scan.nextLine();

        Problema p = new Problema("test");
        p.initProblema(nj, fen);

        mPartida = new Partida(p);
        System.out.println("Partida creada");
    }

    void testGetTorn() {
        Color c = mPartida.getTorn();
        System.out.println(c == Color.BLANC ? "blanc" : "negre");
    }

    void testGetPeca() {
        optPrint("Posició (x y): ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();
        System.out.println(mPartida.getAtPosicio(x, y));
    }

    void testGetHistorial() {
        ArrayList<Moviment> hist = mPartida.getHistorial();

        optPrintln("Historial:");
        int i = 1;
        for (Moviment m : hist) {
            System.out.printf("%d. ", i);
            printMoviment(m);
            i++;
        }
    }

    void testGetNumMoviments() {
        System.out.println(mPartida.getNumMoviments());
    }

    void testMoure() {
        printTauler(mPartida.getSituacioActual());
        optPrint("Jugador (b/n):");
        Color j = scan.nextLine().contains("b") ? Color.BLANC : Color.NEGRE;
        optPrint("Peça a moure (x y): ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();

        ArrayList<Moviment> movs = mPartida.obteMovimentsPosicio(x, y);
        if (movs == null || movs.isEmpty()) {
            System.out.println("No hi ha moviments");
            return;
        }
        optPrintln("Moviments possibles:");
        for (int i = 0; i < movs.size(); i++) {

            Pair<Integer, Integer> end = movs.get(i).getPosFinal();
            System.out.printf("%d: %d,%d\r\n", i + 1, end.getKey(), end.getValue());
        }
        optPrint("Moviment a realitzar: ");
        int mov = scan.nextInt() - 1;

        scan.nextLine();
        int res;
        try {
            res = mPartida.moure(j, movs.get(mov));
        } catch (RuntimeException e) {
            System.out.printf("Error: %s\r\n", e.getMessage());
            return;
        }
        System.out.println(res);
    }

    void testReset() {
        mPartida.reset();
        System.out.println("reset");
    }
}
