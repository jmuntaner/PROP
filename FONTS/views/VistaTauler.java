package views;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class VistaTauler extends JPanel {

    private static final String llistaFitxes = "KQRNBPkqrnbp";
    ImageIcon empty;
    private ImageIcon[] fitxes;
    private VistaCasella[][] caselles;

    VistaTauler() {
        super();
        caselles = new VistaCasella[8][8];
        fitxes = new ImageIcon[llistaFitxes.length()];

        setLayout(new GridBagLayout());

        generaImatges();
        System.out.println("Fichas generadas");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                VistaCasella vc = new VistaCasella(this, i, j);
                caselles[i][j] = vc;
                gbc.gridx = j;
                gbc.gridy = i;
                add(vc, gbc);
            }
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                generaImatges();
            }
        });

        // Algunes fitxes
        setFitxa(1, 1, 'k');
        setFitxa(2, 3, 'q');
        setFitxa(6, 5, 'K');
        setFitxa(7, 3, 'Q');
        setFitxa(4, 4, 'P');
        setFitxa(5, 2, 'B');
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
            try {
                // Source: https://marcelk.net/chess/pieces/
                BufferedImage orig = ImageIO.read(new File("res/piezas/" + ficha + ".png"));
                fitxes[i] = new ImageIcon(orig.getScaledInstance(lado, lado, Image.SCALE_SMOOTH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (VistaCasella[] f : caselles)
            for (VistaCasella vc : f)
                if (vc != null) vc.updateImatge();
    }

    ImageIcon getFicha(char type) {
        int index = llistaFitxes.indexOf(type);
        if (index != -1) {
            return fitxes[index];
        } else return empty;
    }

    void setFitxa(int x, int y, char fitxa) {
        caselles[x][y].setPeca(fitxa);
    }
}
