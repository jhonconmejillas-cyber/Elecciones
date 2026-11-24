package com.elecciones.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un partido político y sus candidatos.
 * Autor: Carlos Camacho - feature/candidato-partido
 */
public class PartidoPolitico {
    private String idPartido;
    private String nombre;
    private List<Candidato> candidatos;

    public PartidoPolitico(String idPartido, String nombre) {
        this.idPartido = idPartido;
        this.nombre = nombre;
        this.candidatos = new ArrayList<>();
    }

    public void agregarCandidato(Candidato c) {
        candidatos.add(c);
    }

    public String getIdPartido() { return idPartido; }
    public String getNombre() { return nombre; }
    public List<Candidato> getCandidatos() { return candidatos; }

    @Override
    public String toString() {
        return "PartidoPolitico{" + nombre + "}";
    }
}
