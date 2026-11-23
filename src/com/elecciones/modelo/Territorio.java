package com.elecciones.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un territorio (departamento o municipio).
 * Autor: Juan Maldonado - feature/proceso-territorio
 */
public class Territorio {
    private String idTerritorio;
    private String nombre;
    private String tipo; // "departamento" o "municipio"
    private Territorio territorioPadre; // null si es departamento
    private List<PuestoVotacion> puestosVotacion;

    public Territorio(String idTerritorio, String nombre, String tipo, Territorio territorioPadre) {
        this.idTerritorio = idTerritorio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.territorioPadre = territorioPadre;
        this.puestosVotacion = new ArrayList<>();
    }

    public void agregarPuestoVotacion(PuestoVotacion p) {
        puestosVotacion.add(p);
    }

    public String getIdTerritorio() { return idTerritorio; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public Territorio getTerritorioPadre() { return territorioPadre; }
    public List<PuestoVotacion> getPuestosVotacion() { return puestosVotacion; }

    @Override
    public String toString() {
        return "Territorio{" + nombre + " (" + tipo + ")}";
    }
}
