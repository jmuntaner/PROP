package views;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
    private CtrlPresentacion mCp;
    private JFrame frameVista;
    private VistaMenuPrincipal panelMenuPrincipal;
    private VistaJugar panelJugar;
    private VistaEditor panelEditor;
    private JPanel contentPane;

    public VistaPrincipal(CtrlPresentacion cp) {
        mCp = cp;

        initFrameVista();
        initMenuPrincipal();
        initJugar();
        initEditor();
        addPanels();

        mostraMenuPrincipal();
    }

    private void initFrameVista() {
        frameVista = new JFrame("Vista Principal");
        // Mida inicial
        frameVista.setMinimumSize(new Dimension(800, 500));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(true);

        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMenuPrincipal() {
        panelMenuPrincipal = new VistaMenuPrincipal(this);
    }

    private void initJugar() {
        panelJugar = new VistaJugar(this);
    }

    private void initEditor() {
        panelEditor = new VistaEditor(this, mCp.getEditor());
    }

    private void addPanels() {
        contentPane = (JPanel) frameVista.getContentPane();
        contentPane.setLayout(new CardLayout());
        contentPane.add(panelMenuPrincipal, "menu");
        contentPane.add(panelJugar, "jugar");
        contentPane.add(panelEditor, "editar");
    }

    void mostraJugar() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "jugar");
    }

    void mostraEditar() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "editar");
    }

    void mostraMenuPrincipal() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "menu");
    }


    public void ferVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
        frameVista.setEnabled(true);
    }

}
