package drivers;

import domain.Color;
import domain.Reina;
import utils.Pair;


public class DriverReina extends GenericDriver {
    private Reina r;

    DriverReina(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear reina",
                "Conversio a char",
                "Comprova moviment valid",
                "Obté color",
                "Obté posicio",
                "Modifica posicio 1",
                "Modifica posicio 2"};
    }

    public static void main(String[] args) {
        DriverReina driver = new DriverReina(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && r == null) {
            System.out.println("ERROR: REINA NULL");
            return;
        }
        switch (option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testToChar();
                break;
            case 3:
                testEsMovimentValid();
                break;
            case 4:
                testGetColor();
                break;
            case 5:
                testGetPos();
                break;
            case 6:
                testSetPos1();
                break;
            case 7:
                testSetPos2();
                break;
            default:
                System.out.println("Test no valid");
        }
    }

    public void testConstructor() {
        optPrint("Color: ");
        String color = scan.nextLine();
        optPrint("x: ");
        int x = scan.nextInt();
        scan.nextLine();
        optPrint("y: ");
        int y = scan.nextInt();
        Color c;
        if (color.equals("BLANC")) c = Color.BLANC;
        else if (color.equals("NEGRE")) c = Color.NEGRE;
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        r = new Reina(x, y, c);
        c = r.getColor();
        if (c == Color.BLANC) color = "BLANC";
        else if (c == Color.NEGRE) color = "NEGRE";
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        System.out.printf("Creat: x: %d, y: %d, Color: %s\n", r.getPosicio().first(), r.getPosicio().second(), color);
    }

    public void testToChar() {
        System.out.println(r.toChar());
    }

    public void testEsMovimentValid() {
        optPrint("mata: ");
        boolean mata = scan.nextBoolean();
        optPrint("x: ");
        int x = scan.nextInt();
        scan.nextLine();
        optPrint("y: ");
        int y = scan.nextInt();
        boolean res = r.esMovimentValid(mata, x, y);
        System.out.println(res);
    }

    public void testGetColor() {
        String s = r.getColor() == Color.BLANC ? "Blanc" : "Negre";
        System.out.println(s);
    }

    public void testGetPos() {
        System.out.println("x: " + r.getPosicio().first() + " y: " + r.getPosicio().second());
    }

    public void testSetPos1() {
        optPrint("x: ");
        int x = scan.nextInt();
        optPrint("y: ");
        int y = scan.nextInt();
        r.setPosicio(new Pair<>(x, y));
        System.out.println("x: " + r.getPosicio().first() + " y: " + r.getPosicio().second());
    }

    public void testSetPos2() {
        optPrint("x: ");
        int x = scan.nextInt();
        optPrint("y: ");
        int y = scan.nextInt();
        r.setPosicio(x, y);
        System.out.println("x: " + r.getPosicio().first() + " y: " + r.getPosicio().second());
    }
}
