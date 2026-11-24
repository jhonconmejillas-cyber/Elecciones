package com.elecciones.gestion;

import com.elecciones.modelo.PuestoVotacion;

/**
 * Representa un incidente ocurrido durante la jornada electoral.
 * Autor: Jhon Mejia - feature/incidente-reclamo
 */
public class Incidente {
    private String idIncidente;
    private String descripcion;
    private PuestoVotacion puestoVotacion;
    private String fecha;

    public Incidente(String idIncidente, String descripcion, PuestoVotacion puestoVotacion, String fecha) {
        this.idIncidente = idIncidente;
        this.descripcion = descripcion;
        this.puestoVotacion = puestoVotacion;
        this.fecha = fecha;
    }

    public String getIdIncidente() { return idIncidente; }
    public String getDescripcion() { return descripcion; }
    public PuestoVotacion getPuestoVotacion() { return puestoVotacion; }
    public String getFecha() { return fecha; }

    @Override
    public String toString() {
        return "Incidente{" + descripcion + " en " + puestoVotacion.getNombre() + "}";
    }
}
