package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipment;

import java.util.List;

public interface EquipoDAO {
    void registrarEquipo(Equipment equipo) throws PersistenceException;
    List<Equipment> consultarEquipos() throws PersistenceException;
    int consultarUltimoId() throws PersistenceException;
    Equipment consultarEquipoPorId(int id) throws PersistenceException;
    void cambiarLaboratorio(Integer idLaboratory, Integer idEquipment) throws PersistenceException;
    int consultarNumeroEquipos(int laboratory_id) throws PersistenceException;
    void eliminarEquipoPorId(int id) throws PersistenceException;
    void cambiarEstadoElementoId(int id,String estado) throws PersistenceException;
    String consultarNombreEquipo(Integer equipmentID) throws PersistenceException;
    Integer consultarEquipoIDporNombre(String nombreEquipo) throws PersistenceException;
}
