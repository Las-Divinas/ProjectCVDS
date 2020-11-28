package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Novelty;

public interface ServicioNovelty {
    void registrarNovedad(Novelty n) throws ExceptionHistorialDeEquipos;
    List<Novelty> consultarNovedades() throws ExceptionHistorialDeEquipos;
    List<Novelty> consultarNovedadesPorIDEquipo(Integer equipoID) throws ExceptionHistorialDeEquipos;
    List<Novelty> consultarNovedadesPorIDElemento(Integer elementoID) throws ExceptionHistorialDeEquipos;
    List<Novelty> consultarNovedadesPorIDLaboratorio(Integer laboratorioID) throws ExceptionHistorialDeEquipos;
}
