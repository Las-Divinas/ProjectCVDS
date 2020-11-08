package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipment;
import java.util.List;

public interface EquipoDAO {
    public void registrarEquipo(Equipment equipo) throws PersistenceException;
    public List<Equipment> consultarEquipos() throws PersistenceException;
    
}
