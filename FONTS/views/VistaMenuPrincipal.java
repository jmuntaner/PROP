package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VistaMenuPrincipal extends JPanel {
    private JLabel nomPrograma;
    private JButton botoJugar, botoGestio;
    private VistaPrincipal vp;

    VistaMenuPrincipal(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initNomPrograma();
        initBotoJugar();
        initBotoGestio();
        initGlues();
    }

    private void initGlues() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(Box.createGlue(), gbc);
        gbc.gridy = 5;
        gbc.weighty = 1;
        add(Box.createGlue(), gbc);
    }

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

    private void initBotoJugar() {
        botoJugar = new JButton("Jugar");
        botoJugar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                vp.mostraJugar();
            }
        });
        botoJugar.setPreferredSize(new Dimension(140, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipadx = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoJugar, gbc);
    }

    private void initBotoGestio() {
        botoGestio = new JButton("Gestio");
        botoGestio.setPreferredSize(new Dimension(140, 28));
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(botoGestio, gbc);
    }


}
