package com.elecciones.gestion;

import com.elecciones.modelo.Acta;
import com.elecciones.modelo.ProcesoElectoral;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del patron FACADE (estructural) - SistemaElectoralFacade.
 * La fachada coordina fabricas (Factory), builder (Builder), jerarquia (Composite)
 * y notificaciones (Observer) tras una interfaz unica y simple.
 *
 * Metodos probados: constructor, registrar*, cerrarMesa(), votosDe(), suscribir().
 */
class SistemaElectoralFacadeTest {

    private SistemaElectoralFacade nuevaFachada() {
        ProcesoElectoral proceso = new ProcesoElectoral("P1", "Legislativas", "2026-03-08", "planeado");
        return new SistemaElectoralFacade(proceso);
    }

    // POSITIVA (flujo completo): registrar territorio/puesto/mesa/partido/candidato,
    // cerrar la mesa (Builder + Observer) y consultar el total por territorio (Composite).
    @Test
    void flujoCompleto_cierraMesaYConsolidaVotos() {
        SistemaElectoralFacade sistema = nuevaFachada();
        Analista analista = new Analista("AN1", "Carlos");
        sistema.suscribir(analista);

        sistema.registrarDepartamento("D1", "Cundinamarca");
        sistema.registrarPuesto("PV1", "Colegio Central", "Calle 10", "D1");
        sistema.registrarMesa("PV1", 1, 300);
        sistema.registrarPartido("PP1", "Partido Verde");
        sistema.registrarCandidato("C1", "Ana", "PP1");
        sistema.registrarCandidato("C2", "Luis", "PP1");

        Map<String, Integer> votos = new LinkedHashMap<>();
        votos.put("C1", 120);
        votos.put("C2", 95);
        Acta acta = sistema.cerrarMesa("A1", "PV1", 1, "2026-03-08 18:00", votos);

        assertEquals(215, acta.totalVotos());
        assertEquals(215, sistema.votosDe("D1"));
        assertEquals(1, analista.getActasRecibidas()); // el observer fue notificado
    }

    // NEGATIVA: la fachada no se puede crear sin un proceso electoral.
    @Test
    void constructor_procesoNulo_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> new SistemaElectoralFacade(null));
    }

    // NEGATIVA: registrar un municipio bajo un departamento inexistente falla.
    @Test
    void registrarMunicipio_departamentoInexistente_lanzaIllegalArgument() {
        SistemaElectoralFacade sistema = nuevaFachada();
        assertThrows(IllegalArgumentException.class,
                () -> sistema.registrarMunicipio("M1", "Bogota", "NO_EXISTE"));
    }

    // NEGATIVA: cerrar una mesa que no existe en el puesto falla.
    @Test
    void cerrarMesa_mesaInexistente_lanzaIllegalArgument() {
        SistemaElectoralFacade sistema = nuevaFachada();
        sistema.registrarDepartamento("D1", "Cundinamarca");
        sistema.registrarPuesto("PV1", "Colegio", "dir", "D1");
        assertThrows(IllegalArgumentException.class,
                () -> sistema.cerrarMesa("A1", "PV1", 99, "2026-03-08", new LinkedHashMap<>()));
    }

    // CASO LIMITE: consultar votos de un territorio recien creado (sin mesas) devuelve 0.
    @Test
    void votosDe_territorioSinMesas_devuelveCero() {
        SistemaElectoralFacade sistema = nuevaFachada();
        sistema.registrarDepartamento("D1", "Cundinamarca");
        assertEquals(0, sistema.votosDe("D1"));
    }
}
