package com.elecciones.modelo;

/** Creador concreto: un departamento no tiene territorio padre. */
public class DepartamentoFactory extends TerritorioFactory {

    @Override
    protected Territorio crearTerritorio(String id, String nombre, Territorio padre) {
        if (padre != null) {
            throw new IllegalArgumentException("Un departamento no tiene territorio padre");
        }
        return new Territorio(id, nombre, "departamento", null);
    }
}