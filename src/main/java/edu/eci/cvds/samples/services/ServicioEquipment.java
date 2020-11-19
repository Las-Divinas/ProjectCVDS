package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.samples.entities.Equipment;

public interface ServicioEquipment {
    public abstract void registrarEquipment(Equipment e) throws ExceptionHistorialDeEquipos;
    public abstract List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos;
    public abstract int consultarUltimoIdEquipment() throws ExceptionHistorialDeEquipos;
    public abstract Equipment consultarEquipoPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract void cambiarLaboratorioEquipo(int idLaboratory, int idEquipment) throws ExceptionHistorialDeEquipos;
    public int consultarNumeroEquipos(int laboratory_id) throws ExceptionHistorialDeEquipos;
    public abstract void eliminarEquipoPorId(int id) throws ExceptionHistorialDeEquipos;
    public abstract void cambiarEstadoElementoId(int id,String estado) throws ExceptionHistorialDeEquipos;
}
