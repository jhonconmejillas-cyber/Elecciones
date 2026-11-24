package com.elecciones.modelo;

/**
 * Patrón Factory Method (creacional) - clase creadora.
 * Define el método de fábrica crearTerritorio(...) que cada subclase implementa
 * según el tipo de territorio (departamento o municipio).
 */
public abstract class TerritorioFactory {

    /** Operación común: valida los datos y delega la creación al método de fábrica. */
    public final Territorio crear(String id, String nombre, Territorio padre) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El territorio requiere un id");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El territorio requiere un nombre");
        }
        return crearTerritorio(id, nombre, padre);
    }

    /** Método de fábrica: cada subclase decide qué tipo de territorio crea. */
    protected abstract Territorio crearTerritorio(String id, String nombre, Territorio padre);
}