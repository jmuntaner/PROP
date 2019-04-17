package drivers;

import domain.Peo;
import domain.Color;


public class DriverPeo extends GenericDriver {
    private Peo p;

    DriverPeo(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear peo",
                "Conversio a char",
                "Comprova moviment valid"};
    }

    public static void main(String[] args) {
        DriverPeo driver = new DriverPeo(args);
        driver.runLoop();
        System.out.println(); //Línia en blanc entre tests
    }

    @Override
    void runTest(int option) {
        if (option != 1 && p == null) {
            System.out.println("ERROR: PEO NULL");
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
        if(color.equals("BLANC")) c = Color.BLANC;
        else if(color.equals("NEGRE")) c = Color.NEGRE;
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        p = new Peo(x, y, c);
        c = p.getColor();
        if(c == Color.BLANC) color = "BLANC";
        else if(c == Color.NEGRE) color = "NEGRE";
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        System.out.printf("Creat: x: %d, y: %d, Color: %s\n", p.getPosicio().getKey(), p.getPosicio().getValue(), color);
    }

    public void testToChar() {
        System.out.println();
        System.out.println(p.toChar());
    }

    public void testEsMovimentValid() {
        optPrint("mata: ");
        boolean mata = scan.nextBoolean();
        optPrint("x: ");
        int x = scan.nextInt();
        scan.nextLine();
        optPrint("y: ");
        int y = scan.nextInt();
        boolean res = p.esMovimentValid(mata, x, y);
        System.out.println(res);
    }
}
