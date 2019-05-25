package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Vista amb el menú principal del programa.
 */
class VistaMenuPrincipal extends JPanel {
    private JLabel nomPrograma;
    private JButton botoJugar, botoGestio, botoSortir;
    private VistaPrincipal vp;

    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaMenuPrincipal(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initNomPrograma();
        initBotoJugar();
        initBotoPerfil();
        initBotoSortir();
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
     * Inicialitza la imatge amb el nom del programa.
     */
    private void initNomPrograma() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("res/logo.png"));
            nomPrograma = new JLabel(new ImageIcon(myPicture));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        //nomPrograma.setText("Increible programa d'escacs");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        add(nomPrograma, gbc);
    }

    /**
     * Inicialitza el botó de jugar.
     */
    private void initBotoJugar() {
        botoJugar = new JButton("Jugar");
        botoJugar.addActionListener(e -> vp.mostraLlistaProblemes());
        botoJugar.setPreferredSize(new Dimension(140, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipadx = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoJugar, gbc);
    }

    /**
     * Inicialitza el botó d'accés al perfil d'usuari.
     */
    private void initBotoPerfil() {
        botoGestio = new JButton("Perfil");
        botoGestio.setPreferredSize(new Dimension(140, 28));
        botoGestio.addActionListener(e -> vp.mostraPerfil());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(botoGestio, gbc);

    }

    /**
     * Inicialitza el botó de sortida
     */
    private void initBotoSortir() {
        botoSortir = new JButton("Sortir");
        botoSortir.setPreferredSize(new Dimension(140, 28));
        botoSortir.addActionListener(e -> System.exit(0)); // No es la manera més maca, però funciona

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(botoSortir, gbc);
    }


}
