package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Utils {
    static final String llistaFitxes = "KQRNBPkqrnbp";
    private static ImageIcon[] lastGenerated = new ImageIcon[llistaFitxes.length()];
    private static int lastSize = -1;

    static ImageIcon getIconPeca(char c, int size) {
        if (lastSize != size) regenerate(size);
        return lastGenerated[llistaFitxes.indexOf(c)];
    }

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
}
