package views;

import javax.swing.*;
import java.awt.*;

//TODO: distribucio real

/*
            TITOL
    USERNAME    textfield
    PASSWORD    passwordfield
    REPEAT P    passwordfield
            LOGIN

            TORNAR
 */

public class VistaRegisterForm extends JPanel {
    private JButton botoLogin, botoTornar;
    private JLabel titol, username, password, repassword;
    private JPasswordField pwd, repwd;
    private JTextField usernameIn;
    private VistaPrincipal vp;

    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaRegisterForm(VistaPrincipal vp) {
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
        repwd.setText("");
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

        repassword = new JLabel("Confirma la contrassenya"); //TODO: el text se surt -> ajustar proporcions
        repassword.setBounds(265, 70, 80, 25);
        add(repassword);

        repwd = new JPasswordField(20);
        repwd.setBounds(355, 70, 160, 25);
        add(repwd);

        botoLogin = new JButton("Registro");
        botoLogin.setBounds(265, 110, 120, 28);
        add(botoLogin);

        botoTornar = new JButton("Tornar");
        botoTornar.setBounds(395, 110, 120, 28);
        add(botoTornar);
    }

    /**
     * Inicialitza el botó de login.
     */
    private void initBotoLogin() {
        //botoLogin = new JButton("Inicia sesió");
        //botoLogin.setPreferredSize(new Dimension(140, 28));
        //botoLogin.addActionListener(e -> vp.mostraNotLogged()); //TODO: login
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.gridx = 0;
        //gbc.gridy = 4;
        //add(botoLogin, gbc);

    }

    /**
     * Inicialitza el botó de tornada.
     */
    private void initBotoTornar() {
        //botoTornar = new JButton("Tornar");
        //botoTornar.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraNotLogged());
        /*GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(botoTornar, gbc);*/
    }
}
