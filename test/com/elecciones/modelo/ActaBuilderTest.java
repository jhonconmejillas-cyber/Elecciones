package com.elecciones.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del patron BUILDER (creacional) - clase ActaBuilder.
 *
 * Metodos probados: constructor(Mesa), conResultado(Candidato,int), construir().
 * Se incluye al menos una prueba positiva, una negativa (datos invalidos /
 * excepciones) y una de caso limite, segun lo exigido por el parcial.
 */
class ActaBuilderTest {

    private Mesa nuevaMesa() {
        return new Mesa(1, 300, null);
    }

    private Candidato nuevoCandidato(String id, String nombre) {
        PartidoPolitico partido = new PartidoPolitico("PP-" + id, "Partido " + id);
        return new Candidato(id, nombre, partido);
    }

    // --- construir() ---

    // POSITIVA: con id, fecha y resultados validos se construye el Acta y suma los votos.
    @Test
    void construir_conDatosValidos_creaActaConResultados() {
        Candidato ana = nuevoCandidato("C1", "Ana");
        Candidato luis = nuevoCandidato("C2", "Luis");

        Acta acta = new ActaBuilder(nuevaMesa())
                .conId("A1")
                .cerradaEn("2026-03-08")
                .conResultado(ana, 120)
                .conResultado(luis, 95)
                .construir();

        assertEquals("A1", acta.getIdActa());
        assertEquals(215, acta.totalVotos());
        assertEquals(2, acta.getResultados().size());
    }

    // CASO LIMITE: un acta sin resultados (ninguna llamada a conResultado) es valida y totaliza 0.
    @Test
    void construir_sinResultados_devuelveActaConCero() {
        Acta acta = new ActaBuilder(nuevaMesa())
                .conId("A2")
                .cerradaEn("2026-03-08")
                .construir();

        assertEquals(0, acta.totalVotos());
        assertTrue(acta.getResultados().isEmpty());
    }

    // NEGATIVA: construir sin id debe fallar con IllegalStateException (invariante del builder).
    @Test
    void construir_sinId_lanzaIllegalState() {
        ActaBuilder builder = new ActaBuilder(nuevaMesa()).cerradaEn("2026-03-08");
        assertThrows(IllegalStateException.class, builder::construir);
    }

    // NEGATIVA: construir sin fecha de cierre tambien debe fallar.
    @Test
    void construir_sinFecha_lanzaIllegalState() {
        ActaBuilder builder = new ActaBuilder(nuevaMesa()).conId("A3");
        assertThrows(IllegalStateException.class, builder::construir);
    }

    // --- constructor(Mesa) ---

    // NEGATIVA: no se puede iniciar un builder sin mesa.
    @Test
    void constructor_mesaNula_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> new ActaBuilder(null));
    }

    // --- conResultado(Candidato, int) ---

    // NEGATIVA: votos negativos no estan permitidos.
    @Test
    void conResultado_votosNegativos_lanzaIllegalArgument() {
        ActaBuilder builder = new ActaBuilder(nuevaMesa());
        Candidato ana = nuevoCandidato("C1", "Ana");
        assertThrows(IllegalArgumentException.class, () -> builder.conResultado(ana, -50));
    }

    // NEGATIVA: candidato nulo no esta permitido.
    @Test
    void conResultado_candidatoNulo_lanzaIllegalArgument() {
        ActaBuilder builder = new ActaBuilder(nuevaMesa());
        assertThrows(IllegalArgumentException.class, () -> builder.conResultado(null, 10));
    }

    // NEGATIVA: no se puede registrar dos veces al mismo candidato en una misma acta.
    @Test
    void conResultado_candidatoRepetido_lanzaIllegalArgument() {
        ActaBuilder builder = new ActaBuilder(nuevaMesa());
        Candidato ana = nuevoCandidato("C1", "Ana");
        builder.conResultado(ana, 10);
        assertThrows(IllegalArgumentException.class, () -> builder.conResultado(ana, 20));
    }

    // CASO LIMITE: cero votos es un valor valido (borde inferior permitido).
    @Test
    void conResultado_ceroVotos_esValido() {
        Acta acta = new ActaBuilder(nuevaMesa())
                .conId("A4")
                .cerradaEn("2026-03-08")
                .conResultado(nuevoCandidato("C1", "Ana"), 0)
                .construir();
        assertEquals(0, acta.totalVotos());
    }
}
