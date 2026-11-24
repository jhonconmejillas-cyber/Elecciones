package com.elecciones.gestion;

import com.elecciones.modelo.Acta;

/**
 * Patrón Observer (comportamental) - interfaz del observador.
 * Quien implemente esta interfaz es notificado cuando se publica un acta.
 */
public interface ResultadoObserver {

    void actaPublicada(Acta acta);
}