package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Laboratory;

public interface LaboratoryDAO {
    public void registrarLaboratorio(Laboratory lab) throws PersistenceException;
    public int consultarUltimoId() throws PersistenceException;
    public List<Laboratory> consultarLaboratorios() throws PersistenceException;
}
