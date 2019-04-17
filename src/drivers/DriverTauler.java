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
                "test get casella (no implementat)",
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
                //testGetCasella();
                optPrintln("El test de getCasella ja s'inclou en la resta de tests");
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

    private static void printMoviments(ArrayList<Moviment> movs) {
        if (movs == null) {
            System.out.println("No hi ha peça en la posicio demanada");
            return;
        }
        else if (movs.size()==0) {
            System.out.println("No hi ha moviments possibles");
            return;
        }
        for (Moviment mv: movs) {
            Pair<Integer,Integer> pi = mv.getPosIni();
            Pair<Integer,Integer> pf = mv.getPosFinal();
            char cp = mv.getPecaMoguda().toChar();
            Peca k = mv.getPecaMorta();
            char ck;
            if (k==null) ck = '-';
            else ck = k.toChar();
            System.out.printf("%d %d %d %d %c %c\n",pi.getKey(),pi.getValue(),pf.getKey(),pf.getValue(),cp,ck);
        }
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

    public void testMou() { //El moviment no té perquè ser vàlid (però com a mínim hi ha d'haver reis al tauler)
        if (!comprovaReis()) return;
        optPrint("Introdueix posicio inicial i final: ");
        int xi = scan.nextInt();
        int yi = scan.nextInt();
        int xf = scan.nextInt();
        int yf = scan.nextInt();
        if (t.getCasella(xi,yi)=='-') {
            System.out.println("No hi ha cap peça a la casella inicial");
            return;
        }
        Peca p = getPeca(t.getCasella(xi,yi),xi,yi);
        m = new Moviment(p,xf,yf);
        t.mou(m);
        optPrintln("Tauler resultant: ");
        printTauler();
        lastTestMou = true;
    }

    public void testMouInvers() { //cal haver fet testMou() immediatament abans -> NO FA MOVIMENT
        t.mouInvers(m);
        ArrayList<Moviment> al = new ArrayList<>();
        al.add(m);
        printMoviments(al);
        optPrintln("Tauler resultant: ");
        printTauler();
    }

    public void testObteMovimentsJugador() { //NO IMPRIMEIX RES
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

    public void testObteMovimentsPeca() {}

    //public void testGetCasella() {}
    //getCasella ja es fa servir a la resta de funcions, per tant ja queda testejat

    public void testFinalEntradaTauler() {}

    public void testComprovaSolAux() {}

}
