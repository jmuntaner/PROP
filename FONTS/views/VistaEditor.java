package views;

import controllers.ControladorEditor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Enumeration;

/**
 * Vista de l'editor de problemes
 */
class VistaEditor extends VistaAmbTauler {
    private static final String PECES = "KQBRNPkqbrnp";
    private static final int SIZE_ICON = 30;
    private static final int JUGADES_MAX = 3;
    private static final int JUGADES_MIN = 1;

    private JTextField fenTextField;
    private JToggleButton buttonBlanc, buttonNegre, buttonReiB, buttonReiN;
    private final ControladorEditor ce;
    private JSlider sliderNjugades;

    private char actual;
    private boolean hasReiBlanc, hasReiNegre;
    private ButtonGroup bgPeces;

    /**
     * Creadora per defecte.
     *
     * @param vp Vista principal.
     * @param ce Controlador d'editor.
     */
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Selecció de color
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
        panelColors.setBorder(BorderFactory.createTitledBorder("Color inicial"));
        panelSeleccio.add(panelColors, gbc);

        // Peces
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy++;
        JPanel panelPeces = new JPanel();
        panelPeces.setLayout(new GridLayout(4, 3, 4, 4));
        bgPeces = new ButtonGroup();
        ImatgePecaLoader iml = ImatgePecaLoader.getLoader();
        for (int i = 0; i < PECES.length(); i++) {

            char c = PECES.charAt(i);
            JToggleButton b = new JToggleButton(iml.getIcon(c, SIZE_ICON));
            b.addActionListener(e -> {
                actual = c;
                b.setSelected(true);
            });
            if (c == 'K') buttonReiB = b;
            else if (c == 'k') buttonReiN = b;
            panelPeces.add(b);
            bgPeces.add(b);
        }
        panelPeces.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Peces"),
                new EmptyBorder(8, 8, 8, 8)));
        panelSeleccio.add(panelPeces, gbc);


        // Selector numero de jugades
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        sliderNjugades = new JSlider(JSlider.HORIZONTAL, JUGADES_MIN, JUGADES_MAX, ce.getNumJugades());
        sliderNjugades.setMajorTickSpacing(1);
        sliderNjugades.setMinorTickSpacing(1);
        sliderNjugades.setPaintLabels(true);
        sliderNjugades.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Nombre de jugades"),
                new EmptyBorder(8, 8, 8, 8)));
        sliderNjugades.addChangeListener(e -> ce.setNumJugades(sliderNjugades.getValue()));
        panelSeleccio.add(sliderNjugades, gbc);

        //End gap
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weighty = 1;
        panelSeleccio.add(Box.createGlue(), gbc);
        return panelSeleccio;
    }

    /**
     * Inicialitza el camp de text d'introducció de fen.
     */
    private void initFenTextField() {
        fenTextField = new JTextField();
        fenTextField.setText(ce.getFen());
        fenTextField.addActionListener(e -> {
            if (ce.carregaFen(fenTextField.getText().trim())) {
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

    /**
     * Recarrega totes les posicions del tauler mostrat.
     */
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

    /**
     * Activa o desactiva els botons de selecció de rei.
     * <p>
     * Desactiva el botó de cada rei si ja existeix al tauler, per tal d'evitar duplicitats.
     * </p>
     */
    private void updateBotonsRei() {
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
        initFenTextField();

        JPanel panelBotons = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(4, 4, 0, 0);

        JButton enrere = new JButton("Tornar");
        enrere.addActionListener(e -> vp.mostraLlistaProblemes());
        panelBotons.add(enrere, gbc);

        gbc.gridx++;
        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(e -> guardarTauler());
        panelBotons.add(guardar, gbc);

        gbc.gridx++;
        panelBotons.add(new JLabel("Fen:"), gbc);

        gbc.gridx++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 0, 4);
        panelBotons.add(fenTextField, gbc);


        return panelBotons;
    }

    /**
     * Guarda el problema en edició.
     */
    private void guardarTauler() {
        if (!(hasReiBlanc && hasReiNegre)) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Hi ha menys de dos reis presents al tauler.",
                    "Tauler invalid",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        setGlobalEnabled(false);
        if (ce.esNou()) {

            String nom = JOptionPane.showInputDialog(this, "Introdueix un nom pel problema");
            if (nom == null) {
                setGlobalEnabled(true);
                return;
            }
            if (nom.indexOf('_') != -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "El nom del problema no pot contenir '_'",
                        "Nom invalid", JOptionPane.WARNING_MESSAGE);
                setGlobalEnabled(true);
                return;
            }
            ce.setIdProblema(nom);
        }
        new Thread(() -> {
            boolean correcte = false;

            int res = ce.guardaProblema();
            switch (res) {
                case 0:
                    correcte = true;
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this,
                            "La situació de les peces no és una situació inicial vàlida.",
                            "Tauler invalid",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this,
                            "No es pot resoldre el problema en les jugades especificades.",
                            "Tauler invalid",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(this,
                            "El nom està repetit.",
                            "Nom invalid",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this,
                            "Error desconegut.",
                            "Tauler invalid",
                            JOptionPane.WARNING_MESSAGE);
            }

            final boolean correct = correcte; // Només es poden pasar finals a la lambda.
            // Runs inside of the Swing UI thread
            SwingUtilities.invokeLater(() -> {
                setGlobalEnabled(true);
                if (correct) vp.mostraLlistaProblemes();
            });

        }).start();

    }

    /**
     * Activa o desactiva tots els botons.
     *
     * @param en Estat d'activació.
     */
    private void setGlobalEnabled(boolean en) {
        bgPeces.clearSelection();
        actual = '-';
        Enumeration<AbstractButton> eb = bgPeces.getElements();
        while (eb.hasMoreElements()) {
            AbstractButton b = eb.nextElement();
            b.setEnabled(en);
        }
        buttonNegre.setEnabled(en);
        buttonBlanc.setEnabled(en);
        setInteractable(en);
        sliderNjugades.setEnabled(en);
        if (en) updateBotonsRei();
    }

    /**
     * Actualitza l'estat dels botons de selecció de color.
     */
    private void updateColor() {
        boolean col = ce.getColorInicial();
        buttonBlanc.setSelected(col);
        buttonNegre.setSelected(!col);
    }

    /**
     * Actualitza el valor del slider de jugades.
     */
    private void updateJugades() {
        sliderNjugades.setValue(ce.getNumJugades());
    }

    /**
     * Actualitza l'estat de tot l'editor
     */
    void actualitza() {
        setGlobalEnabled(true);
        reloadTauler();
        updateBotonsRei();
        updateFenLabel();
        updateColor();
        updateJugades();
    }
}
