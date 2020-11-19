package edu.eci.cvds.samples.services.impl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Equipment;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioEquipment;

public class ServicioEquipmentImpl implements ServicioEquipment {
    @Inject
    private EquipoDAO equipoDAO;

    @Override
    public void registrarEquipment(Equipment e) throws ExceptionHistorialDeEquipos {
        try {
            equipoDAO.registrarEquipo(e);
        } catch (PersistenceException ex) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el equipo");
        }
    }

    @Override
    public List<Equipment> consultarEquipos() throws ExceptionHistorialDeEquipos{
        try {
            return equipoDAO.consultarEquipos();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar los equipos");
        }
    }

    @Override
    public int consultarUltimoIdEquipment() throws ExceptionHistorialDeEquipos {
        try {
            return equipoDAO.consultarUltimoId();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar el ultimo Id de los equipos");
        }
    }

    @Override
    public Equipment consultarEquipoPorId(int id) throws ExceptionHistorialDeEquipos {
        try {
            return equipoDAO.consultarEquipoPorId(id);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar el equipo con el Id "+id);
        }
    }

    @Override
    public void cambiarLaboratorioEquipo(int idLaboratory, int idEquipment) throws ExceptionHistorialDeEquipos {
        try {
            equipoDAO.cambiarLaboratorio(idLaboratory, idEquipment);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    @Override
    public int consultarNumeroEquipos(int laboratory_id) throws ExceptionHistorialDeEquipos {
        try {
            return equipoDAO.consultarNumeroEquipos(laboratory_id);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar los laboratorios");
        }
    }

    @Override
    public void eliminarEquipoPorId(int id) throws ExceptionHistorialDeEquipos {
        try {
            equipoDAO.eliminarEquipoPorId(id);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al eliminar el equipo con id "+id);
        }
    }

    @Override
    public void cambiarEstadoElementoId(int id,String estado) throws ExceptionHistorialDeEquipos {
        try {
            equipoDAO.cambiarEstadoElementoId(id,estado);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }

    }
}
