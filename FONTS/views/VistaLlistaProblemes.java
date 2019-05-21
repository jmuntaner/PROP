package views;

import controllers.ControladorLlistaProblemes;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Vista de la llista de problemes.
 */
class VistaLlistaProblemes extends JPanel {

    private ControladorLlistaProblemes cp;
    private VistaPrincipal vp;
    private VistaTauler preview;
    private JLabel labelNom, labelDificultat, labelJugades;
    private JButton buttonJugarHvH, buttonJugarHvM, buttonEditar, buttonEliminar;
    private JList<String> problemes;
    private final JFileChooser fc;

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
        fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String ext = Utils.getExtension(f);
                return ext != null && ext.equals("fendb");
            }

            @Override
            public String getDescription() {
                return "Arxius de base de dades (*.fendb)";
            }
        });
        fc.setAcceptAllFileFilterUsed(false);
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

        JButton buttonCarrega = new JButton("Carregar arxiu");
        buttonCarrega.addActionListener(e -> carregarArxiu());
        filaBotons.add(buttonCarrega);
        JButton buttonExporta = new JButton("Exportar arxiu");
        buttonExporta.addActionListener(e -> exportarArxiu());
        filaBotons.add(buttonExporta);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(filaBotons, gbc);
    }

    /**
     * Exporta la base de dades a un arxiu.
     */
    private void exportarArxiu() {
        int ret = fc.showSaveDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File filepre = fc.getSelectedFile();
            String ext = Utils.getExtension(filepre);
            if (ext == null || !ext.equals("fendb")) {
                filepre = new File(filepre.getAbsolutePath() + ".fendb");
            }
            final File file = filepre;
            new Thread(() -> {
                cp.exportaProblemes(file);
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this,
                            "Problemes exportats!", "Exportació de problemes",
                            JOptionPane.INFORMATION_MESSAGE);
                });
            }).start();
        }
    }

    /**
     * Afegeix els problemes d'un arxiu a la base de dades.
     */
    private void carregarArxiu() {

        int ret = fc.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            final File file = fc.getSelectedFile();
            new Thread(() -> {
                cp.carregaProblemes(file);
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this,
                            "Problemes carregats!", "Càrrega de problemes",
                            JOptionPane.INFORMATION_MESSAGE);
                });
                update();

            }).start();
        }
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
        labelNom = new JLabel("                                                  ");
        final Dimension size = labelNom.getPreferredSize();
        labelNom.setMinimumSize(size);
        labelNom.setPreferredSize(size);
        visor.add(labelNom, gbc_n);

        // Preview tauler
        gbc_n.gridy++;
        gbc_n.fill = GridBagConstraints.HORIZONTAL;
        preview = new VistaTauler();
        preview.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                preview.getBorder()));
        visor.add(preview, gbc_n);

        gbc_n.insets = new Insets(0, 4, 4, 4);

        labelDificultat = new JLabel("Dificultat: -");
        labelJugades = new JLabel("Jugades: -");

        gbc_n.gridy++;
        visor.add(labelJugades, gbc_n);
        gbc_n.gridy++;
        visor.add(labelDificultat, gbc_n);

        // Boto Jugar HvH
        gbc_n.gridy++;
        buttonJugarHvH = new JButton("Jugar HvH");
        buttonJugarHvH.setEnabled(false);
        buttonJugarHvH.addActionListener(e -> jugaHvH());
        visor.add(buttonJugarHvH, gbc_n);

        // Boto Jugar HvM
        gbc_n.gridy++;
        buttonJugarHvM = new JButton("Jugar HvM");
        buttonJugarHvM.setEnabled(false);
        buttonJugarHvM.addActionListener(e -> jugaHvM());
        visor.add(buttonJugarHvM, gbc_n);

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
            String id = problemes.getSelectedValue();
            cp.eliminaProblema(id);
            update();
        }
    }

    /**
     * Mostra el problema seleccionat al visor.
     */
    private void selecciona() {
        String id = problemes.getSelectedValue();
        if (id == null) {
            // Cap seleccionat
            buttonJugarHvH.setEnabled(false);
            buttonJugarHvM.setEnabled(false);
            buttonEditar.setEnabled(false);
            buttonEliminar.setEnabled(false);
            labelNom.setText("");
            labelDificultat.setText("Dificultat: -");
            labelJugades.setText("Jugades: -");
            clearPreview();
        } else {
            // Seleccionat
            cp.selectProblema(id);
            buttonJugarHvH.setEnabled(true);
            buttonJugarHvM.setEnabled(true);
            buttonEditar.setEnabled(true);
            buttonEliminar.setEnabled(true);
            labelNom.setText(cp.getNom());
            labelDificultat.setText("Dificultat: " + cp.getDificultat());
            labelJugades.setText("Jugades: " + cp.getJugades());
            updatePreview();
        }
    }

    /**
     * Inicia l'edició del problema seleccionat.
     */
    private void edita() {
        vp.editaProblema(problemes.getSelectedValue());
    }

    /**
     * Inicia una partida Humà contra Humà.
     */
    private void jugaHvH() {
        String nomOp = JOptionPane.showInputDialog(
                JOptionPane.getFrameForComponent(this),
                "Introdueix el nom de l'oponent:",
                "Iniciar Partida",
                JOptionPane.QUESTION_MESSAGE);
        if (nomOp == null) return;
        if (nomOp.length() == 0) {
            JOptionPane.showMessageDialog(
                    JOptionPane.getFrameForComponent(this),
                    "Has d'introduir un nom",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        } else vp.jugaProblema(cp.iniciaPartidaHvH(nomOp));
    }

    /**
     * Inicia una partida Humà vs Màquina
     */
    private void jugaHvM() {
        String[] maquines = new String[]{"Xicu (M1)", "Barja (M2)"};
        String result = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona el teu oponent:",
                "Iniciar Partida", JOptionPane.QUESTION_MESSAGE,
                null,
                maquines, maquines[0]);
        if (result == null) return;
        int maquina = 1;
        if (result.equals(maquines[1])) maquina = 2;
        Object[] options = {"Atacar",
                "Defensar",
                "Cancelar"};
        int ataca = JOptionPane.showOptionDialog(null,
                "Que vols fer, atacar o defensar?",
                "Iniciar partida",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        if (ataca == 2) return;

        vp.jugaProblema(cp.iniciaPartidaHvM(maquina != 1, ataca == 1));
    }
}
