package com.elecciones.gestion;

/** Estado inicial: la reclamación fue radicada y espera revisión. */
public class ReclamacionPendiente implements EstadoReclamacion {

    @Override
    public String nombre() {
        return "pendiente";
    }

    @Override
    public void revisar(Reclamacion reclamacion) {
        reclamacion.setEstado(new ReclamacionEnRevision());
    }

    @Override
    public void resolver(Reclamacion reclamacion) {
        throw new IllegalStateException("Una reclamación pendiente debe revisarse antes de resolverse");
    }
}