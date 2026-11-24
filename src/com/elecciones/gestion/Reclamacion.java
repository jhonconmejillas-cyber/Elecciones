package com.elecciones.gestion;

import com.elecciones.modelo.Mesa;

/**
 * Representa una reclamación sobre los resultados de una mesa.
 * Rol en State: CONTEXTO (delega su comportamiento en el estado actual).
 * Autor: Jhon Mejia - feature/incidente-reclamo
 */
public class Reclamacion {
    private String idReclamacion;
    private String descripcion;
    private Mesa mesaAsociada;
    private String solicitante; // partido o testigo
    private EstadoReclamacion estado; // pendiente -> en revision -> resuelta

    public Reclamacion(String idReclamacion, String descripcion, Mesa mesaAsociada, String solicitante) {
        this.idReclamacion = idReclamacion;
        this.descripcion = descripcion;
        this.mesaAsociada = mesaAsociada;
        this.solicitante = solicitante;
        this.estado = new ReclamacionPendiente();
    }

    public void revisarReclamacion() {
        estado.revisar(this);
        System.out.println("Reclamacion " + idReclamacion + " puesta en revision.");
    }

    public void resolverReclamacion() {
        estado.resolver(this);
    }

    /** Solo los estados (mismo paquete) pueden cambiar el estado del contexto. */
    void setEstado(EstadoReclamacion estado) {
        this.estado = estado;
    }

    public String getIdReclamacion() { return idReclamacion; }
    public String getDescripcion() { return descripcion; }
    public Mesa getMesaAsociada() { return mesaAsociada; }
    public String getSolicitante() { return solicitante; }
    public String getEstado() { return estado.nombre(); }

    @Override
    public String toString() {
        return "Reclamacion{" + descripcion + ", estado=" + estado.nombre() + "}";
    }
}