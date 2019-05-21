package views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Representació gràfica d'un tauler d'escacs.
 */
public class VistaTauler extends JPanel {
    private TaulerListener tl;
    private ImageIcon empty;
    private ImageIcon[] fitxes;
    private VistaCasella[][] caselles;
    private ExecutorService executor;
    private int lastLado;

    /**
     * Creadora de tauler interactiu.
     *
     * @param taulerListener Listener de clics a peces.
     */
    VistaTauler(TaulerListener taulerListener) {
        this();
        tl = taulerListener;
        setInteractable(true);
    }

    /**
     * Creadora per defecte.
     */
    VistaTauler() {
        super();
        lastLado = -1;
        caselles = new VistaCasella[8][8];
        fitxes = new ImageIcon[Utils.llistaFitxes.length()];

        executor = Executors.newSingleThreadExecutor();

        setLayout(new GridLayout(8, 8));

        generaImatges();

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                VistaCasella vc = new VistaCasella(this, i, j);
                caselles[i][j] = vc;
                add(vc);
            }
        setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 5));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if (isShowing()) generaImatges();
            }

        });
    }

    /**
     * Gestiona el clic a una peça del tauler.
     *
     * @param x Posició X de la peça.
     * @param y posició Y de la peça.
     */
    void clica(int x, int y) {
        if (tl != null) tl.clicPeca(x, y);
    }

    /**
     * Obté la mida del tauler.
     * <p>
     * A partir de les mides del pare, calcula i retorna una mida quadrada continguda en aquest.
     *
     * @return Dimensió del tauler.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension d;
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            throw new RuntimeException();
        }
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);
        return new Dimension(s, s);
    }

    /**
     * Genera i actualitza les imatges escalades de les diferents peces.
     */
    private void generaImatges() {
        executor.submit(() -> {
            int lado = getWidth() * 2 / (getWidth() < 300 ? 25 : 18);
            lastLado = lado;
            if (lado == 0) lado = 5;
            empty = new ImageIcon(new BufferedImage(lado, lado, BufferedImage.TYPE_INT_ARGB));
            for (int i = 0; i < Utils.llistaFitxes.length(); i++) {
                char ficha = Utils.llistaFitxes.charAt(i);
                fitxes[i] = Utils.getIconPeca(ficha, lado);
            }
            SwingUtilities.invokeLater(() -> {
                for (VistaCasella[] f : caselles)
                    for (VistaCasella vc : f)
                        if (vc != null) vc.updateImatge();
            });
        });
    }

    /**
     * Obté la imatge d'una peça.
     *
     * @param type Tipus de peça.
     * @return Imatge de la peça.
     */
    ImageIcon getIconFitxa(char type) {
        int index = Utils.llistaFitxes.indexOf(type);
        if (index != -1) {
            return fitxes[index];
        } else return empty;
    }

    /**
     * Modifica la peça d'una casella.
     *
     * @param x     Posició X de la casella.
     * @param y     Posició Y de la casella.
     * @param fitxa Tipus de peça a colocar.
     */
    void setFitxa(int x, int y, char fitxa) {
        if (lastLado != getWidth() * 2 / (getWidth() < 300 ? 25 : 18)) generaImatges();
        caselles[x][y].setPeca(fitxa);
    }

    /**
     * Borra la peça d'una casella.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void borraFitxa(int x, int y) {
        caselles[x][y].borraPeca();
    }

    /**
     * Obté la peça situada a una casella.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     * @return El codi fen de la peça situada a (x,y). En cas d'estar buit, '-'.
     */
    char getFitxa(int x, int y) {
        return caselles[x][y].getPeca();
    }

    /**
     * Marca una casella com a seleccionada.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void seleccionaPos(int x, int y) {
        caselles[x][y].setColSelected();
    }

    /**
     * Marca una casella com a possible posició de moviment.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void marcaPos(int x, int y) {
        caselles[x][y].setColPossible();
    }

    /**
     * Torna una casela al seu color per defecte.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void desmarcaPos(int x, int y) {
        caselles[x][y].clearCol();
    }

    /**
     * Activa o desactiva l'interacció amb el tauler.
     *
     * @param interactable Estat d'interacció desitjat.
     */
    void setInteractable(boolean interactable) {
        for (VistaCasella[] f : caselles)
            for (VistaCasella vc : f)
                if (vc != null) vc.setInteractable(interactable);
    }

    /**
     * Interfície per a Listeners d'events de tauler.
     */
    interface TaulerListener {
        /**
         * Gestiona els clics a una casella.
         *
         * @param x Posició X de la casella.
         * @param y Posició Y de la casella.
         */
        void clicPeca(int x, int y);
    }
}
