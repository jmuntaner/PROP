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
    private JToggleButton buttonBlanc, buttonNegre, buttonReiB, buttonReiN;
    private boolean hasReiBlanc, hasReiNegre;
    private ButtonGroup bgPeces;

    VistaEditor(VistaPrincipal vp, ControladorEditor ce) {
        super(vp);
        this.ce = ce;
        actual = '-';

        // Init peces
        initDisplays();
        reloadTauler();
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

        // Peces

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel panelB = new JPanel();
        bgPeces = new ButtonGroup();

        for (int i = 0; i < PECES.length(); i++) {

            char c = PECES.charAt(i);
            JToggleButton b = new JToggleButton(Utils.getIconPeca(c, SIZE_ICON));
            b.addActionListener(e -> {
                actual = c;
                b.setSelected(true);
            });
            if (c == 'K') buttonReiB = b;
            else if (c == 'k') buttonReiN = b;
            panelB.add(b);
            bgPeces.add(b);
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
                reloadTauler();
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

    private void reloadTauler() {
        hasReiBlanc = false;
        hasReiNegre = false;
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++) {
                char c = ce.getCasella(x, y);
                if (c == 'K') {
                    hasReiBlanc = true;
                    updateBotonsRei();
                } else if (c == 'k') {
                    hasReiNegre = true;
                    updateBotonsRei();
                }
                setPos(x, y, c);
            }

    }

    void updateBotonsRei() {
        if (hasReiBlanc) {
            bgPeces.clearSelection();
            buttonReiB.setEnabled(false);
            actual = '-';
        } else buttonReiB.setEnabled(true);

        if (hasReiNegre) {
            bgPeces.clearSelection();
            buttonReiN.setEnabled(false);
            actual = '-';
        } else buttonReiN.setEnabled(true);
    }

    private void updateFenLabel() {
        fenTextField.setText(ce.getFen());
    }

    @Override
    public void clicPeca(int x, int y) {
        char prev = getPos(x, y);
        if (prev != '-') {
            if (prev == 'K') {
                hasReiBlanc = false;
                updateBotonsRei();
            } else if (prev == 'k') {
                hasReiNegre = false;
                updateBotonsRei();
            }
            ce.eliminaPeca(x, y);
            borraPos(x, y);

        } else if (actual != '-') {
            ce.afegeixPeca(x, y, actual);
            char c = ce.getCasella(x, y);
            setPos(x, y, c);
            if (c == 'K') {
                hasReiBlanc = true;
                updateBotonsRei();
            } else if (c == 'k') {
                hasReiNegre = true;
                updateBotonsRei();
            }
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
