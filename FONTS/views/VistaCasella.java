package views;

import javax.swing.*;
import java.awt.*;

public class VistaCasella extends JPanel {
    private int x, y;

    VistaCasella(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        add(new JButton(" "));
        Color c;
        if ((x + y) % 2 == 0) c = new Color(255, 255, 255);
        else c = new Color(0, 0, 0);
        setBackground(c);
        setSize(100, 100);
    }
}
