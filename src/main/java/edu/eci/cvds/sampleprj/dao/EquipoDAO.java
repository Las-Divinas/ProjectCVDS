package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipment;

public interface EquipoDAO {
    public void registrarEquipo(Equipment equipo) throws PersistenceException;
    
}
