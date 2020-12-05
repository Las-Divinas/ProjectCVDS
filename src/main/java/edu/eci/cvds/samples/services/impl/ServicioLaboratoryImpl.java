package edu.eci.cvds.samples.services.impl;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Laboratory;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioLaboratory;

public class ServicioLaboratoryImpl implements ServicioLaboratory {

    @Inject
    private LaboratoryDAO laboratoryDAO;

    @Override
    public void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos {
        try {
            laboratoryDAO.registrarLaboratorio(l);
        } catch (PersistenceException e) {
            throw new ExceptionHistorialDeEquipos("Error al registrar el laboratorio");
        }
    }

    @Override
    public int consultarUltimoIdLaboratorio() throws ExceptionHistorialDeEquipos {
        try {
            return laboratoryDAO.consultarUltimoId();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.toString());
        }
    }

    @Override
    public List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos {
        try {
            return laboratoryDAO.consultarLaboratorios();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar los laboratorios");
        }
    }

    @Override
    public String consultarNombreLaboratorio(Integer laboratorioID) throws ExceptionHistorialDeEquipos {
        try {
            return laboratoryDAO.consultarNombreLaboratorio(laboratorioID);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos(e.getMessage());
        }
    }

    @Override
    public Integer consultarIDLaboratorioPorNombre(String nombreLaboratorio) throws ExceptionHistorialDeEquipos {
        try {
            return laboratoryDAO.consultarIDLaboratorioPorNombre(nombreLaboratorio);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar ID de "+nombreLaboratorio);
        }
    }

    @Override
    public List<Laboratory> laboratorioActivo() throws ExceptionHistorialDeEquipos {
        try {
            return laboratoryDAO.laboratorioActivo();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar laboratorios activos");
        }
    }

    @Override
    public void cambiarEstadoLaboratorio(String estadoLaboratorio, Integer laboratorioID) throws ExceptionHistorialDeEquipos {
        try {
            laboratoryDAO.cambiarEstadoLaboratorio(estadoLaboratorio, laboratorioID);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al cambiar estado de laboratorio");
        }
    }

    @Override
    public void cambiarFechaDeCierre(Date fechaCierre, Integer laboratorioID) throws ExceptionHistorialDeEquipos {
        try {
            laboratoryDAO.cambiarFechaDeCierre(fechaCierre, laboratorioID);
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al cambiar fecha de cierre de laboratorio");
        }
    }

    @Override
    public List<Laboratory> consultarLaboratoriosActivos() throws ExceptionHistorialDeEquipos {
        try {
            return laboratoryDAO.consultarLaboratoriosActivos();
        } catch (Exception e) {
            throw new ExceptionHistorialDeEquipos("Error al consultar laboratorios activos");
        }
    }
}
