package views;

import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class VistaRankingProblema extends JPanel {
    private JList<String[]> llista;
    private JScrollPane panelRanking;

    VistaRankingProblema(VistaPrincipal vp) {
        setLayout(new GridBagLayout());
        initpanelLlista();

        JButton backButton = new JButton("Tornar");
        backButton.addActionListener(e -> vp.mostraLlistaProblemes());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(Box.createGlue(), gbc);
        gbc.gridx = 2;
        add(Box.createGlue(), gbc);
        gbc.weightx = 1;
        gbc.gridx = 1;
        add(panelRanking, gbc);
        gbc.gridy = 1;
        add(backButton, gbc);

    }

    /**
     * Inicialitza la llista del ranking.
     */
    private void initpanelLlista() {
        llista = new JList<>();
        ListCellRenderer<String[]> rend = new RankingRenderer();
        llista.setCellRenderer(rend);
        panelRanking = new JScrollPane(llista);
    }

    void setRanking(ArrayList<Pair<String, String>> ranking) {
        String[][] array = new String[ranking.size()][2];
        for (int i = 0; i < ranking.size(); i++) {
            Pair<String, String> p = ranking.get(i);
            array[i][0] = p.first();
            array[i][1] = p.second();
        }
        llista.setListData(array);
    }

    private class RankingRenderer implements ListCellRenderer<String[]> {
        @Override
        public Component getListCellRendererComponent(JList<? extends String[]> list, String[] value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel pfila = new JPanel();
            pfila.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.gridx = 0;
            pfila.add(new JLabel((index + 1) + ". " + value[0]), gbc);
            gbc.gridx = 2;
            pfila.add(new JLabel(value[1]), gbc);
            gbc.gridx = 1;
            gbc.weightx = 1;
            pfila.add(Box.createGlue(), gbc);
            return pfila;
        }
    }
}
