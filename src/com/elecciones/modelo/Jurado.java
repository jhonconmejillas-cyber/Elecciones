package com.elecciones.modelo;

/**
 * Representa un jurado de votación asignado a una mesa.
 * Autor: Carlos Camacho - feature/candidato-partido
 */
public class Jurado {
    private String idJurado;
    private String nombre;
    private String funcion;
    private Mesa mesaAsignada;

    public Jurado(String idJurado, String nombre, String funcion) {
        this.idJurado = idJurado;
        this.nombre = nombre;
        this.funcion = funcion;
    }

    public void atenderVotante() {
        System.out.println("Jurado " + nombre + " atendiendo votante en mesa " +
                (mesaAsignada != null ? mesaAsignada.getNumeroMesa() : "N/A"));
    }

    public void registrarResultado(Acta acta, Candidato candidato, int votos) {
        acta.consignarResultado(candidato, votos);
    }

    public String getIdJurado() { return idJurado; }
    public String getNombre() { return nombre; }
    public String getFuncion() { return funcion; }
    public Mesa getMesaAsignada() { return mesaAsignada; }
    public void setMesaAsignada(Mesa mesaAsignada) { this.mesaAsignada = mesaAsignada; }

    @Override
    public String toString() {
        return "Jurado{" + nombre + ", funcion=" + funcion + "}";
    }
}