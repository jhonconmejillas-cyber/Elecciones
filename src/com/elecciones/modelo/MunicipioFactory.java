package com.elecciones.modelo;

/** Creador concreto: un municipio debe pertenecer a un departamento y se enlaza a él. */
public class MunicipioFactory extends TerritorioFactory {

    @Override
    protected Territorio crearTerritorio(String id, String nombre, Territorio padre) {
        if (padre == null || !"departamento".equals(padre.getTipo())) {
            throw new IllegalArgumentException("Un municipio debe pertenecer a un departamento");
        }
        Territorio municipio = new Territorio(id, nombre, "municipio", padre);
        padre.agregarSubterritorio(municipio);
        return municipio;
    }
}