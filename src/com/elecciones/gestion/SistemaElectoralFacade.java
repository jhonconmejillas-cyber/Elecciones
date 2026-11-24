package com.elecciones.gestion;

import com.elecciones.modelo.Acta;
import com.elecciones.modelo.ActaBuilder;
import com.elecciones.modelo.Candidato;
import com.elecciones.modelo.DepartamentoFactory;
import com.elecciones.modelo.Mesa;
import com.elecciones.modelo.MunicipioFactory;
import com.elecciones.modelo.PartidoPolitico;
import com.elecciones.modelo.ProcesoElectoral;
import com.elecciones.modelo.PuestoVotacion;
import com.elecciones.modelo.Territorio;
import com.elecciones.modelo.TerritorioFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Patrón Facade (estructural).
 * Ofrece una interfaz única y simple para gestionar el proceso electoral,
 * ocultando la coordinación entre fábricas, builder, composite y observer.
 */
public class SistemaElectoralFacade {
    private final ProcesoElectoral proceso;
    private final TerritorioFactory fabricaDepartamentos = new DepartamentoFactory();
    private final TerritorioFactory fabricaMunicipios = new MunicipioFactory();
    private final PublicadorResultados publicador = new PublicadorResultados();
    private final Map<String, Territorio> territorios = new HashMap<>();
    private final Map<String, PuestoVotacion> puestos = new HashMap<>();
    private final Map<String, PartidoPolitico> partidos = new HashMap<>();
    private final Map<String, Candidato> candidatos = new HashMap<>();

    public SistemaElectoralFacade(ProcesoElectoral proceso) {
        if (proceso == null) {
            throw new IllegalArgumentException("El proceso electoral no puede ser nulo");
        }
        this.proceso = proceso;
    }

    public void suscribir(ResultadoObserver observador) {
        publicador.suscribir(observador);
    }

    public Territorio registrarDepartamento(String id, String nombre) {
        Territorio departamento = fabricaDepartamentos.crear(id, nombre, null);
        proceso.agregarTerritorio(departamento);
        territorios.put(id, departamento);
        return departamento;
    }

    public Territorio registrarMunicipio(String id, String nombre, String idDepartamento) {
        Territorio padre = obtener(territorios, idDepartamento, "territorio");
        Territorio municipio = fabricaMunicipios.crear(id, nombre, padre);
        territorios.put(id, municipio);
        return municipio;
    }

    public PuestoVotacion registrarPuesto(String id, String nombre, String ubicacion, String idTerritorio) {
        Territorio territorio = obtener(territorios, idTerritorio, "territorio");
        PuestoVotacion puesto = new PuestoVotacion(id, nombre, ubicacion, territorio);
        territorio.agregarPuestoVotacion(puesto);
        puestos.put(id, puesto);
        return puesto;
    }

    public Mesa registrarMesa(String idPuesto, int numeroMesa, int censo) {
        PuestoVotacion puesto = obtener(puestos, idPuesto, "puesto");
        Mesa mesa = new Mesa(numeroMesa, censo, puesto);
        puesto.agregarMesa(mesa);
        return mesa;
    }

    public PartidoPolitico registrarPartido(String id, String nombre) {
        PartidoPolitico partido = new PartidoPolitico(id, nombre);
        partidos.put(id, partido);
        return partido;
    }

    public Candidato registrarCandidato(String id, String nombre, String idPartido) {
        PartidoPolitico partido = obtener(partidos, idPartido, "partido");
        Candidato candidato = new Candidato(id, nombre, partido);
        candidatos.put(id, candidato);
        return candidato;
    }

    /** Cierra una mesa: construye el acta (Builder) y la publica a los observadores (Observer). */
    public Acta cerrarMesa(String idActa, String idPuesto, int numeroMesa, String fechaCierre,
                           Map<String, Integer> votosPorCandidato) {
        Mesa mesa = buscarMesa(idPuesto, numeroMesa);
        ActaBuilder builder = new ActaBuilder(mesa).conId(idActa).cerradaEn(fechaCierre);
        for (Map.Entry<String, Integer> e : votosPorCandidato.entrySet()) {
            builder.conResultado(obtener(candidatos, e.getKey(), "candidato"), e.getValue());
        }
        Acta acta = builder.construir();
        publicador.publicar(acta);
        return acta;
    }

    /** Total de votos de un territorio (Composite). */
    public int votosDe(String idTerritorio) {
        return obtener(territorios, idTerritorio, "territorio").totalVotos();
    }

    private Mesa buscarMesa(String idPuesto, int numeroMesa) {
        PuestoVotacion puesto = obtener(puestos, idPuesto, "puesto");
        for (Mesa m : puesto.getMesas()) {
            if (m.getNumeroMesa() == numeroMesa) {
                return m;
            }
        }
        throw new IllegalArgumentException("No existe la mesa " + numeroMesa + " en el puesto " + idPuesto);
    }

    private <T> T obtener(Map<String, T> mapa, String id, String tipo) {
        T valor = mapa.get(id);
        if (valor == null) {
            throw new IllegalArgumentException("No existe " + tipo + " con id " + id);
        }
        return valor;
    }
}