package controllers;

import domain.Problema;

import java.util.ArrayList;

public class ControladorPrincipal {
    ArrayList<Problema> probs;
    public ControladorPrincipal() {
        probs = new ArrayList<>();
    }

    public ControladorEditor getControladorEditor() {
        return new ControladorEditor(this);
    }

    void afegeixProblema(Problema p) {
        probs.add(p);
    }

    int getNumProblemes() {
        return probs.size();
    }

    Problema getProblema(int index) {
        return probs.get(index);
    }
}
