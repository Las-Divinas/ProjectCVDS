package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Novelty;

public interface ServicioNovelty {
    public abstract void registrarNovedad(Novelty n) throws ExceptionHistorialDeEquipos;
    public abstract List<Novelty> consultarNovedades() throws ExceptionHistorialDeEquipos;
    public List<Novelty> consultarNovedadesPorIDEquipo(int equipoID) throws ExceptionHistorialDeEquipos;
    public List<Novelty> consultarNovedadesPorIDElemento(int elementoID) throws ExceptionHistorialDeEquipos;
}
