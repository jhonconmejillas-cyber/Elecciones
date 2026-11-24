package com.elecciones.gestion;

import com.elecciones.modelo.Acta;
import com.elecciones.modelo.Territorio;

/**
 * Representa un analista que revisa los resultados consolidados.
 * Es observador de las actas publicadas (Observer) y consulta totales
 * mediante la jerarquía Composite de Territorio.
 * Autor: Jhon Mejia
 */
public class Analista implements ResultadoObserver {
    private String idAnalista;
    private String nombre;
    private int actasRecibidas;

    public Analista(String idAnalista, String nombre) {
        this.idAnalista = idAnalista;
        this.nombre = nombre;
    }

    public int compararResultados(Territorio t1, Territorio t2) {
        int total1 = totalVotosTerritorio(t1);
        int total2 = totalVotosTerritorio(t2);
        System.out.println(t1.getNombre() + ": " + total1 + " votos | " + t2.getNombre() + ": " + total2 + " votos");
        return total1 - total2;
    }

    public int totalVotosTerritorio(Territorio t) {
        return t.totalVotos(); // Composite: recorre puestos, mesas y municipios
    }

    @Override
    public void actaPublicada(Acta acta) {
        actasRecibidas++;
        System.out.println("Analista " + nombre + " recibió el acta " + acta.getIdActa()
                + " (" + acta.totalVotos() + " votos)");
    }

    public int getActasRecibidas() { return actasRecibidas; }
    public String getIdAnalista() { return idAnalista; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Analista{" + nombre + "}";
    }
}