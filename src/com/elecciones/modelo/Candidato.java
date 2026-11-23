package com.elecciones.modelo;

/**
 * Representa un candidato inscrito por un partido político.
 * Autor: Carlos Camacho - feature/candidato-partido
 */
public class Candidato {
    private String idCandidato;
    private String nombre;
    private PartidoPolitico partido;

    public Candidato(String idCandidato, String nombre, PartidoPolitico partido) {
        this.idCandidato = idCandidato;
        this.nombre = nombre;
        this.partido = partido;
        partido.agregarCandidato(this);
    }

    public String getIdCandidato() { return idCandidato; }
    public String getNombre() { return nombre; }
    public PartidoPolitico getPartido() { return partido; }

    @Override
    public String toString() {
        return "Candidato{" + nombre + ", partido=" + partido.getNombre() + "}";
    }
}
