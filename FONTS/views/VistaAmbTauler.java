package views;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.LINE_START;

abstract class VistaAmbTauler extends JPanel implements VistaTauler.TaulerListener {
    VistaPrincipal vp;
    private VistaTauler tauler;


    VistaAmbTauler(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initTauler();
        initGaps();
    }

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

    private void initGaps() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(Box.createGlue(), gbc);
        gbc.gridx = 3;
        add(Box.createGlue(), gbc);
    }

    private void initBarraSuperior() {
        JPanel panelBotons = getPanelBotons();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.anchor = LINE_START;
        add(panelBotons, gbc);
    }

    private JPanel getPanelBotons() {
        JButton enrere = new JButton("Tornar");
        enrere.addActionListener(e -> vp.mostraMenuPrincipal());
        JPanel panelBotons = new JPanel();
        panelBotons.add(enrere);
        return panelBotons;
    }

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

    void setPos(int x, int y, char peca) {
        tauler.setFitxa(x, y, peca);
    }

    @Override
    public void clicPeca(int x, int y) {
        System.out.printf("Clic a %d, %d\n", x, y);
    }
}

