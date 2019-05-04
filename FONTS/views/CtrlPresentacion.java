package views;

public class CtrlPresentacion {
    private VistaPrincipal vp;

    public CtrlPresentacion() {
        // Conectar domini
        vp = new VistaPrincipal(this);
    }

    public void iniciar() {
        // init domini
        vp.ferVisible();
    }
}