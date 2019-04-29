package views;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
    private CtrlPresentacion mCp;
    private JFrame frameVista;
    private JPanel panelContinguts;
    private JLabel nomPrograma;
    private JButton botoJugar, botoGestio;

    public VistaPrincipal(CtrlPresentacion cp) {
        mCp = cp;

        initFrameVista();
        initPanel();
        initNomPrograma();
        initBotoJugar();
        initBotoGestio();

    }

    private void initFrameVista() {
        frameVista = new JFrame("Vista Principal");
        // Mida fixa
        frameVista.setMinimumSize(new Dimension(700, 400));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(true);

        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initPanel() {
        panelContinguts = new JPanel();
        panelContinguts.setLayout(new GridBagLayout());
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContinguts);

    }

    private void initNomPrograma() {
        nomPrograma = new JLabel();
        nomPrograma.setText("Increible programa d'escacs");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelContinguts.add(nomPrograma, gbc);
    }

    private void initBotoJugar() {
        botoJugar = new JButton("Jugar");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelContinguts.add(botoJugar, gbc);

    }

    private void initBotoGestio() {
        botoGestio = new JButton("Gestio");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelContinguts.add(botoGestio, gbc);

    }

    public void ferVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
        frameVista.setEnabled(true);
    }

}
