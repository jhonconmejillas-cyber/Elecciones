package com.elecciones.gestion;

/**
 * Patrón State (comportamental) - interfaz del estado.
 * Cada estado de una Reclamacion decide qué transiciones son válidas.
 */
public interface EstadoReclamacion {

    String nombre();

    void revisar(Reclamacion reclamacion);

    void resolver(Reclamacion reclamacion);
}