package views;

import controllers.ControladorUsuari;

import javax.swing.*;
import java.awt.*;

class VistaLogged extends JPanel {
    private VistaPrincipal vp;
    private ControladorUsuari cu;
    private JLabel benvinguda;
    private JLabel vIntents, vAcabats, vGuanyats;
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     * @param cu Controlador usuari
     */
    VistaLogged(VistaPrincipal vp, ControladorUsuari cu) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;
        this.cu = cu;

        initBenvinguda();
        initEstadistiques();
        initBotoLogout();
        initBotoTornar();
        initGlues();
    }

    /**
     * Actualitza la vista segons l'usuari
     */
    void update() {
        benvinguda.setText("Benvingut " + cu.getNom());
        Font defaultFont = benvinguda.getFont();
        benvinguda.setFont(new Font(defaultFont.getName(), Font.PLAIN, 18));
        int intents = cu.getStatistics().getIntents();
        int acabats = cu.getStatistics().getAcabats();
        int guanyats = cu.getStatistics().getGuanyats();
        vIntents.setText("Intents: " + intents);
        vAcabats.setText("Acabats: " + acabats + '/' + intents);
        vGuanyats.setText("Guanyats: " + guanyats + '/' + acabats);
    }

    /**
     * Inicialitza les separacions entre elements de la vista.
     */
    private void initGlues() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 0.1;
        add(Box.createGlue(), gbc);
        gbc.gridy = 7;
        add(Box.createGlue(), gbc);
        gbc.gridy = 9;
        gbc.weighty = 1;
        add(Box.createGlue(), gbc);
    }

    /**
     * Inicialitza la benvinguda i el nom de l'usuari.
     */
    private void initBenvinguda() {
        benvinguda = new JLabel("Benvingut " + cu.getNom());
        Font defaultFont = benvinguda.getFont();
        benvinguda.setFont(new Font(defaultFont.getName(), Font.PLAIN, 18));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.3;
        add(benvinguda, gbc);
    }

    /**
     * Inicialitza el botó d'estadístiques
     */
    private void initEstadistiques() {
        int intents = cu.getStatistics().getIntents();
        int acabats = cu.getStatistics().getAcabats();
        int guanyats = cu.getStatistics().getGuanyats();
        vIntents = new JLabel("Intents: " + intents);
        vAcabats = new JLabel("Acabats: " + acabats + '/' + intents);
        vGuanyats = new JLabel("Guanyats: " + guanyats + '/' + acabats);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        add(vIntents, gbc);
        gbc.gridy = 3;
        add(vAcabats, gbc);
        gbc.gridy = 4;
        add(vGuanyats, gbc);
    }

    /**
     * Inicialitza el botó de logout.
     */
    private void initBotoLogout() {
        JButton botoLogout = new JButton("Logout");
        botoLogout.setPreferredSize(new Dimension(140, 28));
        botoLogout.addActionListener(e -> cu.logout());
        botoLogout.addActionListener(e -> vp.mostraNotLogged());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(botoLogout, gbc);
    }

    /**
     * Inicialitza el botó de tornada.
     */
    private void initBotoTornar() {
        JButton botoTornar = new JButton("Tornar");
        botoTornar.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraMenuPrincipal());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(botoTornar, gbc);
    }
}
