package drivers;

import domain.Cavall;
import domain.Color;


public class DriverCavall extends GenericDriver {
    private Cavall k;

    DriverCavall(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear cavall",
                "Conversio a char",
                "Comprova moviment valid"};
    }

    public static void main(String[] args) {
        DriverCavall driver = new DriverCavall(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && k == null) {
            System.out.println("ERROR: CAVALL NULL");
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
        k = new Cavall(x, y, c);
        c = k.getColor();
        if(c == Color.BLANC) color = "BLANC";
        else if(c == Color.NEGRE) color = "NEGRE";
        else {
            System.out.println("ERROR: COLOR NO VALID");
            return;
        }
        System.out.printf("Creat: x: %d, y: %d, Color: %s\n", k.getPosicio().getKey(), k.getPosicio().getValue(), color);
    }

    public void testToChar() {
        System.out.println(k.toChar());
    }

    public void testEsMovimentValid() {
        optPrint("mata: ");
        boolean mata = scan.nextBoolean();
        optPrint("x: ");
        int x = scan.nextInt();
        scan.nextLine();
        optPrint("y: ");
        int y = scan.nextInt();
        boolean res = k.esMovimentValid(mata, x, y);
        System.out.println(res);
    }
}