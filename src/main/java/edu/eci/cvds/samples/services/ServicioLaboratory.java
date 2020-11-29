package edu.eci.cvds.samples.services;

import java.util.List;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Laboratory;

public interface ServicioLaboratory {
    void registrarLaboratorio(Laboratory l) throws ExceptionHistorialDeEquipos;
    int consultarUltimoIdLaboratorio() throws ExceptionHistorialDeEquipos;
    List<Laboratory> consultarLaboratorios() throws ExceptionHistorialDeEquipos;
    String consultarNombreLaboratorio(Integer laboratorioID) throws ExceptionHistorialDeEquipos;
    Integer consultarIDLaboratorioPorNombre(String nombreLaboratorio) throws ExceptionHistorialDeEquipos;
}
