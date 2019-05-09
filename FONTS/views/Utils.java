package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Utils {
    static ImageIcon getIconPeca(char c, int size) {
        // Source: https://marcelk.net/chess/pieces/
        BufferedImage orig = null;
        try {
            orig = ImageIO.read(new File("res/piezas/" + c + ".png"));
            return new ImageIcon(orig.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
