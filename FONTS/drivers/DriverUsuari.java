package drivers;

import domain.Usuari;

import java.util.Set;

public class DriverUsuari extends GenericDriver {
    private Usuari u;

    DriverUsuari(String[] args) {
        super(args);
        opcionsMenu = new String[]{
                "Crear usuari",
                "Check contrasenya",
                "Obté ID",
                "Modifica nom",
                "Modifica contrasenya",
                "Supera problema"};
    }

    public static void main(String[] args) {
        DriverUsuari driver = new DriverUsuari(args);
        driver.runLoop();
    }

    @Override
    void runTest(int option) {
        if (option != 1 && u == null) {
            System.out.println("ERROR: USER NULL");
            return;
        }
        switch (option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testCheckPass();
                break;
            case 3:
                testGetId();
                break;
            case 4:
                testSetNom();
                break;
            case 5:
                testSetPass();
                break;
            case 6:
                testSuperaProblema();
                break;
            default:
                System.out.println("Test no valid");
        }
    }

    public void testConstructor() {
        scan.nextLine();
        optPrint("Nom: ");
        String nom = scan.nextLine();
        optPrint("Contrasenya: ");
        String pass = scan.nextLine();
        u = new Usuari(nom, nom, pass);
        System.out.printf("Creat: Id: %s, Nom: %s\r\n", u.getId(), u.getNom());
    }

    public void testCheckPass() {
        optPrint("Contrasenya: ");
        String pass = scan.nextLine();
        System.out.println(u.checkPass(pass));
    }

    public void testGetId() {
        System.out.println(u.getId());
    }

    public void testSetNom() {
        optPrint("Nom nou: ");
        String nom = scan.nextLine();
        u.setNom(nom);
        System.out.println(u.getNom());
    }

    public void testSetPass() {
        optPrint("Contrasenya: ");
        String pass = scan.nextLine();
        u.setPass(pass);
        System.out.println(u.checkPass(pass));
    }

    public void testSuperaProblema() {
        optPrint("Problema a superar: ");
        int id = scan.nextInt();
        scan.nextLine();
        u.superaProblema(id);
        Set<Integer> s = u.getProblemesSuperats();

        optPrintln("Problemes superats");
        for (int idp : s) {
            System.out.println(idp);
        }
    }
}
