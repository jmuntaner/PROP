package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaJugar extends JPanel {
    private VistaTauler tauler;
    private VistaPrincipal vp;

    VistaJugar(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;
        initBack();
        initTauler();
    }


    private void initBack() {
        Button b = new Button("Tornar");
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                vp.mostraMenuPrincipal();
            }

        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(b, gbc);
    }

    private void initTauler() {
        tauler = new VistaTauler();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(tauler, gbc);

    }
}
