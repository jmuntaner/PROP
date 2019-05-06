package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VistaCasella extends JPanel {
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color HOVER = new Color(100, 100, 100);

    private int x, y;
    private char peca;

    VistaCasella(int x, int y) {
        super();
        this.x = x;
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
    }

    void setPeca(char peca) {
        this.peca = peca;
    }

    void borraPeca() {
        this.peca = '-';
    }


}
