package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Novelty;

public interface NovedadDAO {
    public void registrarNovedad(Novelty novedad) throws PersistenceException;
}
