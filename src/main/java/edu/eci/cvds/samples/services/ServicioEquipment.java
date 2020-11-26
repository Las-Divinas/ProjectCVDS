package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Equipment;

public interface ServicioEquipment {
    void registrarEquipment(Equipment e) throws ExceptionHistorialDeEquipos;
    List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos;
    int consultarUltimoIdEquipment() throws ExceptionHistorialDeEquipos;
    Equipment consultarEquipoPorId(int id) throws ExceptionHistorialDeEquipos;
    void cambiarLaboratorioEquipo(Integer idLaboratory, Integer idEquipment) throws ExceptionHistorialDeEquipos;
    int consultarNumeroEquipos(int laboratory_id) throws ExceptionHistorialDeEquipos;
    void eliminarEquipoPorId(int id) throws ExceptionHistorialDeEquipos;
    void cambiarEstadoElementoId(int id,String estado) throws ExceptionHistorialDeEquipos;
    String consultarNombreEquipo(Integer equipmentID) throws ExceptionHistorialDeEquipos;
    Integer consultarEquipoIDporNombre(String nombreEquipo) throws ExceptionHistorialDeEquipos;
}
