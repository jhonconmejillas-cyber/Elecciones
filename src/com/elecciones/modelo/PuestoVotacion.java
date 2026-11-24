package com.elecciones.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un puesto de votación dentro de un territorio.
 * Autor: Juan Maldonado - feature/proceso-territorio
 */
public class PuestoVotacion implements ElementoElectoral {
    private String idPuesto;
    private String nombre;
    private String ubicacion;
    private Territorio territorio;
    private List<Mesa> mesas;

    public PuestoVotacion(String idPuesto, String nombre, String ubicacion, Territorio territorio) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.territorio = territorio;
        this.mesas = new ArrayList<>();
    }

    public void agregarMesa(Mesa m) {
        mesas.add(m);
    }
        @Override
    public int totalVotos() {
        int total = 0;
        for (Mesa m : mesas) {
            total += m.totalVotos();
        }
        return total;
    }

    @Override
    public int totalCenso() {
        int total = 0;
        for (Mesa m : mesas) {
            total += m.totalCenso();
        }
        return total;
    }
    public String getIdPuesto() { return idPuesto; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public Territorio getTerritorio() { return territorio; }
    public List<Mesa> getMesas() { return mesas; }

    @Override
    public String toString() {
        return "PuestoVotacion{" + nombre + ", " + ubicacion + "}";
    }
}
