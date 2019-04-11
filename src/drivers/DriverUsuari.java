package drivers;

import domain.Usuari;

import java.util.Scanner;

public class DriverUsuari {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        boolean fi = false;
        while (!fi) {
            System.out.println("Driver usuari:");
            System.out.println(" 1. Crear usuari");
            System.out.println(" 99. Sortir");

            System.out.print("Elecció: ");
            int entrada = scan.nextInt();
            scan.nextLine(); //Skip intro from int
            switch (entrada) {
                case 1:
                    System.out.print("Nom: ");
                    String nom = scan.nextLine();
                    System.out.print("Contrasenya: ");
                    String pass = scan.nextLine();
                    Usuari user = new Usuari(1, nom, pass);
                    System.out.printf("Id: %d, Nom: %s\n\n", user.getId(), user.getNom());
                    break;
                case 99:
                    fi = true;
                    break;
                default:
                    System.out.println("Acció no soportada.");
            }
        }
    }
}
