package views;

import controllers.ControladorUsuari;

import javax.swing.*;
import java.awt.*;

class VistaNotLogged extends JPanel {
    private ControladorUsuari cu;
    private JButton botoSubmit;
    private JButton botoChangeMode;
    private VistaPrincipal vp;
    private JLabel titol, repassword;
    private JPasswordField pwd, repwd;
    private JTextField usernameIn;
    private boolean logreg = true; //true = login / false = register

    /**
     * Creadora per defecte
     *
     * @param vp Vista principal.
     */
    VistaNotLogged(VistaPrincipal vp, ControladorUsuari cu) {
        super();
        setLayout(new GridBagLayout());
        this.vp = vp;
        this.cu = cu;
        initForm();
    }

    /**
     * Genera el formulari de login
     */
    private void initForm() {
        titol = new JLabel("Inici de sessió");
        Font defaultFont = titol.getFont();
        titol.setFont(new Font(defaultFont.getName(), Font.BOLD, 20));
        JLabel username = new JLabel("Usuari");
        JLabel password = new JLabel("Contrasenya");
        usernameIn = new JTextField(20);
        pwd = new JPasswordField(20);

        //Nova grid + external padding
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);

        //Tamanys preferits
        Dimension sizeBoto = new Dimension(140, 28);
        Dimension sizeCamp = new Dimension(250, 28);
        titol.setPreferredSize(new Dimension(200, 40));
        username.setPreferredSize(sizeBoto);
        usernameIn.setPreferredSize(sizeCamp);
        password.setPreferredSize(sizeBoto);
        pwd.setPreferredSize(sizeCamp);

        // Titol

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        add(titol, c);

        // Afegir formulari comu

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


        //Boto tornar

        JButton botoTornar = new JButton("Tornar");
        botoTornar.setPreferredSize(sizeBoto);
        botoTornar.addActionListener(e -> vp.mostraMenuPrincipal());
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 1;
        add(botoTornar, c);


        // Boto submit
        botoSubmit = new JButton("Login");
        botoSubmit.setPreferredSize(sizeBoto);
        botoSubmit.addActionListener(e -> submit());

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        add(botoSubmit, c);


        // Botó changeMode

        botoChangeMode = new JButton("Registrar-se");
        botoChangeMode.setPreferredSize(sizeBoto);
        botoChangeMode.addActionListener(e -> canviarMode());

        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        add(botoChangeMode, c);


        //Definició formulari extra (parts del registre)

        repassword = new JLabel("Confirma la pwd");
        repwd = new JPasswordField(20);
        repassword.setPreferredSize(sizeBoto);
        repwd.setPreferredSize(sizeCamp);

        canvisVisuals();
    }

    /**
     * Canvia de mode entre iniciar sessió i registrar-se
     */
    private void canviarMode() {
        logreg = !logreg;
        canvisVisuals();
        clearForm();
    }

    /**
     * Mostra la vista de login.
     */
    void showLogin() {
        logreg = true;
        canvisVisuals();
        clearForm();
    }

    /**
     * Efectua els canvis visuals entre Iniciar Sessió i Registrar-se
     */
    private void canvisVisuals() {
        if (logreg) {
            remove(repassword);
            remove(repwd);
            titol.setText("Iniciar Sessió");
            botoSubmit.setText("Login");
            botoChangeMode.setText("Registrar-se");
        } else {
            titol.setText("Registre d'usuari");
            botoSubmit.setText("Registrar");
            botoChangeMode.setText("Iniciar Sessió");

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
        }
    }

    void clearForm() {
        usernameIn.setText("");
        pwd.setText("");
        repwd.setText("");
    }

    private void submit() {
        if (logreg) login();
        else register();
    }

    /**
     * Executa el login
     */
    private void login() {
        String usuari = usernameIn.getText().trim();
        char[] pass = pwd.getPassword();
        //existeix l'usuari
        if (!cu.existeixUsuari(usuari)) {
            vp.missatgeError("L'usuari introduït no existeix");
            usernameIn.setText("");
            return;
        }
        //contrasenya erronea
        if (!cu.authenticate(usuari, String.valueOf(pass))) {
            vp.missatgeError("Contrasenya errònea");
            pwd.setText("");
            return;
        }
        cu.selectUsuari(usuari);
        clearForm();
        vp.mostraLogged();
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
        while (match && i < pass.length) {
            if (pass[i] != confirmation[i]) match = false;
            ++i;
        }
        if (!match) {
            vp.missatgeError("Les contrasenyes no coincideixen");
            pwd.setText("");
            repwd.setText("");
            return;
        }
        //nom buit
        if (usuari.equals("")) {
            vp.missatgeError("El camp del nom està sense omplir, omple'l per a continuar");
            return;
        }
        //nom = guest -> invalid
        if (usuari.equals("guest")) {
            vp.missatgeError("El nom introduït és invàlid, escull un altre nom per a continuar");
            return;
        }
        if (cu.existeixUsuari(usuari)) {
            vp.missatgeError("Ja existeix un usuari amb aquest nom, escull un altre nom si us plau");
            usernameIn.setText("");
            return;
        }
        String contra = String.valueOf(pass);
        cu.register(usuari, contra);
        clearForm();
        vp.mostraLogged();
    }
}
