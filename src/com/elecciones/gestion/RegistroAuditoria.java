package com.elecciones.gestion;

import com.elecciones.modelo.Acta;

import java.util.ArrayList;
import java.util.List;

/**
 * Observador concreto: deja trazabilidad de cada acta publicada.
 */
public class RegistroAuditoria implements ResultadoObserver {
    private final List<String> registros = new ArrayList<>();

    @Override
    public void actaPublicada(Acta acta) {
        registros.add("Acta " + acta.getIdActa() + " publicada (mesa "
                + acta.getMesa().getNumeroMesa() + ", " + acta.totalVotos() + " votos)");
    }

    public List<String> getRegistros() {
        return registros;
    }
}