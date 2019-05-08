package views;

import controllers.ControladorEditor;
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

    ControladorEditor getEditor() {
        return cp.getControladorEditor();
    }


}