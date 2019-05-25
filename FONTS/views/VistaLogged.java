package views;

import controllers.ControladorUsuari;

import javax.swing.*;
import java.awt.*;

public class VistaLogged extends JPanel {
    private VistaPrincipal vp;
    private ControladorUsuari cu;
    private JLabel benvinguda;
    private JTextArea estadistiques;
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
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

    public void update() {
        benvinguda.setText("Benvingut " + cu.getNom());
        int intents = cu.getStatistics().getIntents();
        int acabats = cu.getStatistics().getAcabats();
        int guanyats = cu.getStatistics().getGuanyats();
        estadistiques.setText("Intents: " + intents + '\n' +
                "Acabats: " + acabats + '/' + intents + '\n' +
                "Guanyats: " + guanyats + '/' + acabats + '\n');

    }

    /**
     * Inicialitza les separacions entre elements del menu.
     */
    private void initGlues() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(Box.createGlue(), gbc);
        gbc.gridy = 5;
        add(Box.createGlue(), gbc);
        gbc.gridy = 7;
        gbc.weighty = 1;
        add(Box.createGlue(), gbc);
    }

    /**
     * Inicialitza la benvinguda i el nom de l'usuari.
     */
    private void initBenvinguda() {
        benvinguda = new JLabel("Benvingut " + cu.getNom());

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
        estadistiques = new JTextArea("Estadistiques: " +
                "aquest usuari es AlphaZero");
        estadistiques.setPreferredSize(new Dimension(150, 150));
        estadistiques.setEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(estadistiques, gbc);
        int intents = cu.getStatistics().getIntents();
        int acabats = cu.getStatistics().getAcabats();
        int guanyats = cu.getStatistics().getGuanyats();
        estadistiques.setText("Intents: " + intents + '\n' +
                "Acabats: " + acabats + '/' + intents + '\n' +
                "Guanyats: " + guanyats + '/' + acabats + '\n');
        //TODO: millorar interficie
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
        gbc.gridy = 4;
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
        gbc.gridy = 6;
        add(botoTornar, gbc);
    }
}
