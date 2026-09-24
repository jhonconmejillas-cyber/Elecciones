package com.elecciones.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un proceso electoral (ej. Elecciones Legislativas 2026).
 * Autor: Juan Maldonado - feature/proceso-territorio
 */
public class ProcesoElectoral {
    private String idProceso;
    private String tipoEleccion;
    private String fecha;
    private String estado;
    private List<Territorio> territorios;

    public ProcesoElectoral(String idProceso, String tipoEleccion, String fecha, String estado) {
        this.idProceso = idProceso;
        this.tipoEleccion = tipoEleccion;
        this.fecha = fecha;
        this.estado = estado;
        this.territorios = new ArrayList<>();
    }

    public void agregarTerritorio(Territorio t) {
        territorios.add(t);
    }

    public String getIdProceso() { return idProceso; }
    public String getTipoEleccion() { return tipoEleccion; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<Territorio> getTerritorios() { return territorios; }

    @Override
    public String toString() {
        return "ProcesoElectoral{" + tipoEleccion + ", " + fecha + ", " + estado + "}";
    }
}
