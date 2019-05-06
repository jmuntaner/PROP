package views;


import javax.swing.*;
import java.awt.*;

public class VistaTauler extends JPanel {


    VistaTauler() {
        super();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 20;
        gbc.ipady = 20;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                VistaCasella vc = new VistaCasella(i, j);
                gbc.gridx = i;
                gbc.gridy = j;
                add(vc, gbc);
            }
    }
}
