package views;

import controllers.ControladorPartida;

import javax.swing.*;
import java.awt.*;

/**
 * Vista per a representar els resultats d'una partida.
 */
public class VistaFiPartida extends JPanel {
    ControladorPartida cp;
    VistaPrincipal vp;
    JLabel labelGuanyador;

    /**
     * Creadora per defecte.
     */
    VistaFiPartida(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;
        initLabels();
    }

    /**
     * Assigna un controlador a la vista.
     *
     * @param cp Controlador de partida a assignar.
     */
    void setControlador(ControladorPartida cp) {
        this.cp = cp;
        labelGuanyador.setText("Ha guanyat: " + cp.getNomGuanyador());
    }

    /**
     * Inicialitza els textos de pantalla.
     */
    void initLabels() {
        // Títol
        JLabel labelPrincipal = new JLabel("Partida finalitzada");
        Font defaulltFont = labelPrincipal.getFont();
        labelPrincipal.setFont(new Font(defaulltFont.getName(), Font.PLAIN, 40));

        // Guanyador
        labelGuanyador = new JLabel("Ha guanyat: [ERROR]");
        labelGuanyador.setFont(new Font(defaulltFont.getName(), Font.PLAIN, 30));


        // Botó ranking
        JButton botoRanking = new JButton("Veure ranking");

        // Botó llista
        JButton botoLlista = new JButton("Llista de problemes");
        botoLlista.addActionListener(e -> vp.mostraLlistaProblemes());

        // Botó menu
        JButton botoMenu = new JButton("Menu principal");
        botoMenu.addActionListener(e -> vp.mostraMenuPrincipal());

        // Add to layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.anchor = GridBagConstraints.CENTER;
        add(labelPrincipal, gbc);
        gbc.gridy++;
        add(labelGuanyador, gbc);
        gbc.gridy++;
        add(botoRanking, gbc);
        gbc.gridy++;
        add(botoLlista, gbc);
        gbc.gridy++;
        add(botoMenu, gbc);
    }
}
