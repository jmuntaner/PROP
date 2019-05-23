package views;

import controllers.ControladorAnalisi;
import controllers.ControladorPartida;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Vista principal del programa
 */
public class VistaPrincipal {
    private CtrlPresentacion mCp;
    private JFrame frameVista;
    private VistaMenuPrincipal panelMenuPrincipal;
    private VistaJugar panelJugar;
    private VistaEditor panelEditor;
    private VistaLlistaProblemes panelLlistaProblemes;
    private VistaFiPartida panelResultats;
    private JPanel contentPane;
    private VistaRankingProblema panelRanking;
    private VistaAnalisiPartides panelAnalisi;
    private VistaLogged panelLogged;
    private VistaNotLogged panelNotLogged;
    private VistaLoginForm panelLoginForm;
    private VistaRegisterForm panelRegisterForm;
    private VistaEstadistiquesJugador panelEstadistiques;

    /**
     * Creadora per defecte
     *
     * @param cp Control de presentació
     */
    public VistaPrincipal(CtrlPresentacion cp) {
        mCp = cp;

        initFrameVista();
        initMenuPrincipal();
        initJugar();
        initEditor();
        initLlistaProblemes();
        initResultats();
        initRanking();
        initAnalisi();
        initLogged();
        initNotLogged();
        initLoginForm();
        initRegisterForm();
        initEstadistiques();

        addPanels();

        mostraMenuPrincipal();
    }

    /**
     * Inicialitza el JFrame principal.
     */
    private void initFrameVista() {
        frameVista = new JFrame("Vista Principal");
        // Mida inicial
        frameVista.setMinimumSize(new Dimension(800, 500));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(true);

        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Inicialitza la vista de menu principal.
     */
    private void initMenuPrincipal() {
        panelMenuPrincipal = new VistaMenuPrincipal(this);
    }

    /**
     * Inicialitza la vista de partida.
     */
    private void initJugar() {
        panelJugar = new VistaJugar(this);
    }

    /**
     * Inicialitza la vista d'editor.
     */
    private void initEditor() {
        panelEditor = new VistaEditor(this, mCp.getCEditor());
    }

    /**
     * Inicialitza la vista de llista de problemes.
     */
    private void initLlistaProblemes() {
        panelLlistaProblemes = new VistaLlistaProblemes(this, mCp.getCLlista());
    }

    /**
     * Inicialitza la vista de resultats de partida.
     */
    private void initResultats() {
        panelResultats = new VistaFiPartida(this);
    }

    /**
     * Inicialitza la vista de ranking de problema.
     */
    private void initRanking() {
        panelRanking = new VistaRankingProblema(this);
    }

    private void initAnalisi() {
        panelAnalisi = new VistaAnalisiPartides(this);
    }

    /**
     * Inicialitza la vista de perfil logged.
     */
    private void initLogged() {
        panelLogged = new VistaLogged(this);
    }

    /**
     * Inicialitza la vista de perfil no logged.
     */
    private void initNotLogged() {
        panelNotLogged = new VistaNotLogged(this);
    }

    /**
     * Inicialitza la vista d'estadistiques.
     */
    private void initEstadistiques() {
        panelEstadistiques = new VistaEstadistiquesJugador(this);
    }

    /**
     * Inicialitza la vista d'estadistiques.
     */
    private void initLoginForm() {
        panelLoginForm = new VistaLoginForm(this, mCp);
    }

    /**
     * Inicialitza la vista d'estadistiques.
     */
    private void initRegisterForm() {
        panelRegisterForm = new VistaRegisterForm(this, mCp);
    }

    /**
     * Afegeix els panels de totes les vistes al CardLayout principal.
     */
    private void addPanels() {
        contentPane = (JPanel) frameVista.getContentPane();
        contentPane.setLayout(new CardLayout());
        contentPane.add(panelMenuPrincipal, "menu");
        contentPane.add(panelJugar, "jugar");
        contentPane.add(panelEditor, "editar");
        contentPane.add(panelLlistaProblemes, "llistaProbs");
        contentPane.add(panelResultats, "resultats");
        contentPane.add(panelRanking, "ranking");
        contentPane.add(panelAnalisi, "analisi");
        contentPane.add(panelEstadistiques, "estadistiques");
        contentPane.add(panelNotLogged, "notLogged");
        contentPane.add(panelLogged, "logged");
        contentPane.add(panelRegisterForm, "registerForm");
        contentPane.add(panelLoginForm, "loginForm");
    }

    /**
     * Mostra la vista de partida.
     */
    private void mostraJugar() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "jugar");
    }

    /**
     * Mostra la vista d'editor de problemes.
     */
    private void mostraEditar() {
        panelEditor.actualitza();
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "editar");
    }

    /**
     * Mostra la vista de menu principal.
     */
    void mostraMenuPrincipal() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "menu");
    }

    /**
     * Mostra la vista de llista de problemes.
     */
    void mostraLlistaProblemes() {
        panelLlistaProblemes.update();
        CardLayout cl = (CardLayout) contentPane.getLayout();

        cl.show(contentPane, "llistaProbs");
    }

    /**
     * Inicia l'edició d'un problema.
     *
     * @param nom Nom del problema a editar.
     */
    void editaProblema(String nom) {
        mCp.getCEditor().carregaProblema(nom);
        mostraEditar();
    }

    /**
     * Inicia la creació d'un problema nou.
     */
    void creaProblema() {
        mCp.getCEditor().creaProblema();
        mostraEditar();
    }

    /**
     * Inicia una partida.
     *
     * @param cPart Controlador de la partida a iniciar.
     */
    void jugaProblema(ControladorPartida cPart) {
        panelJugar.setControlador(cPart);
        mostraJugar();
    }

    /**
     * Finalitza una partida i mostra els resultats.
     *
     * @param cPart Controlador de la partida a finalitzar.
     */
    void fiPartida(ControladorPartida cPart) {
        cPart.finalitzaPartida();
        panelResultats.setControlador(cPart);
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "resultats");
    }

    /**
     * Mostra un ranking a pantalla.
     *
     * @param ranking El ranking a mostrar
     */
    void mostraRanking(ArrayList<Pair<String, String>> ranking) {
        panelRanking.setRanking(ranking);
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "ranking");
    }

    /**
     * Mostra les estadistiques a pantalla.
     */
    void mostraEstadistiques() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "estadistiques");
    }

    /**
     * Mostra perfil Logged a pantalla.
     */
    void mostraLogged() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "logged");
    }

    /**
     * Mostra perfil NotLogged a pantalla.
     */
    void mostraNotLogged() {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "notLogged");
    }

    /**
     * Mostra el formulari de iniciar sesió a la pantalla.
     */
    void mostraLoginForm() {
        panelLoginForm.clearForm();
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "loginForm");
    }

    /**
     * Mostra el formulari de registre a la pantalla.
     */
    void mostraRegisterForm() {
        panelRegisterForm.clearForm();
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "registerForm");
    }


    /**
     * Inicia la vista d'analisi de conjunt de problemes.
     *
     * @param ca Controlador per a l'analisi.
     */
    void iniciaAnalisi(ControladorAnalisi ca) {
        panelAnalisi.setAnalisi(ca);
        CardLayout cl = (CardLayout) contentPane.getLayout();
        cl.show(contentPane, "analisi");
    }

    /**
     * Crea un missatge d'error
     */
    void missatgeError(String missatge) {
        JOptionPane.showMessageDialog(frameVista, missatge, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Fa visible el JFrame principal
     */
    void ferVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
        frameVista.setEnabled(true);
    }
}
