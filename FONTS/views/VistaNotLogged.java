package views;

import javax.swing.*;
import java.awt.*;

//TODO: arreglar posicionament botons

public class VistaNotLogged extends JPanel {
    private JButton botoLogin, botoRegister, botoTornar;
    private VistaPrincipal vp;
    /*
    (BOTÓ LOGIN -> FORMULARI LOGIN) // (BOTÓ REGISTRE -> FORMULARI REGISTRE) // TORNAR
     */
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaNotLogged(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initBotoLogin();
        initBotoRegister();
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
     * Inicialitza el botó de login.
     */
    private void initBotoLogin() {
        botoLogin = new JButton("Login");
        //botoLogin.addActionListener(e -> vp.mostraEstadistiques()); //TODO: mostrar formulari login
        botoLogin.setPreferredSize(new Dimension(140, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipadx = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoLogin, gbc);
    }

    /**
     * Inicialitza el botó d'accés al perfil d'usuari.
     */
    private void initBotoRegister() {
        botoRegister = new JButton("Register");
        botoRegister.setPreferredSize(new Dimension(140, 28));
        //botoRegister.addActionListener(e -> vp.mostraNotLogged()); //TODO: mostrar formulari registre
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(botoRegister, gbc);

    }

    /**
     * Inicialitza el botó de sortida
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
