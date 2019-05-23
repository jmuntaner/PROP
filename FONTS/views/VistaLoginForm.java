package views;

import javax.swing.*;
import java.awt.*;

//TODO: distribucio real

/*
            TITOL
    USERNAME    textfield
    PASSWORD    passwordfield
            LOGIN

            TORNAR
 */

public class VistaLoginForm extends JPanel {
    private JButton botoLogin, botoTornar;
    private JLabel titol, username, password;
    private JPasswordField pwd;
    private JTextField usernameIn;
    private VistaPrincipal vp;
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaLoginForm(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        placeComponents();
        initBotoLogin();
        initBotoTornar();
    }

    void clearForm() {
        usernameIn.setText("");
        pwd.setText("");
    }

    private void placeComponents() {
        setLayout(null);

        username = new JLabel("Usuari");
        username.setBounds(265, 10, 80, 25);
        add(username);

        usernameIn = new JTextField(20);
        usernameIn.setBounds(355, 10, 160, 25);
        add(usernameIn);

        password = new JLabel("Contrassenya");
        password.setBounds(265, 40, 80, 25);
        add(password);

        pwd = new JPasswordField(20);
        pwd.setBounds(355, 40, 160, 25);
        add(pwd);

        botoLogin = new JButton("Login");
        botoLogin.setBounds(265, 80, 120, 28);
        add(botoLogin);

        botoTornar = new JButton("Tornar");
        botoTornar.setBounds(395, 80, 120, 28);
        add(botoTornar);
    }

    /**
     * Inicialitza el botó de logout.
     */
    private void initBotoLogin() {
        //botoLogin = new JButton("Inicia sesió");
        //botoLogin.setPreferredSize(new Dimension(140, 28));
        //botoLogin.addActionListener(e -> vp.mostraNotLogged()); //TODO: login
    }

    /**
     * Inicialitza el botó de tornada.
     */
    private void initBotoTornar() {
        //botoTornar = new JButton("Tornar");
        //botoTornar.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraNotLogged());
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.gridx = 0;
        //gbc.gridy = 6;
        //add(botoTornar, gbc);
    }
}
