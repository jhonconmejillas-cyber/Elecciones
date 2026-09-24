package com.elecciones;

import com.elecciones.modelo.*;
import com.elecciones.gestion.*;

public class Main {
    public static void main(String[] args) {
        // Juan Maldonado 
        ProcesoElectoral proceso = new ProcesoElectoral("P1", "Legislativas", "2026-03-08", "planeado");
        Territorio cundinamarca = new Territorio("T1", "Cundinamarca", "departamento", null);
        proceso.agregarTerritorio(cundinamarca);

        PuestoVotacion puesto = new PuestoVotacion("PV1", "Colegio Central", "Calle 10 #5-20", cundinamarca);
        cundinamarca.agregarPuestoVotacion(puesto);

        Mesa mesa1 = new Mesa(1, 300, puesto);
        puesto.agregarMesa(mesa1);
        mesa1.instalarMesa();

        PartidoPolitico partido = new PartidoPolitico("PP1", "Partido Verde");
        Candidato candidato1 = new Candidato("C1", "Ana Torres", partido);
        Candidato candidato2 = new Candidato("C2", "Luis Gomez", partido);

        Jurado jurado = new Jurado("J1", "Maria Perez", "Presidente de mesa");
        mesa1.asignarJurado(jurado);
        jurado.atenderVotante();

        Acta acta = new Acta("A1", mesa1, "2026-03-08 18:00");
        jurado.registrarResultado(acta, candidato1, 120);
        jurado.registrarResultado(acta, candidato2, 95);
        System.out.println(acta);
        Incidente incidente = new Incidente("I1", "Retraso en apertura", puesto, "2026-03-08 08:15");
        System.out.println(incidente);

        Reclamacion reclamacion = new Reclamacion("R1", "Discrepancia en conteo", mesa1, "Partido Verde");
        reclamacion.revisarReclamacion();
        System.out.println(reclamacion);

        Analista analista = new Analista("AN1", "Carlos Ruiz");
        System.out.println("Total votos en " + cundinamarca.getNombre() + ": " +
                analista.totalVotosTerritorio(cundinamarca));
    }
}
