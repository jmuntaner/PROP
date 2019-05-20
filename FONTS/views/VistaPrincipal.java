package views;

import controllers.ControladorPartida;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
    private CtrlPresentacion mCp;
    private JFrame frameVista;
    private VistaMenuPrincipal panelMenuPrincipal;
    private VistaJugar panelJugar;
    private VistaEditor panelEditor;
    private VistaLlistaProblemes panelLlistaProblemes;
    private VistaFiPartida panelResultats;
    private JPanel contentPane;

    public VistaPrincipal(CtrlPresentacion cp) {
        mCp = cp;

        initFrameVista();
        initMenuPrincipal();
        initJugar();
        initEditor();
        initLlistaProblemes();
        initResultats();
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
        panelEditor = new VistaEditor(this, mCp.getCEditor());
    }

    private void initLlistaProblemes() {
        panelLlistaProblemes = new VistaLlistaProblemes(this, mCp.getCLlista());
    }

    private void initResultats() {
        panelResultats = new VistaFiPartida(this);
    }

    private void addPanels() {
        contentPane = (JPanel) frameVista.getContentPane();
        contentPane.setLayout(new CardLayout());
        contentPane.add(panelMenuPrincipal, "menu");
        contentPane.add(panelJugar, "jugar");
        contentPane.add(panelEditor, "editar");
        contentPane.add(panelLlistaProblemes, "llistaProbs");
        contentPane.add(panelResultats, "resultats");
    }

    private void mostraJugar() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "jugar");
    }

    private void mostraEditar() {
        panelEditor.actualitza();
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "editar");
    }

    void mostraMenuPrincipal() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "menu");
    }

    void mostraLlistaProblemes() {
        panelLlistaProblemes.update();
        CardLayout cl = (CardLayout) contentPane.getLayout();

        cl.show(contentPane, "llistaProbs");
    }

    void editaProblema(String nom) {
        mCp.getCEditor().carregaProblema(nom);
        mostraEditar();
    }

    void creaProblema() {
        mCp.getCEditor().creaProblema();
        mostraEditar();
    }

    void jugaProblema(ControladorPartida cPart) {
        panelJugar.setControlador(cPart);
        mostraJugar();
    }

    void fiPartida(ControladorPartida cPart) {
        panelResultats.setControlador(cPart);
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "resultats");
    }


    public void ferVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
        frameVista.setEnabled(true);
    }

}
