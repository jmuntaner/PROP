package views;

/**
 * Classe principal del programa.
 */
public class Escacs {
    /**
     * Inicia el programa.
     *
     * @param args Arguments del programa.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
            ctrlPresentacion.iniciar();
        });
    }
}
