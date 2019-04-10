package domain;

public interface Puntuacio<T> {
    boolean esMillor(T altre);

    String representa();
}
