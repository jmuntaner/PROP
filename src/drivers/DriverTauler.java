package drivers;

import domain.Tauler;
import domain.Peca;
import domain.Rei;
import domain.Reina;
import domain.Alfil;
import domain.Torre;
import domain.Cavall;
import domain.Peo;
import domain.Color;
import domain.Moviment;

import java.util.ArrayList;
import javafx.util.Pair;

public class DriverTauler extends GenericDriver{
    private Tauler t;
    private Moviment m;
    private boolean lastTestMou;

    DriverTauler(String [] args) {
        super(args);
        opcionsMenu = new String [] {
                "Test constructor",
                "Test constructor 2",
                "test afegir peça",
                "test treure peça",
                "test mou",
                "test mou invers",
                "test obté moviments jugador",
                "test obté moviments peça",
                "test get casella",
                "test final entrada tauler",
                "test comprova sol aux"
        };
    }

    public static void main(String [] args) {
        DriverTauler driver = new DriverTauler(args);
        driver.runLoop();
        System.out.println(); //Línia en blanc entre tests
    }

    @Override
    void runTest(int option) {
        if (option!=1 && option!=2 && t==null) {
            System.out.println("ERROR: TAULER NULL");
            return;
        }
        if (option==6 && !lastTestMou) {
            System.out.println("Per invertir moviment, fes-ne un immediatament abans");
            return;
        }
        lastTestMou = false;
        switch(option) {
            case 1:
                testConstructor();
                break;
            case 2:
                testConstructor2();
                break;
            case 3:
                testAfegirPeca();
                break;
            case 4:
                testTreurePeca();
                break;
            case 5:
                testMou();
                break;
            case 6:
                testMouInvers();
                break;
            case 7:
                testObteMovimentsJugador();
                break;
            case 8:
                testObteMovimentsPeca();
                break;
            case 9:
                testGetCasella();
                //optPrintln("El test de getCasella ja s'inclou en la resta de tests");
                break;
            case 10:
                testFinalEntradaTauler();
                break;
            case 11:
                testComprovaSolAux();
                break;
            default:
                System.out.println("Test no vàlid");
        }
    }

    private Peca getPeca(char c, int x, int y) {
        Color color = Color.BLANC;
        if (c=='-') return null;
        else if (c>='a') {
            color = Color.NEGRE;
            c -= 32;
        }
        switch (c) {
            case 'K':
                return new Rei(x, y, color);
            case 'Q':
                return new Reina(x, y, color);
            case 'R':
                return new Torre(x, y, color);
            case 'B':
                return new Alfil(x, y, color);
            case 'N':
                return new Cavall(x, y, color);
            case 'P':
                return new Peo(x, y, color);
            default:
                throw new RuntimeException(String.format("Tipus de peça desconegut: '%c'.", c));
        }
    }
    private Peca readPeca() {
        int x = scan.nextInt();
        int y = scan.nextInt();
        char c = scan.next().charAt(0);
        scan.nextLine();
        return getPeca(c,x,y);
    }

    private Peca[][] readTauler() {
        String s = scan.nextLine();
        Peca[][] p = new Peca[8][8];
        for (int i = 0; i<s.length(); ++i) {
            p[i/8][i%8] = getPeca(s.charAt(i),i/8,i%8);
        }
        return p;
    }

    private void printTauler() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(t.getCasella(i, j));
            System.out.println();
        }
    }

    private void printMoviment(Moviment mv) {
        if (mv == null) {
            System.out.println("No hi ha moviment");
            return;
        }
        Pair<Integer,Integer> pi = mv.getPosIni();
        Pair<Integer,Integer> pf = mv.getPosFinal();
        char cp = mv.getPecaMoguda().toChar();
        Peca k = mv.getPecaMorta();
        char ck;
        if (k==null) ck = '-';
        else ck = k.toChar();
        System.out.printf("%d %d %d %d %c %c\n",pi.getKey(),pi.getValue(),pf.getKey(),pf.getValue(),cp,ck);
    }

    private void printMoviments(ArrayList<Moviment> movs) {
        if (movs == null) {
            System.out.println("No hi ha peça en la posicio demanada");
            return;
        }
        else if (movs.size()==0) {
            System.out.println("No hi ha moviments possibles");
            return;
        }
        optPrintln("Moviments: ");
        for (Moviment mv: movs) printMoviment(mv);
    }

    private boolean comprovaReis() {
        if (t == null) {
            System.out.println("Crea un tauler");
        }
        boolean w = false;
        boolean b = false;
        for (int i=0; i<8; ++i) {
            for (int j=0;j<8;++j) {
                char c = t.getCasella(i,j);
                if (c=='k' && !b) b = true;
                else if (c=='k') {
                    System.out.println("Hi ha dos reis negres");
                    return false;
                }
                else if (c=='K' && !w) w = true;
                else if (c=='K') {
                    System.out.println("Hi ha dos reis blancs");
                    return false;
                }
            }
        }
        if (w&&b) return true;
        System.out.println("Falta algun rei");
        return false;
    }

    private static Moviment searchMoviment(ArrayList<Moviment> al, int x, int y) {
        for (Moviment mov: al) {
            Pair<Integer,Integer> p = mov.getPosFinal();
            if (p.getKey()==x && p.getValue()==y) return mov;
        }
        return null;
    }

    public void testConstructor() {
        t = new Tauler();
        optPrintln("Tauler buit: ");
        printTauler();
    }

    public void testConstructor2() {
        optPrint("Introdueix un tauler: ");
        Peca[][] p = readTauler();
        try {
            t = new Tauler(p);
            optPrintln("Tauler introduït: ");
            printTauler();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testAfegirPeca() {
        optPrint("Introdueix una peça (x y codi): ");
        Peca p = readPeca();
        t.afegirPeca(p);
        printTauler();
    }

    public void testTreurePeca() {
        optPrint("Introdueix una posicio: ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();
        try {
            t.treurePeca(x,y);
            printTauler();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    public void testMou() {
        if (!comprovaReis()) return;
        optPrint("Introdueix posicio inicial i final: ");
        int xi = scan.nextInt();
        int yi = scan.nextInt();
        int xf = scan.nextInt();
        int yf = scan.nextInt();
        ArrayList<Moviment> al = t.obteMovimentsPeca(xi,yi);
        if (al == null) {
            System.out.println("No hi ha peça a la casella inicial");
            return;
        }
        m = searchMoviment(al,xf,yf);
        if (m==null) {
            System.out.println("La casella final no és un moviment vàlid");
            return;
        }
        int res = t.mou(m);
        optPrint("Moviment: ");
        printMoviment(m);
        optPrint("Codi resultat: ");
        System.out.println(res);
        optPrintln("Tauler resultant: ");
        printTauler();
        lastTestMou = true;
    }

    public void testMouInvers() { //cal haver fet testMou() immediatament abans -> NO FA MOVIMENT
        t.mouInvers(m);
        optPrint("Moviment: ");
        printMoviment(m);
        optPrintln("Tauler resultant: ");
        printTauler();
    }

    public void testObteMovimentsJugador() {
        optPrint("Introdueix un jugador (W/B): ");
        char c = scan.nextLine().charAt(0);
        Color col;
        if (c=='W') col = Color.BLANC;
        else if (c=='B') col = Color.NEGRE;
        else {
            System.out.println("Jugador incorrecte");
            return;
        }
        ArrayList<Moviment> al = t.obteMovimentsJugador(col);
        printMoviments(al);
    }

    public void testObteMovimentsPeca() {
        optPrint("Introdueix una posicio: ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();
        printMoviments(t.obteMovimentsPeca(x,y));
    }

    public void testGetCasella() {
        optPrint("Introdueix posicio: ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        scan.nextLine();
        optPrint("Casella: ");
        try {
            System.out.println(t.getCasella(x, y));
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testFinalEntradaTauler() {
        optPrint("Introdueix el color del jugador que comença (W/B): ");
        char c = scan.nextLine().charAt(0);
        Color col;
        if (c == 'W') col = Color.BLANC;
        else if (c=='B') col = Color.NEGRE;
        else {
            System.out.println("Color incorrecte");
            return;
        }
        optPrint("Resultat: ");
        System.out.println(t.finalEntradaTauler(col));
    }

    public void testComprovaSolAux() {
        int[] data = new int[]{0,0};
        optPrint("Introdueix torn i tema (W/B), la jugada actual i el màxim de jugades: ");
        char c1 = scan.next().charAt(0);
        Color torn;
        if (c1 == 'W') torn = Color.BLANC;
        else if (c1 == 'B') torn = Color.NEGRE;
        else {
            System.out.println("Torn invàlid");
            return;
        }
        char c2 = scan.next().charAt(0);
        Color tema;
        if (c2 == 'W') tema = Color.BLANC;
        else if (c2 == 'B') tema = Color.NEGRE;
        else {
            System.out.println("Tema invàlid");
            return;
        }
        int n = scan.nextInt();
        int nmax = scan.nextInt();
        t.comprovaSolAux(torn,tema,n,nmax,data);
        optPrint("Resultat (número de solucions i decisions vàlides): ");
        System.out.printf("%d %d\n", data[0], data[1]);
    }
}
