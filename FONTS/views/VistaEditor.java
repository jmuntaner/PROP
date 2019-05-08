package views;

import controllers.ControladorEditor;

import javax.swing.*;
import java.awt.*;

class VistaEditor extends VistaAmbTauler {
    private static final String PECES = "KQBRNPkqbrnp";
    private static final int SIZE_ICON = 30;
    private JTextField fenTextField;
    private ControladorEditor ce;
    private char actual;
    private JToggleButton buttonBlanc, buttonNegre;

    VistaEditor(VistaPrincipal vp, ControladorEditor ce) {
        super(vp);
        this.ce = ce;
        actual = '-';

        // Init peces
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++) {
                setPos(x, y, ce.getCasella(x, y));
            }
        initDisplays();
    }

    @Override
    JPanel getPanelDreta() {
        JPanel panelSeleccio = new JPanel();
        panelSeleccio.setLayout(new GridBagLayout());

        // Fen de la partida
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;


        // Selecció de color
        panelSeleccio.add(new JLabel("Color inicial:"), gbc);
        gbc.gridx = 1;
        JPanel panelColors = new JPanel();
        ButtonGroup colorBg = new ButtonGroup();
        buttonBlanc = new JToggleButton("Blanc");
        colorBg.add(buttonBlanc);
        buttonBlanc.addActionListener(e -> {
            ce.setColorInicial(true);
            updateFenLabel();
        });
        buttonNegre = new JToggleButton("Negre");
        colorBg.add(buttonNegre);
        buttonNegre.addActionListener(e -> {
            ce.setColorInicial(false);
            updateFenLabel();
        });

        if (ce.getColorInicial()) buttonBlanc.setSelected(true);
        else buttonNegre.setSelected(true);

        panelColors.add(buttonBlanc);
        panelColors.add(buttonNegre);
        panelSeleccio.add(panelColors, gbc);

        // Peces Blanques

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel panelB = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < PECES.length(); i++) {

            char c = PECES.charAt(i);
            JToggleButton b = new JToggleButton(Utils.getIconPeca(c, SIZE_ICON));
            b.addActionListener(e -> {
                actual = c;
                b.setSelected(true);
            });
            panelB.add(b);
            bg.add(b);
            if (i % 3 == 2) {
                gbc.gridy++;
                panelSeleccio.add(panelB, gbc);
                if (i < (PECES.length() - 1)) panelB = new JPanel();
            }
        }

        //End gap
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weighty = 1;
        panelSeleccio.add(Box.createGlue(), gbc);
        return panelSeleccio;
    }

    private void initFenLabel() {
        fenTextField = new JTextField(54);
        fenTextField.setText(ce.getFen());
        fenTextField.addActionListener(e -> {
            if (ce.carregaFen(fenTextField.getText())) {
                for (int y = 0; y < 8; y++)
                    for (int x = 0; x < 8; x++) {
                        setPos(x, y, ce.getCasella(x, y));
                    }
                if (ce.getColorInicial()) buttonBlanc.setSelected(true);
                else buttonNegre.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "El fen que has introduït no és correcte.",
                        "Fen Invalid",
                        JOptionPane.WARNING_MESSAGE);
            }

        });
    }

    private void updateFenLabel() {
        fenTextField.setText(ce.getFen());
    }

    @Override
    public void clicPeca(int x, int y) {
        if (getPos(x, y) != '-') {
            ce.eliminaPeca(x, y);
            borraPos(x, y);
        } else if (actual != '-') {
            ce.afegeixPeca(x, y, actual);
            setPos(x, y, ce.getCasella(x, y));
        }
        updateFenLabel();
    }

    @Override
    JPanel getPanelBotons() {
        // Init class vars
        initFenLabel();

        JPanel panelBotons = new JPanel();

        JButton enrere = new JButton("Tornar");
        enrere.addActionListener(e -> vp.mostraMenuPrincipal());
        panelBotons.add(enrere);

        panelBotons.add(new JLabel("Fen:"));
        panelBotons.add(fenTextField);


        return panelBotons;
    }
}
