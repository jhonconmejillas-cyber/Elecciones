package com.elecciones.gestion;

/** Estado final: no admite más transiciones. */
public class ReclamacionResuelta implements EstadoReclamacion {

    @Override
    public String nombre() {
        return "resuelta";
    }

    @Override
    public void revisar(Reclamacion reclamacion) {
        throw new IllegalStateException("Una reclamación resuelta no puede revisarse de nuevo");
    }

    @Override
    public void resolver(Reclamacion reclamacion) {
        throw new IllegalStateException("La reclamación ya fue resuelta");
    }
}