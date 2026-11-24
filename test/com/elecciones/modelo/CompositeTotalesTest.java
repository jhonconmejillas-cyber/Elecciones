package com.elecciones.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del patron COMPOSITE (estructural) sobre la jerarquia
 * Territorio -> PuestoVotacion -> Mesa (interfaz ElementoElectoral).
 *
 * Metodos probados: totalVotos() y totalCenso() en los tres niveles.
 */
class CompositeTotalesTest {

    private Candidato candidato() {
        PartidoPolitico p = new PartidoPolitico("PP1", "Verde");
        return new Candidato("C1", "Ana", p);
    }

    private Mesa mesaConVotos(PuestoVotacion puesto, int numero, int censo, int votos) {
        Mesa mesa = new Mesa(numero, censo, puesto);
        puesto.agregarMesa(mesa);
        Acta acta = new Acta("A" + numero, mesa, "2026-03-08");
        acta.consignarResultado(candidato(), votos);
        return mesa;
    }

    // POSITIVA: una mesa delega su total en el acta registrada.
    @Test
    void mesa_totalVotos_reflejaElActa() {
        PuestoVotacion puesto = new PuestoVotacion("PV1", "Colegio", "dir", null);
        Mesa mesa = mesaConVotos(puesto, 1, 300, 150);
        assertEquals(150, mesa.totalVotos());
        assertEquals(300, mesa.totalCenso());
    }

    // CASO LIMITE: una mesa sin acta totaliza 0 votos (coleccion/acta vacia).
    @Test
    void mesa_sinActa_totalizaCero() {
        Mesa mesa = new Mesa(9, 250, null);
        assertEquals(0, mesa.totalVotos());
        assertEquals(250, mesa.totalCenso());
    }

    // POSITIVA: un puesto suma los votos y el censo de todas sus mesas.
    @Test
    void puesto_sumaMesas() {
        PuestoVotacion puesto = new PuestoVotacion("PV1", "Colegio", "dir", null);
        mesaConVotos(puesto, 1, 300, 100);
        mesaConVotos(puesto, 2, 200, 80);
        assertEquals(180, puesto.totalVotos());
        assertEquals(500, puesto.totalCenso());
    }

    // POSITIVA (recursividad del Composite): un departamento suma sus municipios,
    // sus puestos y, por lo tanto, todas las mesas de la jerarquia.
    @Test
    void departamento_sumaRecursivaDeTodaLaJerarquia() {
        Territorio cund = new DepartamentoFactory().crear("D1", "Cundinamarca", null);
        Territorio bogota = new MunicipioFactory().crear("M1", "Bogota", cund);

        PuestoVotacion puestoDepto = new PuestoVotacion("PV1", "Puesto Depto", "dir", cund);
        cund.agregarPuestoVotacion(puestoDepto);
        mesaConVotos(puestoDepto, 1, 300, 100);

        PuestoVotacion puestoMuni = new PuestoVotacion("PV2", "Puesto Muni", "dir", bogota);
        bogota.agregarPuestoVotacion(puestoMuni);
        mesaConVotos(puestoMuni, 2, 400, 250);

        // 100 (puesto del depto) + 250 (puesto del municipio hijo)
        assertEquals(350, cund.totalVotos());
        assertEquals(700, cund.totalCenso());
    }

    // CASO LIMITE: un territorio sin puestos ni subterritorios totaliza 0.
    @Test
    void territorioVacio_totalizaCero() {
        Territorio vacio = new DepartamentoFactory().crear("D9", "Vacio", null);
        assertEquals(0, vacio.totalVotos());
        assertEquals(0, vacio.totalCenso());
    }
}
