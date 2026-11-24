package com.elecciones.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Patrón Builder (creacional).
 * Construye un Acta paso a paso y valida los datos antes de crearla.
 * Evita un constructor largo y que queden actas con datos inválidos.
 */
public class ActaBuilder {
    private final Mesa mesa;
    private String idActa;
    private String fechaCierre;
    private final Map<Candidato, Integer> votos = new LinkedHashMap<>();

    public ActaBuilder(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("El acta requiere una mesa");
        }
        this.mesa = mesa;
    }

    public ActaBuilder conId(String idActa) {
        this.idActa = idActa;
        return this;
    }

    public ActaBuilder cerradaEn(String fechaCierre) {
        this.fechaCierre = fechaCierre;
        return this;
    }

    public ActaBuilder conResultado(Candidato candidato, int cantidadVotos) {
        if (candidato == null) {
            throw new IllegalArgumentException("El candidato no puede ser nulo");
        }
        if (cantidadVotos < 0) {
            throw new IllegalArgumentException("Los votos no pueden ser negativos");
        }
        if (votos.containsKey(candidato)) {
            throw new IllegalArgumentException("Candidato repetido en el acta: " + candidato.getNombre());
        }
        votos.put(candidato, cantidadVotos);
        return this;
    }

    public Acta construir() {
        if (idActa == null || idActa.isBlank()) {
            throw new IllegalStateException("El acta requiere un id");
        }
        if (fechaCierre == null || fechaCierre.isBlank()) {
            throw new IllegalStateException("El acta requiere fecha de cierre");
        }
        Acta acta = new Acta(idActa, mesa, fechaCierre);
        for (Map.Entry<Candidato, Integer> e : votos.entrySet()) {
            acta.consignarResultado(e.getKey(), e.getValue());
        }
        return acta;
    }
}
