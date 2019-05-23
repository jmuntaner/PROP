package views;

import controllers.ControladorAnalisi;

import javax.swing.*;
import java.awt.*;

public class VistaAnalisiPartides extends JPanel {
    private VistaPrincipal vp;
    private ControladorAnalisi ca;
    private int numPartides;
    private JLabel labelTorn, nomA, nomB, labelVictA, labelVictB, labelTTA, labelTTB, labelTMA, labelTMB;

    private JPanel panelA, panelB, panelStats, panelSuperior;


    /**
     * Creadora per defecte.
     *
     * @param vp Vista principal.
     */
    VistaAnalisiPartides(VistaPrincipal vp) {
        this.vp = vp;
        setLayout(new GridBagLayout());

        initPanelA();
        initPanelB();
        initPanelStats();
        initPanelSuperior();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelSuperior, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        add(panelA, gbc);
        gbc.gridx = 1;
        add(panelStats, gbc);
        gbc.gridx = 2;
        add(panelB, gbc);


    }

    private void initPanelSuperior() {
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridBagLayout());

        JButton buttonTornar = new JButton("Tornar");
        buttonTornar.addActionListener(e -> vp.mostraLlistaProblemes());

        JButton buttonIniciar = new JButton("Iniciar");
        buttonIniciar.addActionListener(e -> iniciaPartides());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 4);
        panelSuperior.add(buttonTornar, gbc);
        gbc.gridx++;
        panelSuperior.add(buttonIniciar, gbc);
        // Glues
        gbc.gridx++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelSuperior.add(Box.createGlue(), gbc);
    }

    private void initPanelStats() {
        panelStats = new JPanel();
        panelStats.setLayout(new GridBagLayout());
        panelStats.setBorder(BorderFactory.createTitledBorder("Estat"));
        labelTorn = new JLabel("Esperant inici.");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelStats.add(labelTorn, gbc);
    }

    private void initPanelA() {
        panelA = new JPanel();
        panelA.setLayout(new GridBagLayout());
        panelA.setBorder(BorderFactory.createTitledBorder("Atacant"));
        nomA = new JLabel("-");
        labelVictA = new JLabel("0");
        labelTTA = new JLabel("0");
        labelTMA = new JLabel("0");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelA.add(nomA, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panelA.add(new JLabel("Victories:"), gbc);
        gbc.gridx = 1;
        panelA.add(labelVictA, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelA.add(new JLabel("Temps total:"), gbc);
        gbc.gridx = 1;
        panelA.add(labelTTA, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelA.add(new JLabel("Temps mitjà:"), gbc);
        gbc.gridx = 1;
        panelA.add(labelTMA, gbc);


        // Glue
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelA.add(Box.createGlue(), gbc);
    }

    private void initPanelB() {
        panelB = new JPanel();
        panelB.setLayout(new GridBagLayout());
        panelB.setBorder(BorderFactory.createTitledBorder("Defensor"));
        nomB = new JLabel("-");
        labelVictB = new JLabel("0");
        labelTTB = new JLabel("0");
        labelTMB = new JLabel("0");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelB.add(nomB, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panelB.add(new JLabel("Victories:"), gbc);
        gbc.gridx = 1;
        panelB.add(labelVictB, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelB.add(new JLabel("Temps total:"), gbc);
        gbc.gridx = 1;
        panelB.add(labelTTB, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelB.add(new JLabel("Temps mitjà:"), gbc);
        gbc.gridx = 1;
        panelB.add(labelTMB, gbc);

        // Glue
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelB.add(Box.createGlue(), gbc);
    }

    /**
     * Estableix el controlador d'analisi de la vista.
     *
     * @param ca Controlador d'analisi.
     */
    void setAnalisi(ControladorAnalisi ca) {
        this.ca = ca;
        numPartides = ca.getNumProbs();
        nomA.setText(ca.getNomM(false));
        nomB.setText(ca.getNomM(true));
    }

    /**
     * Inicia el fil d'execució de partides.
     */
    private void iniciaPartides() {
        new Thread(() -> {
            if (ca == null) {
                throw new RuntimeException("No hi ha controlador");
            }
            do {
                System.out.println("Ronda");

                SwingUtilities.invokeLater(() -> {

                    updateStats();
                });
            }
            while (ca.jugaPartida());
            SwingUtilities.invokeLater(() -> {
                labelTorn.setText("Partides finalitzades");
            });
        }).start();
    }

    private void updateStats() {
        int n = ca.getNumAct();
        int wa = ca.getWinCount(false);
        int wd = ca.getWinCount(true);
        long[] timesA = ca.getTimes(true);
        long[] timesB = ca.getTimes(false);
        long totalTimeA = 0;
        long totalTimeB = 0;
        for (long t : timesA) {
            totalTimeA += t;
        }
        for (long t : timesB) {
            totalTimeB += t;
        }
        long meanTimeA = timesA.length == 0 ? 0 : totalTimeA / timesA.length;
        long meanTimeB = timesB.length == 0 ? 0 : totalTimeB / timesB.length;


        labelTorn.setText("Jugant partida " + n + "/" + numPartides);
        labelVictA.setText(String.valueOf(wa));
        labelVictB.setText(String.valueOf(wd));
        labelTTA.setText(String.valueOf(totalTimeA));
        labelTMA.setText(String.valueOf(totalTimeB));
        labelTTB.setText(String.valueOf(meanTimeA));
        labelTMB.setText(String.valueOf(meanTimeB));
    }
}
