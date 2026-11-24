package com.elecciones.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del patron FACTORY METHOD (creacional) - TerritorioFactory,
 * DepartamentoFactory y MunicipioFactory.
 *
 * Metodos probados: crear(id, nombre, padre) en ambas fabricas concretas.
 */
class TerritorioFactoryTest {

    private final TerritorioFactory departamentos = new DepartamentoFactory();
    private final TerritorioFactory municipios = new MunicipioFactory();

    // --- DepartamentoFactory ---

    // POSITIVA: la fabrica de departamentos crea un territorio tipo "departamento" sin padre.
    @Test
    void departamento_conDatosValidos_creaDepartamento() {
        Territorio cund = departamentos.crear("D1", "Cundinamarca", null);
        assertEquals("departamento", cund.getTipo());
        assertNull(cund.getTerritorioPadre());
    }

    // NEGATIVA: un departamento no puede tener territorio padre.
    @Test
    void departamento_conPadre_lanzaIllegalArgument() {
        Territorio otro = departamentos.crear("D1", "Cundinamarca", null);
        assertThrows(IllegalArgumentException.class,
                () -> departamentos.crear("D2", "Boyaca", otro));
    }

    // NEGATIVA: id en blanco es invalido (validacion de la clase creadora base).
    @Test
    void departamento_idEnBlanco_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> departamentos.crear("  ", "Cundinamarca", null));
    }

    // --- MunicipioFactory ---

    // POSITIVA: un municipio valido queda tipado como "municipio", enlazado a su departamento
    // y registrado como subterritorio de este (relacion Composite).
    @Test
    void municipio_conDepartamento_creaYEnlaza() {
        Territorio cund = departamentos.crear("D1", "Cundinamarca", null);
        Territorio bogota = municipios.crear("M1", "Bogota", cund);

        assertEquals("municipio", bogota.getTipo());
        assertSame(cund, bogota.getTerritorioPadre());
        assertTrue(cund.getSubterritorios().contains(bogota));
    }

    // NEGATIVA: un municipio no puede crearse sin departamento padre.
    @Test
    void municipio_sinPadre_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> municipios.crear("M1", "Bogota", null));
    }

    // NEGATIVA: el padre de un municipio debe ser un departamento, no otro municipio.
    @Test
    void municipio_conPadreNoDepartamento_lanzaIllegalArgument() {
        Territorio cund = departamentos.crear("D1", "Cundinamarca", null);
        Territorio bogota = municipios.crear("M1", "Bogota", cund);
        assertThrows(IllegalArgumentException.class,
                () -> municipios.crear("M2", "Soacha", bogota));
    }

    // CASO LIMITE: nombre en blanco es invalido.
    @Test
    void municipio_nombreEnBlanco_lanzaIllegalArgument() {
        Territorio cund = departamentos.crear("D1", "Cundinamarca", null);
        assertThrows(IllegalArgumentException.class,
                () -> municipios.crear("M1", "", cund));
    }
}
