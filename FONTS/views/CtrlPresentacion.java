package views;

import controllers.ControladorEditor;
import controllers.ControladorLlistaProblemes;
import controllers.ControladorPrincipal;
import controllers.ControladorUsuari;

/**
 * Controlador de la capa de presentació.
 */
public class CtrlPresentacion {
    private VistaPrincipal vp;
    private ControladorPrincipal cp;

    /**
     * Creadora per defecte.
     */
    public CtrlPresentacion() {
        // Conectar domini
        cp = new ControladorPrincipal();
        vp = new VistaPrincipal(this);
    }

    /**
     * Inicia la vista principal.
     */
    public void iniciar() {
        // init domini
        vp.ferVisible();
    }

    /**
     * Obté del domini el controlador principal.
     *
     * @return Controlador principal de domini.
     */
    ControladorPrincipal getPrincipal() {
        return cp;
    }

    /**
     * Obté del domini un controlador d'editor.
     *
     * @return Controlador d'editor.
     */
    ControladorEditor getCEditor() {
        return cp.getControladorEditor();
    }

    /**
     * Obté del domini un controlador de llista de problemes.
     *
     * @return Controlador de llista de problemes.
     */
    ControladorLlistaProblemes getCLlista() {
        return cp.getControladorLlistaProblemes();
    }

    /**
     * Obté del domini un controlador d'usuari.
     *
     * @return Controlador d'usuari.
     */
    ControladorUsuari getCUser() { return cp.getControladorUsuari(); }


}