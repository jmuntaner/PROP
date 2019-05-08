package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VistaCasella extends JPanel {
    private static final Color COL_FOSC = new Color(125, 135, 150);
    private static final Color COL_CLAR = new Color(232, 235, 239);
    private static final Color COL_FOSC_HOVER = new Color(85, 95, 110);
    private static final Color COL_CLAR_HOVER = new Color(192, 195, 199);
    private static final Color COL_FOSC_CLICK = new Color(55, 65, 80);
    private static final Color COL_CLAR_CLICK = new Color(162, 165, 169);
    private int x, y;
    private char peca;
    private JLabel labelPeca;
    private VistaTauler vt;
    private boolean hovering;
    private boolean interactable;

    VistaCasella(VistaTauler vt, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.vt = vt;
        this.peca = '-';
        this.hovering = false;
        //add(new JButton(" "));
        Color c;
        if ((x + y) % 2 == 0) c = COL_CLAR;
        else c = COL_FOSC;
        setBackground(c);
        setSize(100, 100);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (!interactable) return;
                Color c;
                if ((x + y) % 2 == 0) c = COL_CLAR_HOVER;
                else c = COL_FOSC_HOVER;
                setBackground(c);
                hovering = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (!interactable) return;
                Color c;
                if ((x + y) % 2 == 0) c = COL_CLAR;
                else c = COL_FOSC;
                setBackground(c);
                hovering = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!interactable) return;
                Color c;
                if ((x + y) % 2 == 0) c = COL_CLAR_CLICK;
                else c = COL_FOSC_CLICK;
                setBackground(c);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (!interactable) return;
                Color c;
                if ((x + y) % 2 == 0) c = hovering ? COL_CLAR_HOVER : COL_CLAR;
                else c = hovering ? COL_FOSC_HOVER : COL_FOSC;
                setBackground(c);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!interactable) return;
                vt.clica(x, y);
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


    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }
}
