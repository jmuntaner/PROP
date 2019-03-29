package domain;

public enum Color {
    BLANC, NEGRE;

    /**
     * Obté el color del següent torn.
     *
     * @return Color oposat.
     */
    public Color getNext() {
        if (this == BLANC) return NEGRE;
        else return BLANC;
    }
}
