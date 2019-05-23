package views;

import controllers.ControladorAnalisi;

import javax.swing.*;
import java.awt.*;

class VistaAnalisiPartides extends JPanel {
    private final VistaPrincipal vp;
    private ControladorAnalisi ca;
    private int numPartides;
    private JLabel labelTorn;
    private JLabel[] labelNom, labelVict, labelTT, labelTM;
    private JPanel[] players;
    private JButton buttonIniciar;
    private JPanel panelStats, panelSuperior;
    private JList<String> listProblemes;
    private boolean started;


    /**
     * Creadora per defecte.
     *
     * @param vp Vista principal.
     */
    VistaAnalisiPartides(VistaPrincipal vp) {
        this.vp = vp;
        started = false;

        setLayout(new GridBagLayout());


        initPlayers();
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
        add(players[0], gbc);
        gbc.gridx = 1;
        add(panelStats, gbc);
        gbc.gridx = 2;
        add(players[1], gbc);


    }

    private void initPlayers() {
        labelNom = new JLabel[2];
        labelVict = new JLabel[2];
        labelTT = new JLabel[2];
        labelTM = new JLabel[2];
        players = new JPanel[2];
        for (int i = 0; i < 2; i++) {
            players[i] = new JPanel();
            players[i].setLayout(new GridBagLayout());
            labelNom[i] = new JLabel("-");
            Font defaulltFont = labelNom[i].getFont();
            labelNom[i].setFont(new Font(defaulltFont.getName(), Font.PLAIN, 20));
            labelVict[i] = new JLabel("0");
            labelTT[i] = new JLabel("0");
            labelTM[i] = new JLabel("0");


            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.weightx = 1;
            gbc.insets = new Insets(0, 4, 0, 4);
            players[i].add(labelNom[i], gbc);
            gbc.gridwidth = 1;

            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            players[i].add(new JLabel("Victories:"), gbc);
            gbc.gridy++;
            players[i].add(new JLabel("Temps total:"), gbc);
            gbc.gridy++;
            players[i].add(new JLabel("Temps mitjà:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.LINE_END;

            players[i].add(labelVict[i], gbc);
            gbc.gridy++;
            players[i].add(labelTT[i], gbc);
            gbc.gridy++;
            players[i].add(labelTM[i], gbc);


            // Glue
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.VERTICAL;
            players[i].add(Box.createGlue(), gbc);
        }
        players[0].setBorder(BorderFactory.createTitledBorder("Atacant"));
        players[1].setBorder(BorderFactory.createTitledBorder("Defensor"));
    }

    private void initPanelSuperior() {
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridBagLayout());

        JButton buttonTornar = new JButton("Tornar");
        buttonTornar.addActionListener(e -> vp.mostraLlistaProblemes());

        buttonIniciar = new JButton("Iniciar");
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

        listProblemes = new JList<>(genLlista());
        listProblemes.setSelectionModel(new DisabledItemSelectionModel());
        listProblemes.setBackground(new Color(0, 0, 0, 0));
        listProblemes.setCellRenderer(new ListCellRenderer<String>() {
            final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                return defaultRenderer.getListCellRendererComponent(list, value, index, started && ca.getNumAct() - 1 == index, cellHasFocus);
            }
        });

        JScrollPane spListProblemes = new JScrollPane(listProblemes);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelStats.add(labelTorn, gbc);
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        panelStats.add(spListProblemes, gbc);
    }

    private String[] genLlista() {
        if (ca == null) return new String[0];
        String[] noms = ca.getNoms();
        String[] wins = ca.getWinners();
        int act = ca.getNumAct() - 1;
        for (int i = 0; i < noms.length; i++) {
            noms[i] += ": ";
            if (i < act) noms[i] += wins[i];
            else if (started && i == act) noms[i] += "[Jugant]";
            else noms[i] += "[Pendent]";
        }
        return noms;
    }

    /**
     * Estableix el controlador d'analisi de la vista.
     *
     * @param ca Controlador d'analisi.
     */
    void setAnalisi(ControladorAnalisi ca) {
        this.ca = ca;
        numPartides = ca.getNumProbs();
        resetAll();
    }


    /**
     * Inicia el fil d'execució de partides.
     */
    private void iniciaPartides() {
        resetAll();
        ca.resetAll();
        new Thread(() -> {
            if (ca == null) {
                throw new RuntimeException("No hi ha controlador");
            }
            SwingUtilities.invokeLater(() -> {
                buttonIniciar.setEnabled(false);
                started = true;
            });
            do {
                SwingUtilities.invokeLater(this::updateStats);
            }
            while (ca.jugaPartida());
            SwingUtilities.invokeLater(() -> {
                updateStats();
                labelTorn.setText("Partides finalitzades");
                buttonIniciar.setEnabled(true);
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

        long meanTimeA = 0;
        long meanTimeB = 0;
        if (timesA.length > 0) meanTimeA = totalTimeA / timesA.length;
        if (timesB.length > 0) meanTimeB = totalTimeB / timesB.length;

        labelTorn.setText("Jugant partida " + n + "/" + numPartides);
        labelVict[0].setText(Integer.toString(wa));
        labelVict[1].setText(Integer.toString(wd));
        labelTT[0].setText(totalTimeA + " ms");
        labelTM[0].setText(meanTimeA + " ms");
        labelTT[1].setText(totalTimeB + " ms");
        labelTM[1].setText(meanTimeB + " ms");

        // Update victory list
        listProblemes.setListData(genLlista());
    }

    private void resetAll() {
        labelNom[0].setText(ca.getNomM(false));
        labelNom[1].setText(ca.getNomM(true));
        labelTorn.setText("Esperant Inici");
        labelVict[0].setText("-");
        labelVict[1].setText("-");
        labelTT[0].setText("-");
        labelTM[1].setText("-");
        labelTT[0].setText("-");
        labelTM[1].setText("-");
        listProblemes.setListData(genLlista());
        started = false;
    }

    private class DisabledItemSelectionModel extends DefaultListSelectionModel {

        @Override
        public void setSelectionInterval(int index0, int index1) {
            super.setSelectionInterval(-1, -1);
        }
    }
}
