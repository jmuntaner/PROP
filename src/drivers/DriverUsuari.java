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
    boolean runTest(int option) {
        switch (option) {
            case 1:
                return testConstructor();
            case 2:
                return testCheckPass();
            case 3:
                return testGetId();
            case 4:
                return testSetNom();
            case 5:
                return testSetPass();
            case 6:
                return testSuperaProblema();
            default:
                System.out.println("Test no valid");
                return false;
        }
    }

    public boolean testConstructor() {
        print("Id: ");
        int id = scan.nextInt();
        scan.nextLine();
        print("Nom: ");
        String nom = scan.nextLine();
        print("Contrasenya: ");
        String pass = scan.nextLine();
        u = new Usuari(id, nom, pass);
        printf("Creat: Id: %d, Nom: %s\r\n", u.getId(), u.getNom());
        return true;
    }

    public boolean testCheckPass() {
        if (u == null) return false;
        print("Contrasenya: ");
        String pass = scan.nextLine();
        return u.checkPass(pass);
    }

    public boolean testGetId() {
        if (u == null) return false;
        println(u.getId());
        return true;
    }

    public boolean testSetNom() {
        print("Nom nou: ");
        String nom = scan.nextLine();
        u.setNom(nom);
        return u.getNom().equals(nom);
    }

    public boolean testSetPass() {
        print("Contrasenya: ");
        String pass = scan.nextLine();
        u.setPass(pass);
        return u.checkPass(pass);
    }

    public boolean testSuperaProblema() {
        print("Problema a superar: ");
        int id = scan.nextInt();
        scan.nextLine();
        u.superaProblema(id);
        Set<Integer> s = u.getProblemesSuperats();

        if (!s.contains(id)) {
            System.out.println("Error: El problema no està a problemesSuperats");
            return false;
        }
        return true;
    }
}
