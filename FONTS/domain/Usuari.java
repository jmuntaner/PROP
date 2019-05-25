package domain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class Usuari implements Serializable  {
    private String nom, hashPassword;
    private final String id;
    private final Set<Integer> problemesSuperats;
    private final EstadistiquesJugador estadistiques;

    public Usuari(String id, String nom, String contrasenya) {
        this.id = id;
        this.hashPassword = hashPass(contrasenya);
        this.nom = nom;
        problemesSuperats = new HashSet<>();
        estadistiques = new EstadistiquesJugador();
    }

    /**
     * Crea el hash SHA-1 d'una contrasenya.
     *
     * @param pass Contrasenya a processar.
     * @return Hash SHA-1 de la contrasenya.
     */
    private static String hashPass(String pass) {
        // https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
        String hashedPass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(pass.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte mByte : bytes) {
                sb.append(Integer.toString((mByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPass = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPass;
    }

    /**
     * Obté la id de l'usuari.
     *
     * @return Id d'usuari de l'usuari.
     */
    public String getId() {
        return id;
    }

    /**
     * Obté el nom de l'usuari.
     *
     * @return nom de l'usuari.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifica el nom de l'usuari
     *
     * @param nom Nou nom de l'usuari.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifica la contrasenya de l'usuari.
     * <p>
     * La contrasenya és guardada com a hash a l'objecte.
     * </p>
     *
     * @param password Nova contrasenya.
     */
    public void setPass(String password) {
        hashPassword = hashPass(password);
    }

    /**
     * Comprova si una contrasenya és correcta
     *
     * @param pass Contrasenya a comprovar.
     * @return Vertader si la contrasenya és correcta.
     */
    public boolean checkPass(String pass) {
        return hashPassword.equals(hashPass(pass));
    }

    /**
     * Obté la llista de problemes superats
     *
     * @return Set
     */
    public Set<Integer> getProblemesSuperats() {
        return problemesSuperats;
    }

    /**
     * Afegeix un problema a la llista de problemes superats.
     *
     * @param pid Id del Problema a afegir a la llista.
     */
    public void superaProblema(int pid) {
        problemesSuperats.add(pid);
    }

    /**
     * Retorna les estadistiques del jugador
     */
    public EstadistiquesJugador getStatistics() {return estadistiques;}


}
