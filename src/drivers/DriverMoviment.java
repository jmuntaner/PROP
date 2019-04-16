package drivers;

import domain.Moviment;
import domain.Peca;
import domain.Rei;
import static domain.Color.BLANC;

import java.util.Scanner;
import javafx.util.Pair;

public class DriverMoviment {

    public static void testConstructor() {
        Moviment m = new Moviment();
        System.out.printf("Object reference: %s \n", m.toString());
        if (m.getPecaMoguda()==null && m.getPosIni() == null && m.getPosFinal()==null && m.getPecaMorta()==null)
            System.out.println("Test OK");
        else System.out.println("Test NOT OK");
    }

    public static void testConstructor2(int xi, int yi, int xf, int yf) {
        try {
            Peca p = new Rei(xi, yi, BLANC);
            Moviment m = new Moviment(p, xf, yf);
            System.out.println("Objecte creat");
            System.out.printf("Object reference: %s \n", m.toString());
            if (xi<0 || yi<0 || xf<0 || yf<0 || xi>7 || yi>7 || xf>7 || yf>7) {
                System.out.println("Test NOT OK");
            }
            else if (xi==xf && yi==yf) System.out.println("Test NOT OK");
            else {
                Pair<Integer,Integer> pi = m.getPosIni();
                Pair<Integer,Integer> pf = m.getPosFinal();
                Peca p2 = m.getPecaMoguda();
                if (pi.getKey()==xi && pi.getValue()==yi && pf.getKey()==xf && pf.getValue()==yf && p==p2 && m.getPecaMorta()==null)
                    System.out.println("Test OK");
                else System.out.println("Test NOT OK");
            }
        }
        catch (RuntimeException e) {
            System.out.println("Objecte no creat amb èxit");
            if (xi<0 || yi<0 || xf<0 || yf<0 || xi>7 || yi>7 || xf>7 || yf>7) {
                System.out.println("Test OK");
            }
            else if (xi==xf && yi==yf) System.out.println("Test OK");
            else System.out.println("Test NOT OK");
        }

    }

    public static void testPosFinal(int x, int y) {
        Moviment m = new Moviment();
        Peca r = new Rei(0,0,BLANC); //Posició inicial arbitrària
        m.setPecaMoguda(r);
        boolean exc = false;
        try {
            m.setPosFinal(x,y);
        }
        catch(RuntimeException e) {
            exc = true;
        }

        boolean cond = x < 0 || x > 7 || y < 0 || y > 7;
        Pair<Integer,Integer> pi = m.getPosIni();
        cond = cond || (pi.getKey()==x && pi.getValue()==y);
        if (exc && cond) System.out.println("Test OK");
        else if (exc || cond) System.out.println("Test NOT OK");
        else {
            Pair<Integer,Integer> pf = m.getPosFinal();
            if (pf.getKey()==x && pf.getValue()==y) System.out.println("Test OK");
            else System.out.println("Test NOT OK");
        }
    }

    public static void testPecaMoguda() {
        // Hauria de valer qualsevol peça
        Peca p = new Rei(0,0, BLANC);
        Moviment m = new Moviment();
        m.setPecaMoguda(p);
        if (m.getPecaMoguda()==p) System.out.println("Test OK");
        else System.out.println("Test NOT OK");
    }

    public static void testPecaMorta(int xf, int yf, int xp, int yp) {
        Peca p = new Rei(xp,yp, BLANC);
        Moviment m = new Moviment();
        m.setPosFinal(xf,yf);
        boolean exc = false;
        try {
            m.setPecaMorta(p);
        }
        catch (RuntimeException e){
            exc = true;
        }
        boolean cond = xf!=xp && yf != yp;
        if (exc && cond) System.out.println("Test OK");
        else if (exc || cond) System.out.println("Test NOT OK");
        else {
            Peca k = m.getPecaMorta();
            if (k==p) System.out.println("Test OK");
            else System.out.println("Test NOT OK");
        }
    }

    public static void main (String [] args) {
        Scanner scan = new Scanner(System.in);
        boolean fi = false;

        System.out.println("Driver moviment");
        System.out.println("1. Test Constructor");
        System.out.println("2. Test Constructor Parametritzat");
        System.out.println("3. Test posició final");
        System.out.println("4. Test peça moguda");
        System.out.println("5. Test peça morta");
        System.out.println("99. Sortir");

        while (!fi) {
            System.out.print("Número d'operació: ");
            int entrada = scan.nextInt();
            scan.nextLine();
            int n; // The scope of a switch is the whole switch
            switch(entrada) {
                case 1:
                    testConstructor();
                    break;
                case 2:
                    System.out.print("Número de jocs de prova: ");
                    n = scan.nextInt();
                    scan.nextLine();
                    for (int i=0; i<n; ++i) {
                        System.out.println("Informació necessària");
                        System.out.println("Posició inicial, posició final");
                        System.out.print("Dades: ");
                        int xi = scan.nextInt();
                        int yi = scan.nextInt();
                        int xf = scan.nextInt();
                        int yf = scan.nextInt();
                        scan.nextLine();
                        testConstructor2(xi,yi,xf,yf);
                    }
                    break;
                case 3:
                    System.out.print("Número de jocs de prova: ");
                    n = scan.nextInt();
                    scan.nextLine();
                    for (int i=0; i<n; ++i) {
                        System.out.println("Entra posició final: ");
                        int xf = scan.nextInt();
                        int yf = scan.nextInt();
                        scan.nextLine();
                        testPosFinal(xf, yf);
                    }
                    break;
                case 4:
                    testPecaMoguda();
                    break;
                case 5:
                    System.out.print("Número de jocs de prova: ");
                    n = scan.nextInt();
                    scan.nextLine();
                    for (int i=0; i<n; ++i) {
                        System.out.println("Informació necessària: posició final i posició de la peça morta");
                        System.out.print("Dades: ");
                        int xf = scan.nextInt();
                        int yf = scan.nextInt();
                        int xp = scan.nextInt();
                        int yp = scan.nextInt();
                        scan.nextLine();
                        testPecaMorta(xf,yf,xp,yp);
                    }
                    break;
                case 99:
                    System.out.println("Fi tests");
                    fi = true;
                    break;
                default:
                    System.out.println("Operació incorrecta");
            }
            System.out.println("Operació finalitzada");
        }
    }

}
