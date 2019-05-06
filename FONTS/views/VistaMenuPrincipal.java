package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    }

    private void initNomPrograma() {
        nomPrograma = new JLabel();
        nomPrograma.setText("Increible programa d'escacs");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(botoJugar, gbc);
    }

    private void initBotoGestio() {
        botoGestio = new JButton("Gestio");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoGestio, gbc);
    }


}
