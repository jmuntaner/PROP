package views;

public class Escacs {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                ctrlPresentacion.iniciar();
            }
        });
    }
}
