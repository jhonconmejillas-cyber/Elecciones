package com.elecciones.gestion;

/** La reclamación está siendo revisada por las autoridades electorales. */
public class ReclamacionEnRevision implements EstadoReclamacion {

    @Override
    public String nombre() {
        return "en revision";
    }

    @Override
    public void revisar(Reclamacion reclamacion) {
        throw new IllegalStateException("La reclamación ya está en revisión");
    }

    @Override
    public void resolver(Reclamacion reclamacion) {
        reclamacion.setEstado(new ReclamacionResuelta());
    }
}