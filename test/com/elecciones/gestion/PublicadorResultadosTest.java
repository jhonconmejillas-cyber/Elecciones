package com.elecciones.gestion;

import com.elecciones.modelo.Acta;
import com.elecciones.modelo.Candidato;
import com.elecciones.modelo.Mesa;
import com.elecciones.modelo.PartidoPolitico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del patron OBSERVER (comportamental) - PublicadorResultados (sujeto),
 * RegistroAuditoria y Analista (observadores).
 *
 * Metodos probados: suscribir(), cancelar(), publicar(), cantidadObservadores().
 */
class PublicadorResultadosTest {

    private Acta actaDeEjemplo(String id, int votos) {
        Mesa mesa = new Mesa(1, 300, null);
        Acta acta = new Acta(id, mesa, "2026-03-08");
        PartidoPolitico p = new PartidoPolitico("PP1", "Verde");
        acta.consignarResultado(new Candidato("C1", "Ana", p), votos);
        return acta;
    }

    // POSITIVA: al publicar un acta, todos los observadores suscritos son notificados.
    @Test
    void publicar_notificaATodosLosObservadores() {
        PublicadorResultados publicador = new PublicadorResultados();
        RegistroAuditoria auditoria = new RegistroAuditoria();
        Analista analista = new Analista("AN1", "Carlos");
        publicador.suscribir(auditoria);
        publicador.suscribir(analista);

        publicador.publicar(actaDeEjemplo("A1", 215));

        assertEquals(1, auditoria.getRegistros().size());
        assertEquals(1, analista.getActasRecibidas());
    }

    // POSITIVA: cantidadObservadores refleja las suscripciones activas.
    @Test
    void suscribir_incrementaCantidadObservadores() {
        PublicadorResultados publicador = new PublicadorResultados();
        assertEquals(0, publicador.cantidadObservadores());
        publicador.suscribir(new RegistroAuditoria());
        assertEquals(1, publicador.cantidadObservadores());
    }

    // POSITIVA: un observador cancelado ya no recibe notificaciones.
    @Test
    void cancelar_dejaDeNotificar() {
        PublicadorResultados publicador = new PublicadorResultados();
        RegistroAuditoria auditoria = new RegistroAuditoria();
        publicador.suscribir(auditoria);
        publicador.cancelar(auditoria);

        publicador.publicar(actaDeEjemplo("A1", 100));

        assertEquals(0, publicador.cantidadObservadores());
        assertTrue(auditoria.getRegistros().isEmpty());
    }

    // NEGATIVA: no se puede suscribir un observador nulo.
    @Test
    void suscribir_nulo_lanzaIllegalArgument() {
        PublicadorResultados publicador = new PublicadorResultados();
        assertThrows(IllegalArgumentException.class, () -> publicador.suscribir(null));
    }

    // CASO LIMITE: publicar sin observadores no genera error (lista vacia).
    @Test
    void publicar_sinObservadores_noFalla() {
        PublicadorResultados publicador = new PublicadorResultados();
        assertDoesNotThrow(() -> publicador.publicar(actaDeEjemplo("A1", 50)));
    }
}
