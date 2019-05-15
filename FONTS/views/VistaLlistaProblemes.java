package views;

import controllers.ControladorLlistaProblemes;

import javax.swing.*;
import java.awt.*;

class VistaLlistaProblemes extends JPanel {
    private ControladorLlistaProblemes cp;
    private VistaPrincipal vp;
    private VistaTauler preview;
    private JLabel labelNom;
    private JButton buttonJugar, buttonEditar, buttonEliminar;
    private JList<String> problemes;

    /**
     * Creadora per defecte.
     *
     * @param vp Vista principal.
     * @param cp Controlador principal del domini.
     */
    VistaLlistaProblemes(VistaPrincipal vp, ControladorLlistaProblemes cp) {
        this.vp = vp;
        this.cp = cp;

        setLayout(new GridBagLayout());
        initFilaBotons();
        initVisor();
        initLlistaProblemes();
    }

    /**
     * Inicialitza la llista superior de botons.
     */
    private void initFilaBotons() {
        JPanel filaBotons = new JPanel();
        JButton buttonSortir = new JButton("Tornar");
        buttonSortir.addActionListener(e -> vp.mostraMenuPrincipal());
        filaBotons.add(buttonSortir);
        JButton buttonNou = new JButton("Crear problema");
        buttonNou.addActionListener(e -> vp.creaProblema());
        filaBotons.add(buttonNou);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(filaBotons, gbc);
    }


    /**
     * Inicialitza el panel de la llista de problemes.
     */
    private void initLlistaProblemes() {

        problemes = new JList<>(cp.getNomsProblemes());

        problemes.addListSelectionListener(e -> selecciona());

        JScrollPane llistaProblemes = new JScrollPane(problemes);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(llistaProblemes, gbc);

    }

    /**
     * Inicialitza el panel lateral d'informació del problema.
     */
    private void initVisor() {
        JPanel visor = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_n = new GridBagConstraints();
        gbc_n.gridx = 0;
        gbc_n.gridy = 0;
        gbc_n.insets = new Insets(0, 0, 4, 0);
        labelNom = new JLabel("Nom del problema llaaaaaarg");
        visor.add(labelNom, gbc_n);

        // Preview tauler
        gbc_n.gridy++;
        gbc_n.fill = GridBagConstraints.HORIZONTAL;
        preview = new VistaTauler();
        preview.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Situació inicial"),
                preview.getBorder()));
        visor.add(preview, gbc_n);

        gbc_n.insets = new Insets(0, 4, 4, 4);
        // Boto Jugar
        gbc_n.gridy++;
        buttonJugar = new JButton("Jugar");
        buttonJugar.setEnabled(false);
        buttonJugar.addActionListener(e -> juga());
        visor.add(buttonJugar, gbc_n);

        // Boto Editar
        gbc_n.gridy++;
        buttonEditar = new JButton("Editar");
        buttonEditar.setEnabled(false);
        buttonEditar.addActionListener(e -> edita());
        visor.add(buttonEditar, gbc_n);

        // Boto Eliminar
        gbc_n.gridy++;
        buttonEliminar = new JButton("Eliminar");
        buttonEliminar.setEnabled(false);
        buttonEliminar.addActionListener(e -> eliminaProblema());
        visor.add(buttonEliminar, gbc_n);

        gbc_n.gridy++;
        gbc_n.weighty = 1;
        visor.add(Box.createGlue(), gbc_n);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(visor, gbc);
    }

    /**
     * Actualitza els continguts de la llista de problemes.
     */
    void update() {
        problemes.clearSelection();
        problemes.setListData(cp.getNomsProblemes());
    }

    /**
     * Actualitza la previsualització del problema.
     */
    private void updatePreview() {
        char[][] fichas = cp.getTauler();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                preview.setFitxa(i, j, fichas[i][j]);
    }

    /**
     * Borra els continguts del visor de problemes.
     */
    private void clearPreview() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                preview.borraFitxa(i, j);
    }

    /**
     * Elimina un problema de la base de dades i reinicia el visor.
     */
    private void eliminaProblema() {
        int input = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                "Segur que vols eliminar el problema?", "Eliminar problema",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (input == 0) {
            int id = problemes.getSelectedIndex();
            cp.eliminaProblema(id);
            update();
        }
    }

    /**
     * Mostra el problema seleccionat al visor.
     */
    private void selecciona() {
        int id = problemes.getSelectedIndex();
        if (id == -1) {
            // Cap seleccionat
            buttonJugar.setEnabled(false);
            buttonEditar.setEnabled(false);
            buttonEliminar.setEnabled(false);
            clearPreview();
        } else {
            // Seleccionat
            cp.selectProblema(id);
            buttonJugar.setEnabled(true);
            buttonEditar.setEnabled(true);
            buttonEliminar.setEnabled(true);
            labelNom.setText(cp.getNom());
            updatePreview();
        }
    }

    private void edita() {
        vp.editaProblema(problemes.getSelectedIndex());
    }

    private void juga() {
        vp.jugaProblema(cp.iniciaPartida());
    }
}
