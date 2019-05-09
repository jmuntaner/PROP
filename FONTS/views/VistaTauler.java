package views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;


public class VistaTauler extends JPanel {
    private TaulerListener tl;

    private static final String llistaFitxes = "KQRNBPkqrnbp";
    ImageIcon empty;
    private ImageIcon[] fitxes;
    private VistaCasella[][] caselles;

    VistaTauler(TaulerListener taulerListener) {
        this();
        tl = taulerListener;
        for (VistaCasella[] f : caselles)
            for (VistaCasella vc : f)
                if (vc != null) vc.setInteractable(true);
    }

    VistaTauler() {
        super();
        caselles = new VistaCasella[8][8];
        fitxes = new ImageIcon[llistaFitxes.length()];

        setLayout(new GridLayout(8, 8));

        generaImatges();
        System.out.println("Fichas generadas");

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
                generaImatges();
            }
        });
    }

    void clica(int x, int y) {
        if (tl != null) tl.clicPeca(x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d;
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            throw new RuntimeException();
            //return new Dimension(1, 1);
        }
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);
        return new Dimension(s, s);
    }

    private void generaImatges() {
        int lado = getWidth() / 9;
        if (lado == 0) lado = 10;
        empty = new ImageIcon(new BufferedImage(lado, lado, BufferedImage.TYPE_INT_ARGB));
        for (int i = 0; i < llistaFitxes.length(); i++) {
            char ficha = llistaFitxes.charAt(i);
            fitxes[i] = Utils.getIconPeca(ficha, lado);
        }
        for (VistaCasella[] f : caselles)
            for (VistaCasella vc : f)
                if (vc != null) vc.updateImatge();
    }

    ImageIcon getIconFitxa(char type) {
        int index = llistaFitxes.indexOf(type);
        if (index != -1) {
            return fitxes[index];
        } else return empty;
    }

    void setFitxa(int x, int y, char fitxa) {
        caselles[x][y].setPeca(fitxa);
    }

    void borraFitxa(int x, int y) {
        caselles[x][y].borraPeca();
    }

    char getFitxa(int x, int y) {
        return caselles[x][y].getPeca();
    }

    interface TaulerListener {
        void clicPeca(int x, int y);
    }
}
