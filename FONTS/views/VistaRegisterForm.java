package views;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/*
            TITOL
    USERNAME    textfield
    PASSWORD    passwordfield
    REPEAT P    passwordfield
            LOGIN

            TORNAR
 */

public class VistaRegisterForm extends JPanel {
    private JButton botoRegistre, botoTornar;
    private JLabel titol, username, password, repassword; //TODO: títol
    private JPasswordField pwd, repwd;
    private JTextField usernameIn;
    private VistaPrincipal vp;
    private CtrlPresentacion cp;

    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaRegisterForm(VistaPrincipal vp, CtrlPresentacion cp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;
        this.cp = cp;

        placeComponents();
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

        botoRegistre = new JButton("Registrar");
        botoRegistre.setBounds(265, 110, 120, 28);
        add(botoRegistre);

        botoTornar = new JButton("Tornar");
        botoTornar.setBounds(395, 110, 120, 28);
        add(botoTornar);

        //Assigna els listeners als botons

        botoRegistre.addActionListener(e -> register());
        botoTornar.addActionListener(e -> vp.mostraNotLogged());
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
    }
}
