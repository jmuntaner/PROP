package views;

import javax.swing.*;
import java.awt.*;

/*
            TITOL
    USERNAME    textfield
    PASSWORD    passwordfield
            LOGIN

            TORNAR
 */

public class VistaLoginForm extends JPanel {
    private JButton botoLogin, botoTornar;
    private JLabel titol, username, password; //TODO: titol
    private JPasswordField pwd;
    private JTextField usernameIn;
    private VistaPrincipal vp;
    private CtrlPresentacion cp;
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaLoginForm(VistaPrincipal vp, CtrlPresentacion cp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;
        this.cp = cp;

        placeComponents();
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

        // Assigna els listeners als botons
        botoLogin.addActionListener(e -> login());
        botoTornar.addActionListener(e -> vp.mostraNotLogged());
    }

    /**
     * Executa el login
     */
    private void login() {
        //TODO: login
    }
}
