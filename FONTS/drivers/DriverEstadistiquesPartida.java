package drivers;

import domain.Color;
import domain.EstadistiquesPartida;

public class DriverEstadistiquesPartida extends GenericDriver {
    EstadistiquesPartida mEstadistiques;

    DriverEstadistiquesPartida(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear estadístiques buides",
                "Crear estadístiques amb paràmetres",
                "Inicia torn",
                "Finalitza torn",
                "Imprimeix temps",
                "Imprimeix jugades"};
        mEstadistiques = new EstadistiquesPartida();
    }

    public static void main(String[] args) {
        DriverEstadistiquesPartida driver = new DriverEstadistiquesPartida(args);
        driver.runLoop();
    }

    void imprimeixJugades() {
        optPrint("Jugades Blanc: ");
        System.out.println(mEstadistiques.getJugades(Color.BLANC));
        optPrint("Jugades Negre: ");
        System.out.println(mEstadistiques.getJugades(Color.NEGRE));
        optPrint("Jugades Totals: ");
        System.out.println(mEstadistiques.getJugadesTotals());
    }

    void imprimeixTemps() {
        optPrint("Temps Blanc (ms): ");
        System.out.println(mEstadistiques.getTemps(Color.BLANC));
        optPrint("Temps Negre (ms): ");
        System.out.println(mEstadistiques.getTemps(Color.NEGRE));
        optPrint("Temps total (ms): ");
        System.out.println(mEstadistiques.getTempsTotal());

        optPrint("Temps Mitjà Blanc (ms/jugada): ");
        System.out.println(mEstadistiques.getTempsMitja(Color.BLANC));
        optPrint("Temps Mitjà Negre (ms/jugada): ");
        System.out.println(mEstadistiques.getTempsMitja(Color.NEGRE));
        optPrint("Temps Mitjà General (ms/jugada): ");
        System.out.println(mEstadistiques.getTempsMitja());
    }

    public void testCreadoraBuida() {
        mEstadistiques = new EstadistiquesPartida();
        imprimeixJugades();
        imprimeixTemps();
    }

    public void testCreadoraParams() {
        optPrint("Jugades Blanc: ");
        int jb = scan.nextInt();
        scan.nextLine();
        optPrint("Jugades Negre: ");
        int jn = scan.nextInt();
        scan.nextLine();
        optPrint("Temps Blanc (ms): ");
        int tb = scan.nextInt();
        scan.nextLine();
        optPrint("Temps Negre (ms): ");
        int tn = scan.nextInt();
        scan.nextLine();
        mEstadistiques = new EstadistiquesPartida(jb, jn, tb, tn);
        imprimeixJugades();
        imprimeixTemps();
    }

    public void testIniciaTorn() {
        optPrint("Color (b/n):");
        String color = scan.nextLine();
        Color c = color.contains("b") ? Color.BLANC : Color.NEGRE;
        try {
            mEstadistiques.iniciaTorn(c);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        imprimeixJugades();
    }

    public void testFinalitzaTorn() {
        optPrint("Color (b/n):");
        String color = scan.nextLine();
        Color c = color.contains("b") ? Color.BLANC : Color.NEGRE;
        try {
            mEstadistiques.finalitzaTorn(c);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        imprimeixJugades();
    }

    @Override
    void runTest(int option) {
        switch (option) {
            case 1:
                testCreadoraBuida();
                break;
            case 2:
                testCreadoraParams();
                break;
            case 3:
                testIniciaTorn();
                break;
            case 4:
                testFinalitzaTorn();
                break;
            case 5:
                imprimeixTemps();
                break;
            case 6:
                imprimeixJugades();
                break;
            default:
                System.out.println("Test no implementat");
        }
    }
}
