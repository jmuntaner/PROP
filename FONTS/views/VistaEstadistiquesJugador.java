package views;

import javax.swing.*;
import java.awt.*;

//TODO: mostrar les estadistiques

public class VistaEstadistiquesJugador extends JPanel {
    private VistaPrincipal vp;
    private JButton botoTornar;

    VistaEstadistiquesJugador(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initBotoTornar();
    }

    /**
     * Inicialitza el botó de tornada.
     */
    private void initBotoTornar() {
        botoTornar = new JButton("Tornar");
        botoTornar.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraLogged());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(botoTornar, gbc);
    }
}
