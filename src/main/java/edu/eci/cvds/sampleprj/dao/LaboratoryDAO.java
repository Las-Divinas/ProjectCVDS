package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Laboratory;

public interface LaboratoryDAO {
    public void registrarLaboratorio(Laboratory lab) throws PersistenceException;
    public int consultarUltimoId() throws PersistenceException;
}
