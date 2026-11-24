package com.elecciones.gestion;

import com.elecciones.modelo.Mesa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del patron STATE (comportamental) sobre Reclamacion y sus estados
 * (pendiente -> en revision -> resuelta).
 *
 * Metodos probados: getEstado(), revisarReclamacion(), resolverReclamacion().
 * Se validan tanto las transiciones permitidas como las prohibidas.
 */
class ReclamacionStateTest {

    private Reclamacion nuevaReclamacion() {
        Mesa mesa = new Mesa(1, 300, null);
        return new Reclamacion("R1", "Discrepancia en conteo", mesa, "Partido Verde");
    }

    // POSITIVA: una reclamacion recien creada esta en estado "pendiente".
    @Test
    void nueva_estaPendiente() {
        assertEquals("pendiente", nuevaReclamacion().getEstado());
    }

    // POSITIVA: transicion valida pendiente -> en revision.
    @Test
    void revisar_desdePendiente_pasaAEnRevision() {
        Reclamacion r = nuevaReclamacion();
        r.revisarReclamacion();
        assertEquals("en revision", r.getEstado());
    }

    // POSITIVA: transicion valida completa pendiente -> en revision -> resuelta.
    @Test
    void resolver_desdeEnRevision_pasaAResuelta() {
        Reclamacion r = nuevaReclamacion();
        r.revisarReclamacion();
        r.resolverReclamacion();
        assertEquals("resuelta", r.getEstado());
    }

    // NEGATIVA: no se puede resolver una reclamacion que aun esta pendiente.
    @Test
    void resolver_desdePendiente_lanzaIllegalState() {
        Reclamacion r = nuevaReclamacion();
        assertThrows(IllegalStateException.class, r::resolverReclamacion);
    }

    // NEGATIVA: no se puede volver a revisar una que ya esta en revision.
    @Test
    void revisar_dosVeces_lanzaIllegalState() {
        Reclamacion r = nuevaReclamacion();
        r.revisarReclamacion();
        assertThrows(IllegalStateException.class, r::revisarReclamacion);
    }

    // NEGATIVA / CASO LIMITE: estado final "resuelta" no admite mas transiciones.
    @Test
    void estadoResuelta_noAdmiteMasTransiciones() {
        Reclamacion r = nuevaReclamacion();
        r.revisarReclamacion();
        r.resolverReclamacion();
        assertThrows(IllegalStateException.class, r::revisarReclamacion);
        assertThrows(IllegalStateException.class, r::resolverReclamacion);
    }
}
