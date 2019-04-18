package drivers;

import domain.Color;
import domain.EstadistiquesPartida;
import domain.PuntuacioProblema;

public class DriverPuntuacioProblema extends GenericDriver {
    private PuntuacioProblema pA, pB;

    DriverPuntuacioProblema(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Defineix punts A",
                "Defineix punts B",
                "A es millor que B",
                "B es millor que A"};
        pA = null;
        pB = null;
    }

    public static void main(String[] args) {
        DriverPuntuacioProblema driver = new DriverPuntuacioProblema(args);
        driver.runLoop();
    }

    public void testEsMillor(PuntuacioProblema a, PuntuacioProblema b) {
        if (a == null || b == null) {
            System.out.println("Error: A o B no ha estat definit");
            return;
        }
        System.out.println(a.esMillor(b));
    }

    public void testCreador(char punts) {
        optPrint("Jugades: ");
        int jugades = scan.nextInt();
        scan.nextLine();
        optPrint("Temps (ms): ");
        int temps = scan.nextInt();
        scan.nextLine();
        optPrint("Color (b/n): ");
        Color c = scan.nextLine().contains("b") ? Color.BLANC : Color.NEGRE;

        EstadistiquesPartida e;
        if (c == Color.BLANC)
            e = new EstadistiquesPartida(jugades, 0, temps, 0);
        else
            e = new EstadistiquesPartida(0, jugades, 0, temps);

        if (punts == 'A') {
            pA = new PuntuacioProblema(e, c);
            System.out.println(pA.representa());
        } else {
            pB = new PuntuacioProblema(e, c);
            System.out.println(pB.representa());
        }
    }

    @Override
    void runTest(int option) {
        switch (option) {
            case 1:
                testCreador('A');
                break;
            case 2:
                testCreador('B');
                break;
            case 3:
                testEsMillor(pA, pB);
                break;
            case 4:
                testEsMillor(pB, pA);
                break;
            default:
                System.out.println("Test no implementat");
        }
    }
}
