package views;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.LINE_START;

/**
 * Classe generica per a vistes amb panel lateral i tauler redimensionable.
 */
abstract class VistaAmbTauler extends JPanel implements VistaTauler.TaulerListener {
    final VistaPrincipal vp;
    private VistaTauler tauler;

    /**
     * Creadora per defecte.
     *
     * @param vp Vista principal.
     */
    VistaAmbTauler(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initTauler();
        initGaps();
    }

    /**
     * Inicialitza els components generics.
     */
    void initDisplays() {
        initPanelDreta();
        initBarraSuperior();
    }

    /**
     * Crea el JPanel d'informació
     *
     * @return JPanel a colocar a la dreta del tauler
     */
    abstract JPanel getPanelDreta();

    /**
     * Inicialitza el panel de la dreta del tauler.
     */
    private void initPanelDreta() {
        JPanel panelDreta = getPanelDreta();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.insets = new Insets(8, 0, 8, 0);
        add(panelDreta, gbc);
    }

    /**
     * Inicialitza els espais entre components
     */
    private void initGaps() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(Box.createGlue(), gbc);
        gbc.gridx = 3;
        add(Box.createGlue(), gbc);
    }

    /**
     * Inicialitza la barra superior.
     */
    private void initBarraSuperior() {
        JPanel panelBotons = getPanelBotons();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.anchor = LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelBotons, gbc);
    }

    /**
     * Genera el panel de botons superior.
     *
     * @return Panel de botons.
     */
    JPanel getPanelBotons() {
        JButton enrere = new JButton("Tornar");
        enrere.addActionListener(e -> vp.mostraMenuPrincipal());
        JPanel panelBotons = new JPanel();
        panelBotons.add(enrere);
        return panelBotons;
    }

    /**
     * Inicialitza el tauler
     */
    private void initTauler() {
        tauler = new VistaTauler(this);
        JPanel temp = new JPanel();
        temp.setLayout(new GridBagLayout());
        temp.add(tauler);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(8, 8, 8, 8);
        add(temp, gbc);

    }

    /**
     * Modifica la peça d'una casella.
     *
     * @param x    Posició X de la casella.
     * @param y    Posició Y de la casella.
     * @param peca Tipus de peça a colocar.
     */
    void setPos(int x, int y, char peca) {
        tauler.setFitxa(x, y, peca);
    }

    /**
     * Obté la peça situada a una casella.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     * @return El codi fen de la peça situada a (x,y). En cas d'estar buit, '-'.
     */
    char getPos(int x, int y) {
        return tauler.getFitxa(x, y);
    }

    /**
     * Borra la peça d'una casella.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void borraPos(int x, int y) {
        tauler.borraFitxa(x, y);
    }

    /**
     * Marca una casella com a seleccionada.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void seleccionaPos(int x, int y) {
        tauler.seleccionaPos(x, y);
    }

    /**
     * Marca una casella com a possible posició de moviment.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void marcaPos(int x, int y) {
        tauler.marcaPos(x, y);
    }

    /**
     * Torna una casela al seu color per defecte.
     *
     * @param x Posició X de la casella.
     * @param y Posició Y de la casella.
     */
    void desmarcaPos(int x, int y) {
        tauler.desmarcaPos(x, y);
    }

    /**
     * Activa o desactiva l'interacció amb el tauler.
     *
     * @param interactable Estat d'interacció desitjat.
     */
    void setInteractable(boolean interactable) {
        tauler.setInteractable(interactable);
    }

    @Override
    public void clicPeca(int x, int y) {
        System.out.printf("Clic a %d, %d\n", x, y);
    }
}

