package edu.eci.cvds.sampleprj.dao.mybatis;

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
    
}