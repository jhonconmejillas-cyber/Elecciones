package com.elecciones.modelo;

/**
 * Representa el resultado (votos) de un candidato dentro de un acta.
 * Autor: Carlos Camacho - feature/candidato-partido
 */
public class ResultadoCandidato {
    private Candidato candidato;
    private int votosObtenidos;

    public ResultadoCandidato(Candidato candidato, int votosObtenidos) {
        this.candidato = candidato;
        this.votosObtenidos = votosObtenidos;
    }

    public Candidato getCandidato() { return candidato; }
    public int getVotosObtenidos() { return votosObtenidos; }
    public void setVotosObtenidos(int votosObtenidos) { this.votosObtenidos = votosObtenidos; }

    @Override
    public String toString() {
        return candidato.getNombre() + ": " + votosObtenidos + " votos";
    }
}