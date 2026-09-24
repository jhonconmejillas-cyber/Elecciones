package com.elecciones.gestion;

import com.elecciones.modelo.Territorio;
import com.elecciones.modelo.PuestoVotacion;
import com.elecciones.modelo.Mesa;
//clase analista
/**
 * Representa un analista que revisa los resultados consolidados.
 * Autor: Jhon Mejia
 */
public class Analista {
    private String idAnalista;
    private String nombre;

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
        int total = 0;
        for (PuestoVotacion p : t.getPuestosVotacion()) {
            for (Mesa m : p.getMesas()) {
                if (m.getActa() != null) {
                    total += m.getActa().totalVotos();
                }
            }
        }
        return total;
    }

    public String getIdAnalista() { return idAnalista; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Analista{" + nombre + "}";
    }
}
