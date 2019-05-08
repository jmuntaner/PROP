package views;

import controllers.ControladorEditor;

import javax.swing.*;
import java.awt.*;

public class VistaEditor extends VistaAmbTauler {
    private static final String PECES_BLANQUES = "KQBRKP";
    private static final String PECES_NEGRES = "kqbrkp";
    private boolean colorInicial;
    private JLabel fenLabel;
    private ControladorEditor ce;
    private char actual;

    VistaEditor(VistaPrincipal vp, ControladorEditor ce) {
        super(vp);
        this.ce = ce;
        actual = '-';
        colorInicial = ce.getColorInicial();

        // Init peces
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++) {
                setPos(x, y, ce.getCasella(x, y));
            }
        initDisplays();
    }

    @Override
    JPanel getPanelDreta() {
        initFenLabel();
        JPanel panelSeleccio = new JPanel();
        panelSeleccio.setLayout(new GridBagLayout());

        // Fen de la partida
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSeleccio.add(new JLabel("Fen:"), gbc);
        gbc.gridx = 1;
        panelSeleccio.add(fenLabel, gbc);


        // Selecció de color
        gbc.gridy++;
        gbc.gridx = 0;
        panelSeleccio.add(new JLabel("Color inicial:"), gbc);
        gbc.gridx = 1;
        JPanel panelColors = new JPanel();
        panelColors.add(new JButton("Blanc"));
        panelColors.add(new JButton("Negre"));
        panelSeleccio.add(panelColors, gbc);

        // Peces Blanques
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel panelB = new JPanel();
        for (int i = 0; i < PECES_BLANQUES.length(); i++) {
            char c = PECES_BLANQUES.charAt(i);
            JButton b = new JButton("" + c);
            b.addActionListener(e -> actual = c);
            panelB.add(b);
        }
        panelSeleccio.add(panelB, gbc);

        // Peces Negres
        gbc.gridy++;
        JPanel panelN = new JPanel();
        for (int i = 0; i < PECES_NEGRES.length(); i++) {
            char c = PECES_NEGRES.charAt(i);
            JButton b = new JButton("" + c);
            b.addActionListener(e -> actual = c);
            panelN.add(b);
        }
        panelSeleccio.add(panelN, gbc);

        //End gap
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weighty = 1;
        panelSeleccio.add(Box.createGlue(), gbc);
        return panelSeleccio;
    }

    private void initFenLabel() {
        fenLabel = new JLabel(ce.getFen());
    }

    private void updateFenLabel() {
        fenLabel.setText(ce.getFen());
    }

    @Override
    public void clicPeca(int x, int y) {
        setPos(x, y, actual);
    }
}
