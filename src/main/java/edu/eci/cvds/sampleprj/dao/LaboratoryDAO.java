package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Laboratory;
import org.apache.ibatis.annotations.Param;

public interface LaboratoryDAO {
    void registrarLaboratorio(Laboratory lab) throws PersistenceException;
    int consultarUltimoId() throws PersistenceException;
    List<Laboratory> consultarLaboratorios() throws PersistenceException;
    String consultarNombreLaboratorio(Integer laboratorioID) throws PersistenceException;
    Integer consultarIDLaboratorioPorNombre(String nombreLaboratorio) throws PersistenceException;
}
