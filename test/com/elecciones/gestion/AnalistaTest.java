package com.elecciones.gestion;

import com.elecciones.modelo.Acta;
import com.elecciones.modelo.Candidato;
import com.elecciones.modelo.DepartamentoFactory;
import com.elecciones.modelo.Mesa;
import com.elecciones.modelo.PartidoPolitico;
import com.elecciones.modelo.PuestoVotacion;
import com.elecciones.modelo.Territorio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de la entidad Analista (HU11 - revision de resultados).
 *
 * Metodos probados: totalVotosTerritorio(), compararResultados(), actaPublicada().
 */
class AnalistaTest {

    /** Crea un departamento con un puesto, una mesa y un acta con los votos indicados. */
    private Territorio territorioConVotos(String id, int votos) {
        Territorio depto = new DepartamentoFactory().crear(id, "Territorio " + id, null);
        PuestoVotacion puesto = new PuestoVotacion("PV-" + id, "Puesto", "dir", depto);
        depto.agregarPuestoVotacion(puesto);
        Mesa mesa = new Mesa(1, 300, puesto);
        puesto.agregarMesa(mesa);
        Acta acta = new Acta("A-" + id, mesa, "2026-03-08");
        PartidoPolitico p = new PartidoPolitico("PP-" + id, "Partido");
        acta.consignarResultado(new Candidato("C-" + id, "Cand", p), votos);
        return depto;
    }

    // POSITIVA: el total de un territorio refleja los votos de toda su jerarquia (Composite).
    @Test
    void totalVotosTerritorio_sumaLaJerarquia() {
        Analista analista = new Analista("AN1", "Carlos");
        assertEquals(150, analista.totalVotosTerritorio(territorioConVotos("D1", 150)));
    }

    // CASO LIMITE: un territorio sin votos devuelve 0.
    @Test
    void totalVotosTerritorio_territorioSinVotos_devuelveCero() {
        Analista analista = new Analista("AN1", "Carlos");
        Territorio vacio = new DepartamentoFactory().crear("D9", "Vacio", null);
        assertEquals(0, analista.totalVotosTerritorio(vacio));
    }

    // POSITIVA: comparar devuelve positivo cuando el primero tiene mas votos.
    @Test
    void compararResultados_primeroMayor_devuelvePositivo() {
        Analista analista = new Analista("AN1", "Carlos");
        int dif = analista.compararResultados(territorioConVotos("D1", 200), territorioConVotos("D2", 80));
        assertEquals(120, dif);
    }

    // CASO LIMITE: dos territorios con los mismos votos dan diferencia 0 (empate).
    @Test
    void compararResultados_empate_devuelveCero() {
        Analista analista = new Analista("AN1", "Carlos");
        int dif = analista.compararResultados(territorioConVotos("D1", 100), territorioConVotos("D2", 100));
        assertEquals(0, dif);
    }

    // POSITIVA: como observador, registra cada acta publicada que recibe.
    @Test
    void actaPublicada_incrementaActasRecibidas() {
        Analista analista = new Analista("AN1", "Carlos");
        Mesa mesa = new Mesa(1, 300, null);
        Acta acta = new Acta("A1", mesa, "2026-03-08");

        analista.actaPublicada(acta);
        analista.actaPublicada(acta);

        assertEquals(2, analista.getActasRecibidas());
    }
}
