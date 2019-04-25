package drivers;

import domain.*;
import utils.Pair;

public class DriverProblema extends GenericDriver {
    private Problema p;
    boolean probInit;

    DriverProblema(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Test constructor",
                "Test get nom",
                "Test set nom",
                "Test get tema",
                "Test get número jugades",
                "Test get dificultat",
                "Test get situació inicial",
                "Test get ranking",
                "Test init problema"
        };
    }

    public static void main(String[] args) {
        DriverProblema driver = new DriverProblema(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && p == null) {
            System.out.println("ERROR: PROBLEMA NULL");
            return;
        } else if (!probInit && option >= 4 && option <= 8) {
            System.out.println("Cal inicialitzar el problema");
            return;
        }
        switch (option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testGetNom();
                break;
            case 3:
                testSetNom();
                break;
            case 4:
                testGetTema();
                break;
            case 5:
                testGetNumJugades();
                break;
            case 6:
                testGetDificultat();
                break;
            case 7:
                testGetSituacioInicial();
                break;
            case 8:
                testGetRanking();
                break;
            case 9:
                testInitProblema();
                break;
            default:
                System.out.println("Test no vàlid");
        }
    }

    public void testConstructor() {
        optPrint("Introdueix nom: ");
        String s = scan.nextLine();
        p = new Problema(s);
        System.out.printf("Problema creat: nom = %s\n", p.getNom());
        probInit = false;
    }

    public void testGetNom() {
        System.out.println(p.getNom());
    }

    public void testSetNom() {
        optPrint("Introdueix nom: ");
        String s = scan.nextLine();
        p.setNom(s);
        System.out.printf("Nom registrat: %s\n", p.getNom());
    }

    public void testGetTema() {
        System.out.println(p.getTema());
    }

    public void testGetNumJugades() {
        System.out.println(p.getNumJugades());
    }

    public void testGetDificultat() {
        System.out.println(p.getDificultat());
    }

    public void testGetSituacioInicial() {
        Tauler t = p.getSituacioInicial();
        printTauler(t);
    }

    public void testGetRanking() {
        Ranking<PuntuacioProblema> r = p.getRanking();
        Usuari u = new Usuari(1, "IdTest", "Pass");
        EstadistiquesPartida e = new EstadistiquesPartida();
        PuntuacioProblema pp = new PuntuacioProblema(e, Color.BLANC);
        r.afegeixPuntuacio(u, pp);
        Pair<String, String> data = r.getLlistaRanking(1).get(0);
        System.out.printf("%s %s\n", data.getKey(), data.getValue());
    }

    public void testInitProblema() {
        optPrint("Nombre de jugades seguit de FEN: ");
        int nj = scan.nextInt();
        String fen = scan.nextLine().trim();
        try {
            boolean b = p.initProblema(nj, fen);
            if (b) {
                System.out.println("Inicialitzacio correcta");
                probInit = true;
            } else System.out.println("Inicialitzacio incorrecta: problema sense solucio");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
