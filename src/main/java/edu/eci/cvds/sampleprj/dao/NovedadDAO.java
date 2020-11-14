package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Novelty;

import java.util.List;

public interface NovedadDAO {
    public void registrarNovedad(Novelty novedad) throws PersistenceException;
    public List<Novelty> consultarNovedades() throws PersistenceException;
}
