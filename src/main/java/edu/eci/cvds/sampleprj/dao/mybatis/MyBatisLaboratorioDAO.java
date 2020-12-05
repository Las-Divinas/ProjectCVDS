package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratoryMapper;
import edu.eci.cvds.samples.entities.Laboratory;

public class MyBatisLaboratorioDAO implements LaboratoryDAO {

    /* Aqui se inyecta LaboratorioMapper que va hacer Nikolas */
    @Inject
    private LaboratoryMapper laboratorioMapper;


    @Override
    public void registrarLaboratorio(Laboratory lab) throws PersistenceException {
        try {
            laboratorioMapper.registrarLaboratorio(lab);
        } catch (Exception e) {
            throw new PersistenceException("Error al registrar el nuevo Laboratorio");
        }

    }

    @Override
    public int consultarUltimoId() throws PersistenceException {
        try {
            return laboratorioMapper.consultarUltimoId();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar el ultimo Id de la tabla laboratorio");
        }
    }
    
    @Override
    public List<Laboratory> consultarLaboratorios() throws PersistenceException{
        try {
            return laboratorioMapper.consultarLaboratorios();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar los laboratorios");
        }
    }

    @Override
    public String consultarNombreLaboratorio(Integer laboratorioID) throws PersistenceException{
        try {
            if(laboratorioID!=null){
                return laboratorioMapper.consultarNombreLaboratorio(laboratorioID);
            }

            return "N/A";
        } catch (Exception e) {
            throw new PersistenceException(e.toString());
        }
    }

    @Override
    public Integer consultarIDLaboratorioPorNombre(String nombreLaboratorio) throws PersistenceException {
        try {
            return laboratorioMapper.consultarIDLaboratorioPorNombre(nombreLaboratorio);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar ID de "+nombreLaboratorio);
        }
    }

    @Override
    public List<Laboratory> laboratorioActivo() throws PersistenceException {
        try {
            return laboratorioMapper.laboratorioActivo();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar laboratorios activos");
        }
    }

    @Override
    public void cambiarEstadoLaboratorio(String estadoLaboratorio, Integer laboratorioID) throws PersistenceException {
        try {
            laboratorioMapper.cambiarEstadoLaboratorio(estadoLaboratorio, laboratorioID);
        } catch (Exception e) {
            throw new PersistenceException("Error al cambiar el estado del laboratorio");
        }
    }

    @Override
    public void cambiarFechaDeCierre(Date fechaCierre, Integer laboratorioID) throws PersistenceException {
        try {
            laboratorioMapper.cambiarFechaDeCierre(fechaCierre, laboratorioID);
        } catch (Exception e) {
            throw new PersistenceException("Error al cambiar fecha de cierre de laboratorio");
        }
    }

    @Override
    public List<Laboratory> consultarLaboratoriosActivos() throws PersistenceException {
        try {
            return laboratorioMapper.consultarLaboratoriosActivos();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar laboratorios activos");
        }
    }
}