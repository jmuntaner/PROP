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
    private static final Color COL_CLAR_POSSIBLE = new Color(206, 59, 49);
    private static final Color COL_FOSC_POSSIBLE = new Color(139, 23, 20);
    private static final Color COL_CLAR_POSSIBLE_HOVER = new Color(230, 86, 78);
    private static final Color COL_FOSC_POSSIBLE_HOVER = new Color(163, 43, 41);
    private static final Color COL_FOSC_SELEC = new Color(120, 33, 29);
    private static final Color COL_CLAR_SELEC = new Color(171, 64, 53);
    private int x, y;
    private char peca;
    private JLabel labelPeca;
    private VistaTauler vt;
    private boolean hovering, interactable, selected, possible;

    VistaCasella(VistaTauler vt, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.vt = vt;
        this.peca = '-';
        this.hovering = false;
        this.possible = false;
        this.selected = false;
        //add(new JButton(" "));
        Color c;
        if ((x + y) % 2 == 0) c = COL_CLAR;
        else c = COL_FOSC;
        setBackground(c);
        setSize(100, 100);

        addMouseListener(new CasellaMouseAdapter(x, y, vt));
        initImage();

    }

    char getPeca() {
        return this.peca;
    }

    void setPeca(char peca) {
        this.peca = peca;
        updateImatge();
    }

    void updateImatge() {
        labelPeca.setIcon(vt.getIconFitxa(this.peca));
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


    void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    void setColSelected() {
        selected = true;
        updateColor();
    }

    void setColPossible() {
        possible = true;
        updateColor();

    }

    void clearCol() {
        setBorder(null);
        selected = false;
        possible = false;
        updateColor();
    }

    private void updateColor() {
        Color c;
        if ((x + y) % 2 == 0) {
            if (selected) c = COL_CLAR_SELEC;
            else if (hovering) {
                if (possible) c = COL_CLAR_POSSIBLE_HOVER;
                else c = COL_CLAR_HOVER;
            } else {
                if (possible) c = COL_CLAR_POSSIBLE;
                else c = COL_CLAR;
            }
        } else {
            if (selected) c = COL_FOSC_SELEC;
            else if (hovering) {
                if (possible) c = COL_FOSC_POSSIBLE_HOVER;
                else c = COL_FOSC_HOVER;
            } else {
                if (possible) c = COL_FOSC_POSSIBLE;
                else c = COL_FOSC;
            }
        }
        setBackground(c);
    }

    private class CasellaMouseAdapter extends MouseAdapter {
        private final int x;
        private final int y;
        private final VistaTauler vt;

        CasellaMouseAdapter(int x, int y, VistaTauler vt) {
            this.x = x;
            this.y = y;
            this.vt = vt;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if (!interactable) return;
            hovering = true;
            updateColor();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if (!interactable) return;
            hovering = false;
            updateColor();
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
            updateColor();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (!interactable) return;
            vt.clica(x, y);
        }
    }
}
