package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VistaCasella extends JPanel {
    private static final Color BLACK = new Color(125, 135, 150);
    private static final Color WHITE = new Color(232, 235, 239);
    private static final Color HOVER = new Color(100, 100, 100);

    private int x, y;
    private char peca;
    private JLabel labelPeca;
    private VistaTauler vt;

    VistaCasella(VistaTauler vt, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.vt = vt;
        this.peca = '-';
        //add(new JButton(" "));
        Color c;
        if ((x + y) % 2 == 0) c = WHITE;
        else c = BLACK;
        setBackground(c);
        setSize(100, 100);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Color c;
                if ((x + y) % 2 == 0) c = WHITE;
                else c = BLACK;
                setBackground(c);
            }
        });
        initImage();

    }

    void setPeca(char peca) {
        this.peca = peca;
        updateImatge();
    }

    void updateImatge() {
        labelPeca.setIcon(vt.getFicha(this.peca));
    }


    private void initImage() {
        labelPeca = new JLabel();
        updateImatge();
        add(labelPeca);
    }

    void borraPeca() {
        this.peca = '-';
        updateImatge();
    }


}
