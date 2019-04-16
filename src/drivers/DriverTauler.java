package drivers;

import domain.Tauler;

public class DriverTauler extends GenericDriver{
    private Tauler t;

    DriverTauler(String [] args) {
        super(args);
        opcionsMenu = new String [] {
                "Test constructor",
                "Test constructor 2",
                "test afegir peça",
                "test treure peça",
                "test mou",
                "test mou invers",
                "test obté moviments jugador",
                "test obté moviments peça",
                "test get casella",
                "test final entrada tauler",
                "test comprova sol aux"
        };
    }

    public static void main(String [] args) {
        DriverTauler driver = new DriverTauler(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option!=1 && option!=2 && t==null) {
            System.out.println("ERROR: TAULER NULL");
            return;
        }
        switch(option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testConstructor2();
                break;
            case 3:
                testAfegirPeca();
                break;
            case 4:
                testTreurePeca();
                break;
            case 5:
                testMou();
                break;
            case 6:
                testMouInvers();
                break;
            case 7:
                testObteMovimentsJugador();
                break;
            case 8:
                testObteMovimentsPeca();
                break;
            case 9:
                testGetCasella();
                break;
            case 10:
                testFinalEntradaTauler();
                break;
            case 11:
                testComprovaSolAux();
                break;
            default:
                System.out.println("Test no vàlid");
        }
    }

    public void testConstructor() {}

    public void testConstructor2() {}

    public void testAfegirPeca() {}

    public void testTreurePeca() {}

    public void testMou() {}

    public void testMouInvers() {}

    public void testObteMovimentsJugador() {}

    public void testObteMovimentsPeca() {}

    public void testGetCasella() {}

    public void testFinalEntradaTauler() {}

    public void testComprovaSolAux() {}

}
