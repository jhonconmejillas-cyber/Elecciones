package com.elecciones;

import com.elecciones.gestion.*;
import com.elecciones.modelo.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Juan Maldonado
        ProcesoElectoral proceso = new ProcesoElectoral("P1", "Legislativas", "2026-03-08", "planeado");

        // Facade: un solo punto de entrada para toda la gestión
        SistemaElectoralFacade sistema = new SistemaElectoralFacade(proceso);

        // Observer: analista y auditoría se suscriben a la publicación de actas
        Analista analista = new Analista("AN1", "Carlos Ruiz");
        RegistroAuditoria auditoria = new RegistroAuditoria();
        sistema.suscribir(analista);
        sistema.suscribir(auditoria);

        // Factory Method (dentro del Facade) + Composite: departamento -> municipio -> puesto -> mesa
        Territorio cundinamarca = sistema.registrarDepartamento("T1", "Cundinamarca");
        sistema.registrarMunicipio("T2", "Zipaquira", "T1");
        PuestoVotacion puesto = sistema.registrarPuesto("PV1", "Colegio Central", "Calle 10 #5-20", "T2");
        Mesa mesa1 = sistema.registrarMesa("PV1", 1, 300);
        mesa1.instalarMesa();

        sistema.registrarPartido("PP1", "Partido Verde");
        sistema.registrarCandidato("C1", "Ana Torres", "PP1");
        sistema.registrarCandidato("C2", "Luis Gomez", "PP1");

        Jurado jurado = new Jurado("J1", "Maria Perez", "Presidente de mesa");
        mesa1.asignarJurado(jurado);
        jurado.atenderVotante();

        // Builder (dentro del Facade) + Observer: se construye el acta y se notifica
        Map<String, Integer> votos = new LinkedHashMap<>();
        votos.put("C1", 120);
        votos.put("C2", 95);
        Acta acta = sistema.cerrarMesa("A1", "PV1", 1, "2026-03-08 18:00", votos);
        System.out.println(acta);

        Incidente incidente = new Incidente("I1", "Retraso en apertura", puesto, "2026-03-08 08:15");
        System.out.println(incidente);

        // State: pendiente -> en revision -> resuelta (y transición inválida)
        Reclamacion reclamacion = new Reclamacion("R1", "Discrepancia en conteo", mesa1, "Partido Verde");
        System.out.println(reclamacion);
        reclamacion.revisarReclamacion();
        System.out.println(reclamacion);
        reclamacion.resolverReclamacion();
        System.out.println(reclamacion);
        try {
            reclamacion.revisarReclamacion();
        } catch (IllegalStateException e) {
            System.out.println("Transicion invalida: " + e.getMessage());
        }

        // Composite: el total del departamento incluye municipios, puestos y mesas
        System.out.println("Total votos en " + cundinamarca.getNombre() + ": "
                + analista.totalVotosTerritorio(cundinamarca));
        System.out.println("Registros de auditoria: " + auditoria.getRegistros());
    }
}