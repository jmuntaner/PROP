package drivers;

import domain.Color;
import domain.Torre;


public class DriverTorre extends GenericDriver {
    private Torre t;

    DriverTorre(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear torre",
                "Conversio a char",
                "Comprova moviment valid"};
    }

    public static void main(String[] args) {
        DriverTorre driver = new DriverTorre(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && t == null) {
            System.out.println("ERROR: TORRE NULL");
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
        t = new Torre(x, y, c);
        c = t.getColor();
        if (c == Color.BLANC) color = "BLANC";
        else if (c == Color.NEGRE) color = "NEGRE";
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        System.out.printf("Creat: x: %d, y: %d, Color: %s\n", t.getPosicio().first(), t.getPosicio().second(), color);
    }

    public void testToChar() {
        System.out.println(t.toChar());
    }

    public void testEsMovimentValid() {
        optPrint("mata: ");
        boolean mata = scan.nextBoolean();
        optPrint("x: ");
        int x = scan.nextInt();
        scan.nextLine();
        optPrint("y: ");
        int y = scan.nextInt();
        boolean res = t.esMovimentValid(mata, x, y);
        System.out.println(res);
    }
}