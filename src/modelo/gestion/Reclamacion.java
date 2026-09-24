package com.elecciones.gestion;

import com.elecciones.modelo.Mesa;

/**
 * Representa una reclamación sobre los resultados de una mesa.
 * Autor: Jhon Mejia - feature/incidente-reclamo
 */
public class Reclamacion {
    private String idReclamacion;
    private String descripcion;
    private Mesa mesaAsociada;
    private String solicitante; // partido o testigo
    private String estado; // "pendiente", "en revision", "resuelta"

    public Reclamacion(String idReclamacion, String descripcion, Mesa mesaAsociada, String solicitante) {
        this.idReclamacion = idReclamacion;
        this.descripcion = descripcion;
        this.mesaAsociada = mesaAsociada;
        this.solicitante = solicitante;
        this.estado = "pendiente";
    }

    public void revisarReclamacion() {
        this.estado = "en revision";
        System.out.println("Reclamacion " + idReclamacion + " puesta en revision.");
    }

    public void resolverReclamacion() {
        this.estado = "resuelta";
    }

    public String getIdReclamacion() { return idReclamacion; }
    public String getDescripcion() { return descripcion; }
    public Mesa getMesaAsociada() { return mesaAsociada; }
    public String getSolicitante() { return solicitante; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return "Reclamacion{" + descripcion + ", estado=" + estado + "}";
    }
}
