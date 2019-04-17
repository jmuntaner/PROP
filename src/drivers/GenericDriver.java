package drivers;

import java.util.Scanner;

abstract class GenericDriver {
    final Scanner scan;
    private final String myname;
    String[] opcionsMenu;
    private boolean silent;

    GenericDriver(String[] args) {
        scan = new Scanner(System.in);  // Create a Scanner object
        opcionsMenu = new String[]{"-"};
        silent = false;
        myname = this.getClass().getSimpleName();
        if (args.length == 1) {
            if (args[0].equals("-q")) {
                silent = true;
            } else System.out.printf("Usage: java drivers.%s.%s [-q] [< input.txt]\r\n", myname, myname);
        }
    }

    private int menu() {
        if (!silent) {
            System.out.println();
            System.out.println(myname);
            System.out.println("Tria una opció:");
            int index = 1;
            for (String opcio : opcionsMenu) {
                System.out.printf(" %d: %s\r\n", index, opcio);
                index++;
            }
            System.out.println(" 99: Sortir");
            System.out.print("Opció triada: ");
        }
        int opcio = scan.nextInt();
        scan.nextLine(); // Acaba de llegir la linea del int per a deixar net

        if (opcio < 1 || (opcio != 99 && opcio > opcionsMenu.length)) {
            System.out.printf("---- Opció %d no valida ----\r\n", opcio);
            return menu();
        }

        return opcio;
    }

    void optPrintln(String text) {
        if (!silent) System.out.println(text);
    }

    void optPrint(String text) {
        if (!silent) System.out.print(text);
    }

    void optPrintf(String format, Object... args) {
        optPrint(String.format(format, args));
    }

    abstract void runTest(int option);

    void runLoop() {
        while (true) {
            int opt = menu();
            if (opt == 99) return;

            runTest(opt);

        }
    }
}
