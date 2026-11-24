package com.elecciones.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el acta de resultados de una mesa de votación.
 * Autor: Carlos Camacho - feature/candidato-partido
 */
public class Acta {
    private String idActa;
    private Mesa mesa;
    private String fechaCierre;
    private List<ResultadoCandidato> resultados;

    public Acta(String idActa, Mesa mesa, String fechaCierre) {
        this.idActa = idActa;
        this.mesa = mesa;
        this.fechaCierre = fechaCierre;
        this.resultados = new ArrayList<>();
        mesa.registrarActa(this);
    }

    public void consignarResultado(Candidato candidato, int votos) {
        resultados.add(new ResultadoCandidato(candidato, votos));
    }

    public int totalVotos() {
        int total = 0;
        for (ResultadoCandidato r : resultados) {
            total += r.getVotosObtenidos();
        }
        return total;
    }

    public String getIdActa() { return idActa; }
    public Mesa getMesa() { return mesa; }
    public String getFechaCierre() { return fechaCierre; }
    public List<ResultadoCandidato> getResultados() { return resultados; }

    @Override
    public String toString() {
        return "Acta{mesa=" + mesa.getNumeroMesa() + ", totalVotos=" + totalVotos() + "}";
    }
}