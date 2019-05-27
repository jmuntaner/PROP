package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utilitats per a la capa de presentació.
 */
class Utils {
    static final String llistaFitxes = "KQRNBPkqrnbp";
    private static final ImageIcon[] lastGenerated = new ImageIcon[llistaFitxes.length()];
    private static int lastSize = -1;

    /**
     * Obté la imatge d'una peça.
     * <p>
     * Aquesta versió fa servir una cache d'imatges per a millorar la eficiència.
     * </p>
     *
     * @param c    Identificador de la peça.
     * @param size Mida de la imatge desitjada.
     * @return Imatge de la peça en la mida especificada.
     */
    static ImageIcon getIconPeca(char c, int size) {
        if (lastSize != size) regenerate(size);
        return lastGenerated[llistaFitxes.indexOf(c)];
    }

    /**
     * Regenera la cache d'imatges de peça.
     *
     * @param size Mida de les imatges.
     */
    private static void regenerate(int size) {
        BufferedImage orig;
        for (int i = 0; i < llistaFitxes.length(); i++) {
            char c = llistaFitxes.charAt(i);
            char color = 'b';
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
                color = 'w';
            }
            try {
                // Source: https://marcelk.net/chess/pieces/
                orig = ImageIO.read(new File("res/piezas/" + c + "_" + color + ".png"));
                lastGenerated[i] = new ImageIcon(orig.getScaledInstance(size, size, Image.SCALE_SMOOTH));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        lastSize = size;
    }

    /**
     * Obté la imatge d'una peça.
     * <p>
     * Aquesta versió no fa servir la cache.
     * Utilitzar-la només en casos on no es recarregui la imatge per a evitar colisions de threads.
     * </p>
     *
     * @param peca Identificador de la peça.
     * @param size Mida de la imatge desitjada.
     * @return Imatge de la peça en la mida especificada.
     */
    static ImageIcon rawGetIconPeca(char peca, int size) {
        try {
            char c = peca;
            char color = 'b';
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
                color = 'w';
            }
            // Source: https://marcelk.net/chess/pieces/
            BufferedImage orig = ImageIO.read(new File("res/piezas/" + c + "_" + color + ".png"));
            return new ImageIcon(orig.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

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
