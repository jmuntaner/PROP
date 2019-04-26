package drivers;

import domain.Alfil;
import domain.Color;

public class DriverAlfil extends GenericDriver {
    private Alfil a;

    DriverAlfil(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear alfil",
                "Conversio a char",
                "Comprova moviment valid"};
    }

    public static void main(String[] args) {
        DriverAlfil driver = new DriverAlfil(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && a == null) {
            System.out.println("ERROR: ALFIL NULL");
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
        a = new Alfil(x, y, c);
        c = a.getColor();
        if (c == Color.BLANC) color = "BLANC";
        else if (c == Color.NEGRE) color = "NEGRE";
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        System.out.printf("Creat: x: %d, y: %d, Color: %s\n", a.getPosicio().first(), a.getPosicio().second(), color);
    }

    public void testToChar() {
        System.out.println(a.toChar());
    }

    public void testEsMovimentValid() {
        optPrint("mata: ");
        boolean mata = scan.nextBoolean();
        optPrint("x: ");
        int x = scan.nextInt();
        scan.nextLine();
        optPrint("y: ");
        int y = scan.nextInt();
        boolean res = a.esMovimentValid(mata, x, y);
        System.out.println(res);
    }
}
