package views;

import javax.swing.*;
import java.awt.*;

//TODO: arreglar posicionament botons

public class VistaNotLogged extends JPanel {
    private JButton botoLogin, botoRegister, botoTornar;
    private VistaPrincipal vp;

    //Components registre
    private JButton botoRegistre;
    private JLabel titol, username, password, repassword; //TODO: títol
    private JPasswordField pwd, repwd;
    private JTextField usernameIn;
    /*
    (BOTÓ LOGIN -> FORMULARI LOGIN) // (BOTÓ REGISTRE -> FORMULARI REGISTRE) // TORNAR
     */
    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaNotLogged(VistaPrincipal vp) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;

        initBotoLogin();
        initBotoRegister();
        initBotoTornar();
        initGlues();
    }

    /**
     * Inicialitza les separacions entre elements del menu.
     */
    private void initGlues() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(Box.createGlue(), gbc);
        gbc.gridy = 5;
        add(Box.createGlue(), gbc);
        gbc.gridy = 7;
        gbc.weighty = 1;
        add(Box.createGlue(), gbc);
    }

    /**
     * Inicialitza el botó de login.
     */
    private void initBotoLogin() {
        botoLogin = new JButton("Login");
        //botoLogin.addActionListener(e -> vp.mostraLoginForm());
        botoLogin.setPreferredSize(new Dimension(140, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.ipadx = 50;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botoLogin, gbc);
    }

    /**
     * Inicialitza el botó d'accés al perfil d'usuari.
     */
    private void initBotoRegister() {
        botoRegister = new JButton("Register");
        botoRegister.setPreferredSize(new Dimension(140, 28));
        //botoRegister.addActionListener(e -> vp.mostraRegisterForm());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(botoRegister, gbc);

    }

    /**
     * Inicialitza el botó de sortida
     */
    private void initBotoTornar() {
        botoTornar = new JButton("Tornar");
        botoTornar.setPreferredSize(new Dimension(140, 28));
        botoTornar.addActionListener(e -> vp.mostraMenuPrincipal());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(botoTornar, gbc);
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
