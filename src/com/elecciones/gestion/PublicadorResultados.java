package com.elecciones.gestion;

import com.elecciones.modelo.Acta;

import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Observer (comportamental) - sujeto observable.
 * Al publicar el acta de una mesa, notifica a todos los suscriptores
 * (analistas, auditoría, etc.) sin acoplarse a ninguno en particular.
 */
public class PublicadorResultados {
    private final List<ResultadoObserver> observadores = new ArrayList<>();

    public void suscribir(ResultadoObserver observador) {
        if (observador == null) {
            throw new IllegalArgumentException("El observador no puede ser nulo");
        }
        observadores.add(observador);
    }

    public void cancelar(ResultadoObserver observador) {
        observadores.remove(observador);
    }

    public void publicar(Acta acta) {
        for (ResultadoObserver o : observadores) {
            o.actaPublicada(acta);
        }
    }

    public int cantidadObservadores() {
        return observadores.size();
    }
}