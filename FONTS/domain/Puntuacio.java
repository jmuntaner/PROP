package domain;

public interface Puntuacio<T> {


    /**
     * Funció de comparació de puntuacions
     *
     * @param altre Objecte amb el que comparar
     * @return True si l'objecte des del que es crida la funció és millor que el paràmetre, del contrari false.
     */
    boolean esMillor(T altre);

    /**
     * Permet representar puntuacions en format String
     *
     * @return Cadena de caràcters que representa l'objecte
     */
    String representa();
}
