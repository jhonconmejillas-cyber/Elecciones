package com.elecciones.modelo;

/**
 * Componente del patrón Composite.
 * Es la interfaz común de la jerarquía Territorio -> PuestoVotacion -> Mesa:
 * el cliente puede pedir totales sin saber si es una hoja (Mesa) o un compuesto.
 */
public interface ElementoElectoral {

    /** Total de votos registrados en actas dentro de este elemento. */
    int totalVotos();

    /** Censo aproximado de votantes habilitados dentro de este elemento. */
    int totalCenso();
}