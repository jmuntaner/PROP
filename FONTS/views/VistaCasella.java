package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Vista de casella de tauler.
 */
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
    private final int x, y;
    private char peca;
    private JLabel labelPeca;
    private final VistaTauler vt;
    private boolean hovering, interactable, selected, possible;

    /**
     * Creadora per defecte.
     *
     * @param vt Tauler on es troba la casella.
     * @param x  Posició X de la casella.
     * @param y  Posició Y de la casella.
     */
    VistaCasella(VistaTauler vt, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.vt = vt;
        this.peca = '-';
        this.hovering = false;
        this.possible = false;
        this.selected = false;

        Color c;
        if ((x + y) % 2 == 0) c = COL_CLAR;
        else c = COL_FOSC;

        setBackground(c);
        setSize(100, 100);
        addMouseListener(new CasellaMouseAdapter(x, y, vt));
        initImage();

    }

    /**
     * Obté l'identificador de la peça situada a la casella.
     *
     * @return El caràcter identificador de la peça situada a la casella.
     */
    char getPeca() {
        return this.peca;
    }

    /**
     * Estableix la peça situada a la casella
     *
     * @param peca El caràcter identificador de la peça.
     */
    void setPeca(char peca) {
        if (peca != this.peca) {
            this.peca = peca;
            updateImatge();
        }
    }

    /**
     * Borra la peça de la casella.
     */
    void borraPeca() {
        this.peca = '-';
        updateImatge();
    }

    /**
     * Inicialitza la imatge de la casella
     */
    private void initImage() {
        labelPeca = new JLabel();
        updateImatge();
        add(labelPeca);
    }

    /**
     * Actualitza la imatge de la casella.
     */
    void updateImatge() {
        labelPeca.setIcon(vt.getIconFitxa(this.peca));
    }

    /**
     * Activa o desactiva la interacció amb la casella.
     *
     * @param interactable Estat d'interacció.
     */
    void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    /**
     * Estableix el color al de casella selccionada.
     */
    void setColSelected() {
        selected = true;
        updateColor();
    }

    /**
     * Estableix el color al de casella de possible moviment.
     */
    void setColPossible() {
        possible = true;
        updateColor();

    }

    /**
     * Estableix el color al color per defecte.
     */
    void clearCol() {
        selected = false;
        possible = false;
        updateColor();
    }

    /**
     * Actualitza el color de la casella.
     */
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

    /**
     * Adaptador d'events de ratolí de la casella.
     * <p>
     * Gestiona els events de clic i hover del ratolí a la casella, modificant el seu estat convenientment.
     * </p>
     */
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
