package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Novelty;

import java.util.List;

public interface NovedadDAO {
    void registrarNovedad(Novelty novedad) throws PersistenceException;
    List<Novelty> consultarNovedades() throws PersistenceException;
    List<Novelty> consultarNovedadesPorIDEquipo(Integer equipoID) throws PersistenceException;
    List<Novelty> consultarNovedadesPorIDElemento(Integer elementoID) throws PersistenceException;
    List<Novelty> consultarNovedadesPorIDLaboratorio(Integer laboratorioID) throws PersistenceException;
}
