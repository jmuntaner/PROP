package drivers;

import domain.Color;
import domain.Moviment;
import domain.Peca;
import domain.Rei;
import utils.Pair;

public class DriverMoviment extends GenericDriver {
    private Moviment m;

    DriverMoviment(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Test constructor",
                "Test posició final",
                "Test peça moguda",
                "Test peça morta"
        };
    }

    public static void main(String[] args) {
        DriverMoviment driver = new DriverMoviment(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && m == null) {
            System.out.println("ERROR: MOVIMENT NULL");
            return;
        }
        switch (option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testPosFinal();
                break;
            case 3:
                testPecaMoguda();
                break;
            case 4:
                testPecaMorta();
                break;
            default:
                System.out.println("Test no vàlid");
        }
    }

    public void testConstructor() {
        optPrint("Posició inicial i final: ");
        int xi = scan.nextInt();
        int yi = scan.nextInt();
        int xf = scan.nextInt();
        int yf = scan.nextInt();
        scan.nextLine();
        try {
            Peca r = new Rei(xi, yi, Color.BLANC);
            m = new Moviment(r, xf, yf);
            Pair<Integer, Integer> pi = m.getPosIni();
            Pair<Integer, Integer> pf = m.getPosFinal();
            Peca p = m.getPecaMoguda();
            System.out.printf("%d %d %d %d %c\n", pi.getKey(), pi.getValue(), pf.getKey(), pf.getValue(), p.toChar());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testPosFinal() {
        optPrint("Posició final: ");
        int xf = scan.nextInt();
        int yf = scan.nextInt();
        scan.nextLine();
        try {
            m.setPosFinal(xf, yf);
            Pair<Integer, Integer> p = m.getPosFinal();
            System.out.printf("%d %d\n", p.getKey(), p.getValue());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testPecaMoguda() {
        optPrint("Posicio peça i color: ");
        int xp = scan.nextInt();
        int yp = scan.nextInt();
        boolean b = scan.nextBoolean();
        scan.nextLine();
        Color c;
        if (b) c = Color.BLANC;
        else c = Color.NEGRE;
        try {
            Peca r = new Rei(xp, yp, c);
            m.setPecaMoguda(r);
            Peca p = m.getPecaMoguda();
            Pair<Integer, Integer> pi = m.getPosIni();
            System.out.printf("%d %d %c\n", pi.getKey(), pi.getValue(), p.toChar());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testPecaMorta() {
        optPrint("Posicio peça i color: ");
        int xp = scan.nextInt();
        boolean null_piece = xp == -1;
        int yp = scan.nextInt();
        boolean b = scan.nextBoolean();
        scan.nextLine();
        Color c;
        if (b) c = Color.BLANC;
        else c = Color.NEGRE;
        Peca r = null;
        if (!null_piece) r = new Rei(xp, yp, c);
        try {
            m.setPecaMorta(r);
            Peca p = m.getPecaMorta();
            if (r == null) System.out.println("null");
            else {
                Pair<Integer, Integer> pf = m.getPosFinal();
                System.out.printf("%d %d %c\n", pf.getKey(), pf.getValue(), p.toChar());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
