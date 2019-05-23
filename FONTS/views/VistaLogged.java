package views;

import javax.swing.*;
import java.awt.*;

public class VistaLogged extends JPanel {
    private JButton botoEstadistiques, botoLogout, botoTornar;
    private JLabel benvinguda;
    private VistaPrincipal vp;
    /*
    TEXT: BENVINGUT #NOMUSUARI // (BOTÓ ESTADÍSTIQUES -> MOSTRA-ESTADÍSTIQUES) // (BOTÓ LOGOUT -> VistaPerfilSenseLogin) // TORNAR
     */
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaLogged(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initBenvinguda();
        initBotoEstadistiques();
        initBotoLogout();
        initBotoTornar();
        initGlues();
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
        benvinguda = new JLabel("Benvingut usuari #x"); //TODO: nom de l'usuari actual

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        add(benvinguda, gbc);
    }

    /**
     * Inicialitza el botó d'estadístiques
     */
    private void initBotoEstadistiques() {
        botoEstadistiques = new JButton("Estadistiques");
        botoEstadistiques.addActionListener(e -> vp.mostraEstadistiques()); //mostra estadistiques somehow
        botoEstadistiques.setPreferredSize(new Dimension(140, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipadx = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoEstadistiques, gbc);
    }

    /**
     * Inicialitza el botó de logout.
     */
    private void initBotoLogout() {
        botoLogout = new JButton("Logout");
        botoLogout.setPreferredSize(new Dimension(140, 28));
        botoLogout.addActionListener(e -> vp.mostraNotLogged()); //TODO: fer el logout efectiu
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
        botoTornar = new JButton("Tornar");
        botoTornar.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraMenuPrincipal());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(botoTornar, gbc);
    }
}
