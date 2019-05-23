package views;

import javax.swing.*;
import java.awt.*;

public class VistaNotLogged extends JPanel {
    private JButton botoSubmit = new JButton("Login");
    private JButton botoTornar, botoChangeMode;
    private VistaPrincipal vp;
    private JLabel titol, username, password, repassword;
    private JPasswordField pwd, repwd;
    private JTextField usernameIn;
    private boolean logreg = true; //true = login / false = register
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaNotLogged(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initForm();
        //initBotoSubmit();
        //initBotoTornar();
        //initGlues();
    }

    /**
     * Genera el formulari de login
     */
    private void initForm() {
        titol = new JLabel("Inici de sessió");
        titol.setFont(new Font("Serif", Font.BOLD, 20));
        username = new JLabel("Usuari");
        password = new JLabel("Contrassenya");
        usernameIn = new JTextField(20);
        pwd = new JPasswordField(20);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
        titol.setPreferredSize(new Dimension(200, 40));
        username.setPreferredSize(new Dimension(140, 28));
        usernameIn.setPreferredSize(new Dimension(250, 28));
        password.setPreferredSize(new Dimension(140, 28));
        pwd.setPreferredSize(new Dimension(250, 28));

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        add(titol, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(username, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        add(usernameIn, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(password, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        add(pwd, c);

        botoSubmit = new JButton("Login");
        botoTornar = new JButton("Tornar");
        botoChangeMode = new JButton("Registrar-se");
        botoSubmit.setPreferredSize(new Dimension(140, 28));
        botoTornar.setPreferredSize(new Dimension(140, 28));
        botoChangeMode.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraMenuPrincipal());
        botoChangeMode.addActionListener(e -> canviarMode());

        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 1;
        add(botoTornar, c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        add(botoSubmit, c);

        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        add(botoChangeMode, c);

        repassword = new JLabel("Confirma la pwd");
        repwd = new JPasswordField(20);

        repassword.setPreferredSize(new Dimension(140, 28));
        repwd.setPreferredSize(new Dimension(250, 28));

        if(!logreg) {
            titol.setText("Registre d'usuari");
            botoChangeMode.setText("Iniciar Sesió");
            botoSubmit.setText("Registrar");

            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 1;
            add(repassword, c);

            c.gridx = 1;
            c.gridy = 3;
            c.gridwidth = 2;
            add(repwd, c);

            botoSubmit.addActionListener(e -> register());
        }
        else {

            botoSubmit.addActionListener(e -> login());
        }
    }

    private void canviarMode() {
        logreg = !logreg;
        if(logreg) {
            botoSubmit.removeActionListener(e -> register());
            botoSubmit.addActionListener(e -> login());
            remove(repassword);
            remove(repwd);
            titol.setText("Iniciar Sesió");
            botoSubmit.setText("Login");
            botoChangeMode.setText("Registrar-se");
        }
        else {
            titol.setText("Registre d'usuari");
            botoSubmit.setText("Registrar");
            botoChangeMode.setText("Iniciar Sesió");

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(3, 3, 3, 3);

            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 1;
            add(repassword, c);

            c.gridx = 1;
            c.gridy = 3;
            c.gridwidth = 2;
            add(repwd, c);

            botoSubmit.removeActionListener(e -> login());
            botoSubmit.addActionListener(e -> register());
        }
    }

    /**
     * Inicialitza el botó de login.
     */
    private void initBotoSubmit() {
        botoSubmit.setPreferredSize(new Dimension(140, 28));
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipadx = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoSubmit, gbc);
    }

    void clearForm() {
        usernameIn.setText("");
        pwd.setText("");
        repwd.setText("");
    }

    /**
     * Executa el login
     */
    private void login() {
        String usuari = usernameIn.getText().trim();
        char[] pass = pwd.getPassword();
        char[] confirmation = repwd.getPassword();
        //TODO: login
        clearForm();
    }

    /**
     * Executa el registre
     */
    private void register() {
        String usuari = usernameIn.getText().trim();
        char[] pass = pwd.getPassword();
        char[] confirmation = repwd.getPassword();
        boolean match = (pass.length == confirmation.length);
        int i = 0;
        while(match && i < pass.length) {
            if(pass[i]!=confirmation[i]) match = false;
            ++i;
        }
        if(!match) {
            vp.missatgeError("Les contrassenyes no coincideixen");
            pwd.setText("");
            repwd.setText("");
            return;
        }
        //TODO: registre
        clearForm();
    }
}
