package views;


import java.io.File;

/**
 * Utilitats per a la capa de presentació.
 */
class Utils {
    static final String llistaFitxes = "KQRNBPkqrnbp";

    /**
     * Obté l'extensió d'un arxiu.
     *
     * @param f Arxiu.
     * @return Extensió d el'arxiu f.
     */
    static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
