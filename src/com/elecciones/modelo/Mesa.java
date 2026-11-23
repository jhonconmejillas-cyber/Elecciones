package com.elecciones.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una mesa de votación dentro de un puesto.
 * Autor: Juan Maldonado - feature/proceso-territorio
 */
public class Mesa {
    private int numeroMesa;
    private int censoVotantes;
    private PuestoVotacion puestoVotacion;
    private List<Jurado> jurados;
    private Acta acta;

    public Mesa(int numeroMesa, int censoVotantes, PuestoVotacion puestoVotacion) {
        this.numeroMesa = numeroMesa;
        this.censoVotantes = censoVotantes;
        this.puestoVotacion = puestoVotacion;
        this.jurados = new ArrayList<>();
    }

    public void asignarJurado(Jurado j) {
        jurados.add(j);
        j.setMesaAsignada(this);
    }

    public void instalarMesa() {
        System.out.println("Mesa " + numeroMesa + " instalada en " + puestoVotacion.getNombre());
    }

    public void registrarActa(Acta a) {
        this.acta = a;
    }

    public int getNumeroMesa() { return numeroMesa; }
    public int getCensoVotantes() { return censoVotantes; }
    public PuestoVotacion getPuestoVotacion() { return puestoVotacion; }
    public List<Jurado> getJurados() { return jurados; }
    public Acta getActa() { return acta; }

    @Override
    public String toString() {
        return "Mesa{" + numeroMesa + ", censo=" + censoVotantes + "}";
    }
}
