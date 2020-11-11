package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipment;
import java.util.List;

public interface EquipoDAO {
    public void registrarEquipo(Equipment equipo) throws PersistenceException;
    public List<Equipment> consultarEquipos() throws PersistenceException;
    public int consultarUltimoId() throws PersistenceException;
    public Equipment consultarEquipoPorId(int id) throws PersistenceException;
    public void cambiarLaboratorio(int idLaboratory, int idEquipment) throws PersistenceException;
    
}
