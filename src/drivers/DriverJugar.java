package drivers;

import domain.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//TODO:
// - Operativa (per exemple, especificar que la funcionalitat de comprovar solucio es fa quan afegeixes un problema)
// - HvH, HvM, MvM
public class DriverJugar {
    private static final String[] menuPrincipal = new String[]{"Menu principal", "Gestionar problemes", "Jugar", "Sortir"};
    private static final String[] menuGestio = new String[]{"Gestió de problemes", "Llista", "Afegir", "Eliminar", "Tornar"};
    private static final String[] menuJugar = new String[]{"Jugar", "Humà vs Humà", "Humà vs Màquina", "Màquina vs Màquina", "Tornar"};
    private int mode;
    private boolean end = false;
    private Scanner scan;
    private ArrayList<Problema> problemes;

    private DriverJugar() {
        mode = 0;
        scan = new Scanner(System.in);  // Create a Scanner object
        problemes = new ArrayList<>();
    }

    public static void main(String[] args) {
        DriverJugar d = new DriverJugar();
        d.loop();
    }

    private int lecturaInt(String prompt) {
        int nj = -1;
        boolean ok = false;
        while (!ok) {
            System.out.print(prompt);
            if (scan.hasNextInt()) {
                nj = scan.nextInt();
                ok = true;
            } else System.out.println("Numero incorrecte, intenta de nou.");
            scan.nextLine();
        }
        return nj;
    }

    private void pintaMenu(String[] menu) {
        int maxlen = 0;
        for (String s : menu) {
            if (s.length() > maxlen) maxlen = s.length();
        }

        maxlen += Integer.toString(menu.length).length();
        char[] barra = new char[maxlen + 5];
        Arrays.fill(barra, '-');

        System.out.println(barra);
        char[] esptitle = new char[maxlen - menu[0].length() + 1];
        Arrays.fill(esptitle, ' ');
        System.out.printf("|  %s%s|\r\n", menu[0], new String(esptitle));
        System.out.println(barra);
        for (int i = 1; i < menu.length; i++) {
            char[] espi = new char[maxlen - menu[i].length()];
            Arrays.fill(espi, ' ');
            System.out.printf("|%d. %s%s|\r\n", i, menu[i], new String(espi));
        }
        System.out.println(barra);
    }

    private void printTauler(Tauler t) {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i);
            for (int j = 0; j < 8; j++) {
                System.out.print(" ");
                System.out.print(t.getCasella(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    private void execMenuPrincipal() {
        pintaMenu(menuPrincipal);
        int option = lecturaInt("Opció triada: ");
        switch (option) {
            case 1:
                mode = 1;
                break;
            case 2:
                if (problemes.size()==0) {
                    System.out.println("Cal almenys un problema per poder jugar");
                    return;
                }
                mode = 2;
                break;
            case 3:
                System.out.println();
                System.out.println("---- Adeu! ----");
                System.out.println();
                end = true;
                break;
            default:
                System.out.println("Opció incorrecta");
        }

    }

    private void execGestio() {
        pintaMenu(menuGestio);
        int option = lecturaInt("Opció triada: ");
        switch (option) {
            case 1:
                llistaProblemes();
                break;
            case 2:
                afegirProblema();
                break;
            case 3:
                eliminarProblema();
                break;
            case 4:
                mode = 0;
                break;
            default:
                System.out.println("Opció incorrecta");
        }
    }

    private void execJugar() {
        pintaMenu(menuJugar);
        int option = lecturaInt("Opció triada: ");
        switch (option) {
            case 1:
                partidaHH();
                break;
            case 2:
                partidaHM();
                break;
            case 3:
                partidaMM();
                break;
            case 4:
                mode = 0;
                break;
            default:
                System.out.println("Opcio incorrecta");
        }
    }

    private Problema seleccioProblema() {
        System.out.println("Selecciona un problema:");
        int i = 1;
        for (Problema p : problemes) {
            System.out.printf("%d. %s\r\n", i++, p.getNom());
        }
        System.out.println();
        boolean valid = false;
        int el = -1;
        while (!valid) {
            el = lecturaInt("Problema: ");
            if (el <= 0 || el > problemes.size()) System.out.println("El problema especificat no existeix.");
            else valid = true;

        }
        return problemes.get(el - 1);
    }

    private void partidaMM() {
        Problema prob = seleccioProblema();
        Partida p = new Partida(prob);
        System.out.println("Encara no es pot jugar.");
    }

    private void partidaHM() {
        System.out.print("Nom del jugador: ");
        String nom = scan.nextLine();
        Problema prob = seleccioProblema();
        Partida p = new Partida(prob);
        System.out.println("Encara no es pot jugar.");
    }

    private boolean processResultMou(int result) {
        switch (result) {
            case 0:
                System.out.println("Moviment realitzat");
                return false;
            case 1:
                System.out.println("Escac!");
                return false;
            case 2:
                System.out.println("Escac Mat!");
                return true;
            case 3:
            case 4:
                System.out.println("Taules!");
                return true;
            default:
                System.out.printf("WARNING: Codi de resultat %d desconegut", result);
                return false;
        }
    }

    private void partidaHH() {
        System.out.print("Nom del jugador atacant: ");
        String nom1 = scan.nextLine();
        System.out.print("Nom del jugador defensor: ");
        String nom2 = scan.nextLine();
        Problema prob = seleccioProblema();
        Partida p = new Partida(prob);

        Color tema = prob.getTema();
        int maxMovs = prob.getNumJugades();
        boolean fi = false;
        int result = -1;
        while (!fi) {
            printTauler(p.getSituacioActual());
            System.out.printf("Torn de %s (%s):\r\n",
                    tema == p.getTorn() ? nom1 : nom2,
                    p.getTorn() == Color.BLANC ? "blanques" : "negres");
            System.out.print("Peça a moure (x y): ");
            int xo = scan.nextInt();
            int yo = scan.nextInt();
            scan.nextLine();
            if (p.getAtPosicio(xo, yo) == '-') System.out.printf("No hi a cap peça a la posició.");
            else if (Character.isUpperCase(p.getAtPosicio(xo, yo)) != (p.getTorn() == Color.BLANC))
                System.out.println("Error: la peça no es del teu color.");
            else {
                ArrayList<Moviment> movs = p.obteMovimentsPosicio(xo, yo);
                if (!movs.isEmpty()) {
                    for (int i = 0; i < movs.size(); i++) {
                        Moviment m = movs.get(i);
                        Pair<Integer, Integer> fin = m.getPosFinal();
                        System.out.printf("%d. %d,%d\r\n", i + 1, fin.getKey(), fin.getValue());
                    }
                    int index = lecturaInt("Selecciona un moviment: ");
                    Moviment mov = movs.get(index - 1);
                    result = p.moure(p.getTorn(), mov);
                    fi = processResultMou(result);
                } else System.out.println("No hi ha moviments possibles");
            }

            if (p.getNumMoviments() >= maxMovs) fi = true;
        }
        System.out.println();
        System.out.println("Partida finalitzada");
        if (result == 2) {
            System.out.print("El guanyador es: ");
            if (p.getTorn() == prob.getTema()) System.out.println(nom2);
            else System.out.println(nom1);
        } else if (p.getNumMoviments() >= maxMovs) System.out.println("S'ha arribat al limit de moviments.");
        scan.nextLine();
    }

    private void llistaProblemes() {
        System.out.println();
        if (problemes.isEmpty()) {
            System.out.println("-- No hi ha problemes --");
        } else {
            int i = 1;
            for (Problema p : problemes) {
                System.out.printf("%d. %s\r\n", i++, p.getNom());
            }
        }
        System.out.println();
        System.out.println("Prem Enter per continuar");
        scan.nextLine();
    }

    private void afegirProblema() {
        System.out.println();
        System.out.print("Nom del problema: ");
        String nom = scan.nextLine();
        Problema p = new Problema(nom);
        int nj = lecturaInt("Numero de jugades: ");
        System.out.print("FEN: ");
        String fen = scan.nextLine();
        Tauler t = FenTranslator.generaTauler(fen);
        Color c = FenTranslator.getColor(fen);
        if (p.initProblema(c, nj, t)) {
            problemes.add(p);
            System.out.println("Problema afegit correctament.");
        } else System.out.println("Error: El problema no te solució.");

        System.out.println();
        System.out.println("Prem Enter per continuar");
        scan.nextLine();
    }

    private void eliminarProblema() {
        System.out.println();
        if (problemes.isEmpty()) {
            System.out.println("No hi ha cap problema a eliminar.");
            System.out.println();
            System.out.println("Prem Enter per continuar");
            scan.nextLine();
            return;
        }
        int i = 1;
        for (Problema p : problemes) {
            System.out.printf("%d. %s\r\n", i++, p.getNom());
        }
        System.out.println();
        boolean valid = false;
        int el = -1;
        while (!valid) {
            el = lecturaInt("Problema a eliminar: ");
            if (el <= 0 || el > problemes.size()) System.out.println("El problema especificat no existeix.");
            else valid = true;

        }
        problemes.remove(el - 1);
        System.out.println("Problema eliminat correctament.");
        System.out.println();
        System.out.println("Prem Enter per continuar");
        scan.nextLine();
    }

    private void loop() {
        while (!end) {
            switch (mode) {
                case 1:
                    execGestio();
                    break;
                case 2:
                    execJugar();
                    break;
                default:
                    execMenuPrincipal();
            }
        }
    }
}
