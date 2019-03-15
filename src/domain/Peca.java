package domain;

import javafx.util.Pair;

import java.util.ArrayList;

public abstract class Peca {
    private boolean color;
    private Pair<Integer, Integer> posicio;

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Pair<Integer, Integer> getPosicio() {
        return posicio;
    }

    public void setPosicio(Pair<Integer, Integer> posicio) {
        this.posicio = posicio;
    }

    public abstract ArrayList<Moviment> getMovimentsValids(String ocupacio);
}
