package views;

import controllers.ControladorEditor;
import controllers.ControladorLlistaProblemes;
import controllers.ControladorPrincipal;

public class CtrlPresentacion {
    private VistaPrincipal vp;
    private ControladorPrincipal cp;

    public CtrlPresentacion() {
        // Conectar domini
        cp = new ControladorPrincipal();
        vp = new VistaPrincipal(this);
    }

    public void iniciar() {
        // init domini
        vp.ferVisible();
    }

    ControladorEditor getCEditor() {
        return cp.getControladorEditor();
    }

    ControladorLlistaProblemes getCLlista() {
        return cp.getControladorLlistaProblemes();
    }

    ControladorPrincipal getPrincipal() {
        return cp;
    }


}