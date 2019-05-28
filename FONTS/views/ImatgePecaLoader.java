package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Carregador d'imatges de peces amg cache.
 */
class ImatgePecaLoader {

    private static ImatgePecaLoader me;
    private final ImageIcon[] lastGenerated;
    private int lastSize;


    /**
     * Creadora per defecte
     */
    private ImatgePecaLoader() {
        lastSize = -1;
        lastGenerated = new ImageIcon[Utils.llistaFitxes.length()];
    }

    /**
     * Obté la instància Singleton del carregador.
     *
     * @return
     */
    static ImatgePecaLoader getLoader() {
        if (me == null) me = new ImatgePecaLoader();
        return me;
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
    ImageIcon getIcon(char peca, int size) {
        try {
            char c = peca;
            char color = 'b';
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
                color = 'w';
            }
            // Source: https://marcelk.net/chess/pieces/
            BufferedImage orig = ImageIO.read(this.getClass().getResource("/res/piezas/" + c + "_" + color + ".png"));
            return new ImageIcon(orig.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obté la imatge en cache d'una peça.
     * <p>
     * Aquesta versió fa servir una cache d'imatges per a millorar la eficiència.
     * </p>
     *
     * @param c    Identificador de la peça.
     * @param size Mida de la imatge desitjada.
     * @return Imatge de la peça en la mida especificada.
     */
    ImageIcon getCachedIcon(char c, int size) {
        if (lastSize != size) regenerate(size);
        return lastGenerated[Utils.llistaFitxes.indexOf(c)];
    }

    /**
     * Regenera la cache d'imatges de peça.
     *
     * @param size Mida de les imatges.
     */
    private void regenerate(int size) {
        for (int i = 0; i < Utils.llistaFitxes.length(); i++) {
            char c = Utils.llistaFitxes.charAt(i);
            lastGenerated[i] = getIcon(c, size);
        }
        lastSize = size;
    }
}
